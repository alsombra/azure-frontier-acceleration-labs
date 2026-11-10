# Fontes Java do laboratorio

| Variante | Fontes | Ferramentas |
| --- | --- | --- |
| Baseline H2 v2 | [Legado](legado/README.md) | JDK 8, Boot 1.5.22.RELEASE |
| Referencia final | [Modernizado](modernizado/README.md) | JDK 21, Boot 4.0.9 |

Somente fontes e testes. Leia o [aviso de uso](AVISO-DE-USO.md).
O final usa Jackson Databind 3.1.7 observado no classpath historico, gerenciado
pelo parent Boot, nao fixado diretamente no POM; confira a versao resolvida
na sua execucao autorizada. Nao copie o final e o apresente como modernizacao propria.

## Preparar uma copia

No Windows PowerShell 5.1, a partir da raiz deste repositorio, escolha uma pasta
nova fora do repositorio. Nao sobrescreva destinos existentes. Preserve o baseline
e modernize outra copia. JDKs completos e Maven 3.9.x devem estar disponiveis;
nao ha instalacao automatica.

```powershell
$Destino = '<pasta-nova-de-trabalho>'
if (Test-Path -LiteralPath $Destino) { throw 'Escolha uma pasta que ainda nao exista.' }
Copy-Item -LiteralPath '.\labs\lab-01-apps\modernizacao-java\legado' -Destination $Destino -Recurse
Set-Location -LiteralPath $Destino
.\build.ps1 -JavaHome '<raiz-do-JDK8>' -MavenCommand '<caminho-do-mvn.cmd>'
```

O build legado executa `clean package`, incluindo testes. No modernizado use uma
outra pasta nova, copie `modernizado` em vez de `legado` e execute:

```powershell
.\build.ps1 -JavaHome '<raiz-do-JDK21>' -MavenCommand '<caminho-do-mvn.cmd>' -Goal verify
```

O moderno aceita `-Goal test` e `-TestPattern '<classe-de-teste>'` para verificacao
focada; `test` nao gera JAR. `verify` gera o JAR usado pelo launcher. Nao omita testes.
Confira os totais em `target/surefire-reports` e o resultado em
`evidencias-build/build.json`. JAR e checksum sao criados localmente pelo build,
nao distribuidos. Nao inclua saidas, logs ou evidencias na publicacao de fontes.

## Maven e politicas da organizacao

Ambos os builds usam `maven-local.xml` explicitamente como configuracao de usuario
e global (`-s` e `-gs`), com mirror de todos os repositorios para Maven Central.
Portanto nao herdam automaticamente mirrors/proxy/servidores dos settings usuais.
Se sua organizacao exige repositorio ou proxy, solicite configuracao aprovada e
ajuste somente a copia local de `maven-local.xml`, por exemplo com
`<URL-DO-MIRROR-APROVADO>`, `<HOST-DO-PROXY>` e `<ID-DO-SERVIDOR>`.
Esses marcadores nao sao XML pronto para uso. Nunca grave senhas/tokens no Git,
desabilite TLS ou contorne politicas. Sem resolucao aprovada, pare e registre o bloqueio.
Variaveis de injecao sao recusadas pelos scripts; nao remova controles corporativos
automaticamente. Revise com a equipe responsavel quando houver conflito.

## Iniciar e esperar

Na copia legado, somente apos build aprovado:

```powershell
.\start.ps1 -JavaHome '<raiz-do-JDK8>' -Port 18081
Get-Content -LiteralPath '.\runtime\stdout.log' -Tail 30
```

Na copia modernizada, somente apos `verify` aprovado:

```powershell
.\start.ps1 -JavaHome '<raiz-do-JDK21>' -Port 18082
Get-Content -LiteralPath '.\runtime\stdout.log' -Tail 30
```

O launcher retorna PID antes de a aplicacao estar pronta. Aguarde a mensagem
`Started ContosoRhApplication` em `runtime/stdout.log` antes de qualquer HTTP.
Releia o log; se houver erro ou nao iniciar dentro do prazo combinado da sessao,
consulte tambem `runtime/stderr.log`, pare com `stop.ps1` e investigue.
Nao trate a presenca de PID, JAR ou build verde como servidor pronto.
Ambos os scripts tem porta padrao 18081; **passe 18082 explicitamente no moderno**.
Se a porta estiver ocupada, escolha outra autorizada e ajuste todas as chamadas;
nao encerre processos de terceiros. Nao use `java -jar` ou `spring-boot:run` direto.

## GET vazio, bootstrap, GET

Na copia da variante iniciada, use a mesma porta do launcher:

```powershell
$Port = 18081 # use 18082 no modernizado
$Uri = "http://127.0.0.1:$Port/api/ferias/funcionario/1"
Invoke-WebRequest -UseBasicParsing -Uri $Uri -TimeoutSec 10 | Select-Object -ExpandProperty Content
.\bootstrap.ps1 -Port $Port
Invoke-WebRequest -UseBasicParsing -Uri $Uri -TimeoutSec 10 | Select-Object -ExpandProperty Content
.\stop.ps1
```

O primeiro GET deve retornar `[]`. Bootstrap recusa banco nao vazio e faz GET
vazio -> POST `/api/ferias?funcId=1&inicio=1798761600000&dias=10` -> GET.
A resposta esperada e um array com um registro (datas numericas epoch milliseconds):

```json
[{"id":1,"funcionarioId":1,"dataInicio":1798761600000,"dataFim":1799625600000,"dias":10,"status":"SOLICITADA"}]
```

Compare campos e tipos, nao a ordem textual. O bootstrap legado verifica apenas
parte dos campos; confira tambem `dataInicio`, `dataFim` e `status` no GET final.
O moderno valida o contrato completo. Nao ha tela web de colaboradores.
Execute `stop.ps1` mesmo se bootstrap falhar. O script verifica identidade do
processo registrado; nao apague registro PID para contornar falhas. Reiniciar
descarta H2: start -> aguardar Started -> GET vazio -> bootstrap retorna a id 1.

## Limites de validacao

Esta publicacao passou somente por verificacoes estaticas e de integridade.
Nao houve build, testes Java, inicializacao, instalacao ou cloud nesta preparacao.
Resultados historicos de 12 testes no legado H2 v2 e 18 no final nao sao novas
execucoes nem evidencia de build pelo participante. O legado conserva a falha
conhecida de Tickets em H2 (`SYSDATE - :diasAtras`); use ferias como baseline.
Testes do moderno nao certificam todas as consultas Oracle, seguranca ou Azure.

[Roteiro do Lab 1](../03-guia-completo.md).