param([ValidateRange(1024, 65535)][int]$Port = 18081)
$ErrorActionPreference = 'Stop'
$lock = [IO.File]::Open((Join-Path $PSScriptRoot '.operation.lock'), 'OpenOrCreate', 'ReadWrite', 'None')
try {
$processRecord = Get-Content -Raw (Join-Path $PSScriptRoot 'runtime/process.json') | ConvertFrom-Json
$owned = Get-Process -Id $processRecord.pid -ErrorAction Stop
$details = Get-CimInstance Win32_Process -Filter "ProcessId=$($processRecord.pid)"
if ($processRecord.port -ne $Port -or $owned.StartTime.ToUniversalTime().ToString('o') -ne $processRecord.startedUtc -or $processRecord.jar -ne (Join-Path $PSScriptRoot 'contoso-rh.jar') -or $details.CommandLine -notlike ('*"' + $processRecord.jar + '"*')) { throw 'Processo/porta do pacote nao confere; nenhum POST enviado.' }
$base = "http://127.0.0.1:$Port/api/ferias"
$existing = Invoke-WebRequest -UseBasicParsing -Uri "$base/funcionario/1" -TimeoutSec 10
$items = $existing.Content | ConvertFrom-Json
if (@($items).Count -ne 0) { throw 'Bootstrap exige banco vazio para funcionario 1. Reinicie o pacote para repetir.' }
$created = Invoke-WebRequest -UseBasicParsing -Method Post -Uri "$base`?funcId=1&inicio=1798761600000&dias=10" -TimeoutSec 10
$record = $created.Content | ConvertFrom-Json
if ($created.StatusCode -ne 200 -or $record.funcionarioId -ne 1 -or $record.dias -ne 10 -or $record.status -ne 'SOLICITADA' -or $record.id -ne 1) { throw 'POST nao retornou o registro sintetico esperado.' }
function CheckRecord($item) {
    $fields = @($item.PSObject.Properties.Name | Sort-Object)
    if (($fields -join ',') -cne 'dataFim,dataInicio,dias,funcionarioId,id,status') { throw 'Campos JSON divergentes.' }
    if ($item.dataInicio -isnot [long] -or $item.dataFim -isnot [long]) { throw 'Datas JSON devem ser numeros inteiros epoch milliseconds, nao strings.' }
    if ($item.dataInicio -ne 1798761600000 -or $item.dataFim -ne 1799625600000) { throw 'Datas JSON divergentes.' }
    if ($item.id -ne 1 -or $item.funcionarioId -ne 1 -or $item.dias -ne 10 -or $item.status -cne 'SOLICITADA') { throw 'Valores JSON divergentes.' }
}
CheckRecord $record
$response = Invoke-WebRequest -UseBasicParsing -Uri "$base/funcionario/1" -TimeoutSec 10
$records = $response.Content | ConvertFrom-Json
if ($response.StatusCode -ne 200 -or @($records).Count -ne 1 -or $records[0].id -ne 1 -or $records[0].funcionarioId -ne 1 -or $records[0].dias -ne 10) { throw 'GET nao confirmou o registro criado.' }
CheckRecord $records[0]
$evidence = [ordered]@{
    timestampUtc = [DateTime]::UtcNow.ToString('o')
    syntheticOnly = $true
    before = @{ method = 'GET'; url = "$base/funcionario/1"; status = [int]$existing.StatusCode; body = $existing.Content }
    post = @{ method = 'POST'; url = "$base`?funcId=1&inicio=1798761600000&dias=10"; status = [int]$created.StatusCode; body = $created.Content }
    after = @{ method = 'GET'; url = "$base/funcionario/1"; status = [int]$response.StatusCode; body = $response.Content }
}
New-Item -ItemType Directory -Force -Path (Join-Path $PSScriptRoot 'runtime') | Out-Null
$evidence | ConvertTo-Json -Depth 6 | Set-Content -Encoding UTF8 (Join-Path $PSScriptRoot 'runtime/http.json')
$response.Content
} finally { $lock.Dispose() }