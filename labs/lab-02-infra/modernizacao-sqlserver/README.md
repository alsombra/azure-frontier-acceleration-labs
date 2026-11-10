# Lab 2 - SQL Server para Azure SQL Database

Contoso RH e um cenario ficticio. A entrega contem definicao SQL e dados sinteticos, nao um banco em execucao, backup .bak ou engine de banco. Assessment e plano podem ser realizados por leitura, sem instalar ou iniciar SQL Server.

1. [Setup](SETUP-CLIENTE.md).
2. [Passo a passo](03-guia-completo.md).
3. [Prompts por etapa](07-prompts-do-agente.md).
4. [Consulta rapida](CHEAT-SHEET-CLIENTE.md).
5. [Definicao e seed AS-IS](legado/01-create-contosorh-legacy-sqlserver.sql).
6. [Baseline parcial somente leitura](cli/validate-local.sql).

O seed inclui CPF, nomes, emails e valores **ficticios**, SQL dinamico inseguro e outras deficiencias intencionais. Nao use em producao, nao exponha a instancia e nao teste ataques fora de uma fixture local isolada aprovada.

[Voltar ao indice geral](../../../README.md).