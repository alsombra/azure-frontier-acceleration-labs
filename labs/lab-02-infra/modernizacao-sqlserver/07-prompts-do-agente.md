# Lab 2 SQL - Prompts por etapa

Use um bloco por vez no Copilot. Os caminhos sao relativos a esta pasta. Os prompts nao autorizam conexoes ou escrita automaticamente. Nunca envie segredos.

## 1. Inventario e assessment

```text
Leia somente legado/01-create-contosorh-legacy-sqlserver.sql e
cli/validate-local.sql. Nao altere, execute comandos, instale ou conecte servicos.
Identifique schema, seed, rotinas, trigger, efeitos e lacunas do baseline.
Preserve o AS-IS e nao gere outro legado. Origem declarada: SQL Server 2014,
engine 12.x EOL, nivel 120. Engine recente em 120 e simulacao, nao 2014.
Avalie Unicode/collation, TEXT/NTEXT, MONEY, SQL dinamico, cursor e integridade.
Mostre arquivo/objeto, evidencia, risco, prioridade e teste, separando fatos
e hipoteses. Nao execute injecao. Comentario de avaliacao no SQL nao e resultado
observado; depreciacao e MONEY nao significam incompatibilidade automatica.
```

## 2. Plano

```text
Use o assessment revisado; nao execute, conecte ou altere nada.
Planeje bootstrap local separado, baseline real, backup/restore testavel,
copia de adaptacao, schema/carga e reconciliacao para Azure SQL Database.
O destino e PaaS continuo, nao SQL Server 2022/2025. Nao fixe compatibilidade
sem testes. Exija suporte do metodo/ferramenta para esta origem em fonte
oficial datada. Nao instalar Azure Data Studio aposentado nem presumir DMA/DMS.
Liste gates de licenca/isolamento, identidade, quota, custo, rede/TLS e limpeza.
Nao aplicar CREATE DATABASE/USE do seed diretamente no Azure SQL Database.
Defina aceite por chaves, valores, datas, busca/saldo e trigger, nao so contagens.
Pause para revisao e aprovacao especifica antes de qualquer escrita.
```

## 3. Bootstrap e execucao

```text
Primeiro somente leia e proponha comandos sanitizados do seed e baseline
existentes. Nao execute ou conecte ate aprovacao separada. Exija instancia
local exclusiva ja disponibilizada, base ausente, engine/licenca, identidade,
cliente GO, codificacao/datas, TLS e retorno. Nao instale ou inicie servicos.
O seed nao e idempotente. Se existir base ou ocorrer carga parcial, pare;
nao drope, resete ou recarregue automaticamente. Nao exponha credenciais.
Apos autorizacao especifica, execute apenas o comando aprovado, registre
saida real e pare em erro. Nao invente LOCAL_BASELINE_OK ou .bak.
Antes de adaptar/transferir, valide baseline e retorno; preserve o AS-IS.
Cada escrita/custo Azure exige alvo exclusivo, quota, permissao minima,
rede/TLS, suporte e custo aprovados. Parametrize busca e substitua cursor
somente em copia, preservando comportamento; reconcilie tipos e auditoria.
Nao executar injecao fora de fixture LOCAL isolada aprovada.
```

## 4. Comparacao e negocio

```text
Revise apenas o registro sanitizado fornecido, sem comandos ou conexoes.
Compare esperado/observado/diferenca por criterio: cinco tabelas incluindo log,
chaves/nulos/orfaos, datas, precisao monetaria, Unicode, busca, saldo (Id 1
esperado 20 no seed intacto; incluir sem ferias e inexistente), trigger e
constraints. Contagens iguais nao bastam. Confira acesso/TLS e retorno.
Sem resultados reais, registre pendente, nao sucesso. Resuma problema,
mudanca, evidencia e beneficio potencial a medir; nao prometa economia.
Proponha limpeza de alvos exclusivos comprovados com dono/prazo; nao execute
sem confirmacao separada e preserve recursos compartilhados/fonte do Lab 3.
```

[Passo a passo e gates](03-guia-completo.md).