# Lab 2 SQL - Consulta rapida

Origem: SQL Server 2014/12.x EOL, compatibilidade 120. Engine recente em 120 e simulacao. Destino: Azure SQL Database, nivel escolhido apos assessment e testes.

| Antes de | Confira |
| --- | --- |
| Assessment | Leia seed e baseline; nao altere nem conecte |
| Bootstrap | Instancia local autorizada, base ausente, GO/UTF-8/TLS, aprovacao de escrita |
| Baseline | Contexto ContosoRH_Legacy; esperado 15/25/30/40; log esperado 25 em primeira carga |
| Transferencia | Metodo suportado, backup/retorno testado, copia, alvos/custo/rede/identidade aprovados |
| Aceite | Chaves, nulos, datas, dinheiro, Unicode, busca/saldo, trigger, acesso e diferencas |
| Limpeza | Somente exclusivos com aprovacao; preservar compartilhados e backup autorizado |

LOCAL_BASELINE_OK e parcial. Seed nao e idempotente e nao pode ser aplicado inteiro no Azure SQL Database. Nao declare migracao concluida por contagem isolada. Nao use dados reais, producao ou senhas no chat.

[Passo a passo](03-guia-completo.md).