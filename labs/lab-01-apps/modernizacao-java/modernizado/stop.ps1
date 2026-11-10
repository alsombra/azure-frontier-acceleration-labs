$ErrorActionPreference = 'Stop'
$lock = [IO.File]::Open((Join-Path $PSScriptRoot '.operation.lock'), 'OpenOrCreate', 'ReadWrite', 'None')
try {
$recordPath = Join-Path $PSScriptRoot 'runtime/process.json'
if (-not (Test-Path $recordPath)) { Write-Output 'Nenhum processo registrado por este pacote.'; return }
$record = Get-Content -Raw $recordPath | ConvertFrom-Json
$process = Get-Process -Id $record.pid -ErrorAction SilentlyContinue
if ($process) {
    $details = Get-CimInstance Win32_Process -Filter "ProcessId=$($record.pid)"
    if ($process.StartTime.ToUniversalTime().ToString('o') -ne $record.startedUtc -or $details.CommandLine -notlike ('*"' + $record.jar + '"*') -or $record.jar -ne (Join-Path $PSScriptRoot 'contoso-rh.jar')) { throw 'Identidade do processo nao confere. Nenhum processo foi encerrado.' }
    Stop-Process -Id $process.Id
    if (-not $process.WaitForExit(10000)) { throw 'O processo nao encerrou em 10 segundos; registro preservado.' }
    Write-Output "Processo do pacote encerrado: $($process.Id). Dados H2 descartados."
}
Remove-Item -LiteralPath $recordPath
} finally { $lock.Dispose() }