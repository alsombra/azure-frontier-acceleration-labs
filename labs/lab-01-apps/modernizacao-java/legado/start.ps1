param(
    [Parameter(Mandatory = $true)][string]$JavaHome,
    [ValidateRange(1024, 65535)][int]$Port = 18081
)
$ErrorActionPreference = 'Stop'
$java = Join-Path $JavaHome 'bin/java.exe'
if (-not (Test-Path -LiteralPath $java)) { throw 'JavaHome deve conter bin/java.exe do Java 8.' }
$injected = @(Get-ChildItem Env: | Where-Object { $_.Name -match '^(SPRING_|JAVA_TOOL_OPTIONS$|_JAVA_OPTIONS$|JDK_JAVA_OPTIONS$|JAVA_OPTS$|CLASSPATH$)' })
if ($injected.Count) { throw ('Remova variaveis que podem injetar configuracao: ' + ($injected.Name -join ', ')) }
$savedPreference = $ErrorActionPreference
try {
    $ErrorActionPreference = 'Continue'
    $version = (& $java -version 2>&1 | ForEach-Object { $_.ToString() } | Out-String)
    $versionExit = $LASTEXITCODE
} finally { $ErrorActionPreference = $savedPreference }
if ($versionExit -ne 0) { throw 'Falha ao consultar Java.' }
if ($version -notmatch 'version "1\.8\.') { throw 'Este pacote exige Java 8; nenhuma instalacao sera feita.' }
$jar = Join-Path $PSScriptRoot 'contoso-rh.jar'
$config = Join-Path $PSScriptRoot 'local.properties'
if (-not (Test-Path $jar) -or -not (Test-Path $config)) { throw 'JAR ou local.properties ausente.' }
$checksumFile = Join-Path $PSScriptRoot 'contoso-rh.jar.sha256'
if (-not (Test-Path $checksumFile)) { throw 'Checksum do JAR ausente.' }
$expectedHash = (Get-Content -Raw $checksumFile).Trim().Split(' ')[0]
if ($expectedHash -notmatch '^[A-Fa-f0-9]{64}$' -or (Get-FileHash $jar -Algorithm SHA256).Hash -ne $expectedHash) { throw 'SHA256 do JAR divergente.' }
if (Test-Path (Join-Path $PSScriptRoot 'runtime/process.json')) { throw 'Registro de processo existe. Execute stop.ps1 antes de reiniciar.' }
$listener = New-Object System.Net.Sockets.TcpListener([System.Net.IPAddress]::Loopback, $Port)
try { $listener.Start() } finally { $listener.Stop() }
$runtime = New-Item -ItemType Directory -Force -Path (Join-Path $PSScriptRoot 'runtime')
$configUri = ([Uri]$config).AbsoluteUri
$arguments = @(
    ('-Dspring.config.location=' + $configUri),
    '-Dspring.profiles.active=local',
    '-Dspring.datasource.driver-class-name=org.h2.Driver',
    '-Dspring.datasource.url=jdbc:h2:mem:contoso_local;MODE=Oracle;DB_CLOSE_DELAY=-1',
    '-Dspring.datasource.username=sa',
    '-Dspring.datasource.password=',
    '-Dspring.jpa.database-platform=org.hibernate.dialect.H2Dialect',
    '-Dspring.jpa.hibernate.ddl-auto=create-drop',
    '-Dspring.datasource.initialize=false',
    '-Dspring.h2.console.enabled=false',
    '-Dserver.address=127.0.0.1',
    "-Dserver.port=$Port",
    '-Dmanagement.address=127.0.0.1',
    '-Dmanagement.port=-1',
    '-Dendpoints.enabled=false',
    '-Dmanagement.security.enabled=true',
    '-Duser.timezone=UTC',
    '-jar', $jar
)
$quotedArguments = ($arguments | ForEach-Object { '"' + $_ + '"' }) -join ' '
$process = Start-Process -FilePath $java -ArgumentList $quotedArguments -WorkingDirectory $PSScriptRoot -RedirectStandardOutput (Join-Path $runtime 'stdout.log') -RedirectStandardError (Join-Path $runtime 'stderr.log') -PassThru
[ordered]@{ pid = $process.Id; startedUtc = $process.StartTime.ToUniversalTime().ToString('o'); jar = $jar; port = $Port } | ConvertTo-Json | Set-Content -Encoding UTF8 (Join-Path $runtime 'process.json')
Write-Output "PID $($process.Id). Aguarde a inicializacao no runtime/stdout.log. API: http://127.0.0.1:$Port/api/ferias/funcionario/1"
Write-Output 'Execute bootstrap.ps1 para criar o exemplo ficticio e stop.ps1 ao terminar.'