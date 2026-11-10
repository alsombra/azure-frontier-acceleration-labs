# Lab 3 Fabric - Consulta rapida

| Item | Conferencia |
| --- | --- |
| Seed | Dez CSVs em Files; UTF-8; LAB03_DATASET_OK local |
| Destino | ContosoRH_Lakehouse dedicado, padrao do notebook |
| Efeito | Notebook sobrescreve Bronze/Silver/Gold; nao usar em compartilhado |
| Bronze/Silver | Dez tabelas, contagens do dataset e referencias |
| Gold | dim_colaborador 12; dim_tempo 2; fact_folha_pagamento 14 |
| Chaves | colaborador_id, ano_mes e folha_id |
| Relacoes | Fato N:1 dimensoes, filtro simples dim para fato |
| Sucesso Spark | LAB03_BRONZE_OK, LAB03_SILVER_OK, LAB03_GOLD_VALIDATION_OK reais |
| Opcionais | Copilot/Ontology/Data Agent dependem do tenant; nao automaticos |
| Cleanup | Parar Spark; excluir apenas exclusivos autorizados; preservar capacidade compartilhada |

Gold omite CPF e salario cadastral na dimensao, mas nao anonimiza dados reais. Nao invente moeda da folha. Fonte Gold pronta nao comprova agente publicado ou conexao Foundry.

[Roteiro completo](03-passos-detalhados.md).