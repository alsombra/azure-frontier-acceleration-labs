# Lab 2 SQL - Passo a passo

## 1. Abrir o cenario e os arquivos

Conclua o [setup](SETUP-CLIENTE.md). Abra a pasta deste lab no VS Code e leia o [seed](legado/01-create-contosorh-legacy-sqlserver.sql) e o [baseline](cli/validate-local.sql), sem executar.

Identifique cinco tabelas, duas procedures e uma trigger. Preserve o AS-IS; nao gere outro legado. O comentario de avaliacao no final do SQL e material didatico, nao resultado de ferramenta: nao use a indicacao fixa de nivel 150 como escolha do destino nem trate MONEY como incompatibilidade automatica.

| Objeto | Expectativa do seed intacto, nao resultado observado |
| --- | --- |
| Colaboradores | 15 linhas; VARCHAR, MONEY e NTEXT; CPF ficticio em texto |
| Ferias | 25 linhas; ausencia de FK e CHECK de Status |
| Tickets | 30 linhas; Descricao TEXT |
| Beneficios | 40 linhas; Valor MONEY |
| FeriasLog | 25 linhas geradas pela trigger na primeira carga |
| sp_BuscarColaborador | SQL dinamico concatenado, inseguro intencionalmente |
| sp_SaldoFerias | Cursor; saldo 30 menos dias aprovados; Id 1 esperado 20 |
| trg_Ferias_Audit | Registra cada linha inserida em Ferias |

## 2. Assessment por leitura

Use os [prompts](07-prompts-do-agente.md). Para cada achado, registre objeto/arquivo, evidencia, risco, impacto, prioridade e teste. Analise collation/codificacao, VARCHAR/Unicode, TEXT/NTEXT, precisao monetaria, SQL dinamico, cursor, trigger, chaves e integridade.

Tipos depreciados nao sao automaticamente blockers. VARCHAR pode perder caracteres dependendo da code page; converter depois nao recupera perdas anteriores. MONEY requer justificativa e reconciliacao para conversao, nao troca automatica. Nao execute injecao nessa etapa.

## 3. Planejar

Planeje bootstrap local, baseline real, backup/retorno testavel, adaptacao em copia, schema, carga, reconciliacao e limpeza. Azure SQL Database e o destino proposto. Escolha compatibilidade somente apos testes de comportamento e performance. Verifique suporte atual do metodo de transferencia com fonte oficial datada; nao presuma suporte do SqlPackage a qualquer origem apenas porque a ferramenta esta instalada.

Defina gates de custo, identidade, rede/TLS, origem/destino exclusivos e criterios de parada. Aprovacao do plano nao e aprovacao automatica de cada escrita.

## 4. Bootstrap local autorizado

1. Confirme instancia local ja disponibilizada, engine/edicao/licenca, isolamento, identidade e base ContosoRH_Legacy ausente. Nao inicie servicos por conta propria como atalho.
2. Revise com o responsavel a invocacao do cliente, autenticacao segura, TLS, codificacao e datas. Nao use senha em linha de comando ou no chat.
3. Execute o seed existente uma unica vez, em batches GO, com interrupcao em erro, somente apos autorizacao de criacao/carga. Em falha parcial, pare; nao recarregue automaticamente.
4. Se estiver usando sqlcmd atual, com sintaxe confirmada pelo help e identidade integrada local aprovada, o formato e:

```powershell
sqlcmd -S "<instancia-local-autorizada>" -E -b -f 65001 -i ".\legado\01-create-contosorh-legacy-sqlserver.sql"
sqlcmd -S "<instancia-local-autorizada>" -E -b -f 65001 -d ContosoRH_Legacy -i ".\cli\validate-local.sql"
```

Os placeholders nao sao destinos reais. -E depende de suporte a autenticacao integrada; se nao for compativel, pare e defina metodo seguro aprovado. Nao desabilite verificacao TLS para contornar erro. Nao cole os comandos sem revisar cliente, alvo e efeitos.

5. Registre versao/edicao/collation/code page, horario, comando sanitizado e codigo de saida. LOCAL_BASELINE_OK com 15/25/30/40 e apenas baseline parcial.
6. Com permissao de leitura, confira FeriasLog, chaves, nulos, orfaos, datas, precisao e consultas benignas de busca/saldo. O baseline nao faz essas verificacoes adicionais.
7. Prepare e teste backup/retorno conforme o plano antes de adaptar ou transferir. Nao declare um .bak existente sem verifica-lo.

## 5. Adaptar e transferir com gates

1. Preserve o seed original e trabalhe em copia. Nao aplique CREATE DATABASE/USE do seed no Azure SQL Database.
2. Revise o metodo suportado e comandos de criacao de destino, schema e carga separadamente. Confirme custo, quota, menor privilegio, TLS/rede restrita e aprovacao de cada escrita.
3. Proponha busca parametrizada preservando semantica de LIKE, e alternativa ao cursor preservando saldo para Id sem ferias e inexistente. Nao corrija silenciosamente o AS-IS.
4. Justifique conversoes de tipos. Antes de FK/CHECK/indices, verifique dados e semantica. Controle trigger/log para evitar duplicacao de auditoria durante a carga.
5. Execute somente a etapa aprovada e pare em erro. Nao execute testes de ataque no destino ou em dados corporativos.
6. Registre resultados reais sem credenciais. A existencia do banco destino ou contagens iguais nao comprova migracao concluida.

## 6. Comparar e aceitar

Compare as cinco tabelas, conjuntos e unicidade de chaves, nulos, orfaos, datas, nomes/codificacao e valores monetarios. Confira busca benigna, saldo para Id 1/sem ferias/inexistente, trigger e constraints. Explique mudancas aprovadas de comportamento.

Teste acesso permitido/negado, TLS e retorno. Registre esperado/observado/diferenca/evidencia. Sem baseline e resultados reais, ou com diferenca sem justificativa, mantenha aceite pendente. Nao invente saidas SQL ou economia.

## 7. Encerrar

Resuma problema, mudanca, evidencia e proximo passo. Separe custo observado de estimativa com moeda/periodo. Com aprovacao especifica, remova somente os recursos exclusivos inventariados; preserve backups autorizados, compartilhados e fonte eventualmente entregue ao Lab 3. Nao apague por prefixo de nome. Confira remanescentes e custo residual; se mantiver algo, registre proprietario e prazo em local privado aprovado.