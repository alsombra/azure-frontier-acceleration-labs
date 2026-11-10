param(
    [Parameter(Mandatory = $true)][string]$JavaHome,
    [Parameter(Mandatory = $true)][string]$MavenCommand,
    [ValidateSet('package','verify','test-compile','test')][string]$Goal = 'verify',
    [string]$TestPattern
)
$ErrorActionPreference = 'Stop'
if (-not (Test-Path (Join-Path $JavaHome 'bin/javac.exe'))) { throw 'JavaHome deve apontar para JDK 21 completo.' }
if (-not (Test-Path -LiteralPath $MavenCommand)) { throw 'Informe o caminho de mvn.cmd.' }
$injected = @(Get-ChildItem Env: | Where-Object { $_.Name -match '^(SPRING_|JAVA_TOOL_OPTIONS$|_JAVA_OPTIONS$|JDK_JAVA_OPTIONS$|MAVEN_OPTS$|MAVEN_ARGS$|CLASSPATH$)' })
if ($injected.Count) { throw ('Remova variaveis de injecao: ' + ($injected.Name -join ', ')) }
$lock = [IO.File]::Open((Join-Path $PSScriptRoot '.operation.lock'), 'OpenOrCreate', 'ReadWrite', 'None')
$savedHome = $env:JAVA_HOME
$savedPreference = $ErrorActionPreference
$evidence = New-Item -ItemType Directory -Force -Path (Join-Path $PSScriptRoot 'evidencias-build')
try {
    if (Test-Path (Join-Path $PSScriptRoot 'runtime/process.json')) { throw 'Pare o processo registrado antes do build.' }
    $env:JAVA_HOME = $JavaHome
    $ErrorActionPreference = 'Continue'
    $version = (& (Join-Path $JavaHome 'bin/java.exe') -version 2>&1 | ForEach-Object { $_.ToString() } | Out-String)
    $ErrorActionPreference = $savedPreference
    if ($version -notmatch 'version "21[\."]') { throw 'Build modernizado exige JDK 21.' }
    $version | Set-Content -Encoding UTF8 (Join-Path $evidence 'java.txt')
    $settings = Join-Path $PSScriptRoot 'maven-local.xml'
    $arguments = @('-B', '-ntp', '-V', '-s', $settings, '-gs', $settings, '-f', (Join-Path $PSScriptRoot 'pom.xml'),
        '-Dspring.datasource.driver-class-name=org.h2.Driver',
        '-Dspring.datasource.url=jdbc:h2:mem:contoso_build;MODE=Oracle;DB_CLOSE_DELAY=-1',
        '-Dspring.datasource.username=sa', '-Dspring.datasource.password=',
        '-Dspring.jpa.database-platform=org.hibernate.dialect.H2Dialect',
        '-Dspring.jpa.hibernate.ddl-auto=create-drop', '-Dspring.sql.init.mode=never',
        '-Dspring.h2.console.enabled=false', '-Dserver.address=127.0.0.1', '-Dserver.port=-1',
        '-Dmanagement.server.address=127.0.0.1', '-Dmanagement.server.port=-1', '-Dmanagement.endpoints.access.default=none',
        '-Duser.timezone=UTC', '-DfailIfNoTests=true', 'clean', $Goal)
    if ($TestPattern) { $arguments += ('-Dtest=' + $TestPattern) }
    $ErrorActionPreference = 'Continue'
    & $MavenCommand @arguments 2>&1 | Tee-Object -FilePath (Join-Path $evidence 'maven.log')
    $buildExit = $LASTEXITCODE
    $ErrorActionPreference = $savedPreference
    if ($buildExit -ne 0) { throw "Maven falhou: exit $buildExit. Consulte evidencias-build/maven.log." }
    if ($Goal -in @('test-compile','test')) { return }
    Copy-Item (Join-Path $PSScriptRoot 'target/contoso-rh.jar') (Join-Path $PSScriptRoot 'contoso-rh.jar')
    $totals = @{ tests = 0; failures = 0; errors = 0; skipped = 0 }
    Get-ChildItem (Join-Path $PSScriptRoot 'target/surefire-reports/TEST-*.xml') | ForEach-Object {
        [xml]$report = Get-Content -Raw $_.FullName
        foreach ($key in @('tests','failures','errors','skipped')) { $totals[$key] += [int]$report.testsuite.GetAttribute($key) }
    }
    if ($totals.tests -eq 0 -or $totals.failures -ne 0 -or $totals.errors -ne 0) { throw 'Resultado de testes nao aprovado.' }
    ((Get-FileHash (Join-Path $PSScriptRoot 'contoso-rh.jar') -Algorithm SHA256).Hash + '  contoso-rh.jar') | Set-Content -Encoding ASCII (Join-Path $PSScriptRoot 'contoso-rh.jar.sha256')
    [ordered]@{ timestampUtc = [DateTime]::UtcNow.ToString('o'); exitCode = $buildExit; runtime = $version.Trim(); tests = $totals; jarSha256 = (Get-FileHash (Join-Path $PSScriptRoot 'contoso-rh.jar') -Algorithm SHA256).Hash } | ConvertTo-Json -Depth 5 | Set-Content -Encoding UTF8 (Join-Path $evidence 'build.json')
} finally { $env:JAVA_HOME = $savedHome; $ErrorActionPreference = $savedPreference; $lock.Dispose() }