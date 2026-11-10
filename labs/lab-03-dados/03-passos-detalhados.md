# Lab 3 Fabric - Onze passos

## 1. Conhecer o cenario

Leia o [setup](SETUP-CLIENTE.md) e o [dataset](dados-contoso-rh/README.md). Core, Folha e Reembolsos se relacionam pela matricula; aprovacoes tambem usa ReembolsoId. A rota e CSV + Notebook, sem dependencia do Lab 2. Criterio: reconhecer as dez tabelas e suas chaves, e obter LAB03_DATASET_OK no validador local.

## 2. Preparar workspace

No portal Fabric autorizado, abra Workspaces > New workspace, informe Contoso-RH-Lab03 e associe a capacidade ja aprovada. Confirme modo de licenca e regiao com o administrador. Nao ative trial nem compre capacidade como atalho. Se usar workspace existente autorizado, confirme isolamento e propriedade. Criterio: workspace acessivel e capaz de executar itens Fabric, nao apenas Power BI.

## 3. Criar Lakehouse dedicado

No workspace, escolha New item > Lakehouse, nome ContosoRH_Lakehouse. Habilite suporte a schemas conforme a interface atual. Observe areas Files e Tables. Use destino vazio dedicado, nunca compartilhado. Planeje bronze, silver e gold. Criterio: Lakehouse correto, vazio e associado a capacidade autorizada.

## 4. Escolher ingestao

Use os CSVs desta entrega. Mirroring replica uma origem existente e nao faz a transformacao Silver/Gold. Nao e necessario nem provisionado por este roteiro. Se desejar estudar a alternativa, avalie separadamente elegibilidade da origem, mecanismo de replicacao/CDC requerido, identidade, custo, rede e suporte atual. Nao presuma suporte direto a SQL Server 2014 ou bancos PostgreSQL. Sem ambiente e procedimento aprovados, mantenha CSV.

## 5. Confirmar isolamento antes da carga

Confira workspace, Lakehouse padrao e tabelas existentes. Se encontrar dados, espelhos ou shortcuts, pare para revisao; nao execute overwrite. Uma rota de bancos externos exige outro procedimento autorizado e nao deve receber o notebook de seed indiscriminadamente. Criterio da rota principal: Lakehouse dedicado e permissao de carga confirmados.

## 6. Upload dos arquivos

No Lakehouse, area Files > Upload files, envie os dez CSVs da pasta dados-contoso-rh. Coloque-os diretamente em Files, sem subpasta. Nao envie README. Confira os dez nomes e pre-visualize colunas, separador e acentos. Criterio: dez CSVs UTF-8 no caminho esperado pelo notebook.

## 7. Importar e revisar notebook

Importe [ContosoRH_Notebook.ipynb](notebooks/ContosoRH_Notebook.ipynb) no workspace e anexe ContosoRH_Lakehouse como Lakehouse padrao. Nao e preciso fazer Load to Tables manualmente: o notebook cria schemas e tabelas.

Revise as seis celulas: apresentacao; configuracao/schemas; carga Bronze; limpeza Silver; materializacao Gold; validacao. A configuracao usa Files. As gravacoes usam overwrite e overwriteSchema; confirme novamente destino e aprovacao antes de executar. Criterio: notebook e caminhos revisados, sem afetar dados de outras pessoas.

## 8. Executar Bronze, Silver e Gold

Execute as celulas de codigo em ordem, uma de cada vez, apenas no Lakehouse exclusivo aprovado. Nao execute celula posterior se a anterior falhar.

1. Configuracao: define tipos, contagens e chaves e cria os schemas.
2. Bronze: le dez arquivos, valida contagens, adiciona metadados de ingestao e grava tabelas Delta. Espere LAB03_BRONZE_OK.
3. Silver: remove metadados, aplica trim, trata duplicatas/chaves e verifica referencias. Espere LAB03_SILVER_OK. CPF e Salario continuam presentes aqui.
4. Gold: cria gold.dim_colaborador, gold.dim_tempo e gold.fact_folha_pagamento. A dimensao de colaborador omite CPF e Salario cadastral; a fato mantem valores de folha.
5. Validacao: confira os dez checks, contagens 12/2/14, unicidade, nulos, referencias e tipos monetarios double. Espere LAB03_GOLD_VALIDATION_OK. Nao fabrique o marcador em caso de falha.

Guarde esperado/observado e versao/corte de forma sanitizada em local privado aprovado. Contagens nao cobrem toda qualidade. Os doubles sao escolha didatica para compatibilidade do grafo, nao recomendacao de precisao financeira para producao. Nao copie codigo de referencia alternativo sobre o notebook validado sem revisar diferencas.

## 9. Modelo semantico e relatorio

1. No Lakehouse, selecione New semantic model, nome ContosoRH_SemanticModel, com apenas as tres tabelas gold.
2. Na Model view, relacione fact_folha_pagamento.colaborador_id a dim_colaborador.colaborador_id, muitos-para-um.
3. Relacione fact_folha_pagamento.ano_mes a dim_tempo.ano_mes, muitos-para-um. Use filtro simples das dimensoes para a fato; evite ambiguidade bidirecional.
4. Crie medidas, manualmente ou com Copilot aprovado:

```dax
Colaboradores = DISTINCTCOUNT(dim_colaborador[colaborador_id])
Folha Liquida = SUM(fact_folha_pagamento[salario_liquido])
```

5. Crie relatorio a partir do modelo: contagem de colaboradores por Pais e folha liquida por ano_mes, com filtros visiveis. Nao chame soma de moedas nao confirmadas de total BRL.
6. Salve como ContosoRH_Report. Compare os numeros filtrados com consulta da Gold no mesmo corte. Revise permissoes antes de compartilhar.

Sem Copilot, a rota manual continua valida. Criterio: relacoes corretas e relatorio reconciliado, nao apenas item criado.

## 10. Ontology e Data Agent opcionais

Confirme suporte vigente, licencas, regiao, preview, tenant settings e permissoes. Menus podem mudar; ausencia do recurso e bloqueio, nao autorizacao para mudar o tenant. Se nao disponivel, marque esta etapa como nao executada e siga ao encerramento.

### Criar ontologia

1. New item > Ontology (preview), nome ContosoRH_Ontology.
2. Add entity type > Colaborador > Bind data > Lakehouse table > ContosoRH_Lakehouse > gold.dim_colaborador. Defina chave colaborador_id, mapeie propriedades e salve. Nao inclua CPF/Salario cadastral de Bronze/Silver.
3. Repita para DataPagamento, tabela gold.dim_tempo, chave string ano_mes; e FolhaPagamento, tabela gold.fact_folha_pagamento, chave folha_id.
4. Confira tabelas gerenciadas, chaves nao nulas/unicas e tipos suportados pela oferta atual. O notebook entrega double nos valores de folha; revise suporte antes de alterar tipos.
5. Em FolhaPagamento, Add relationship: pertence_a para Colaborador. Mapping table gold.fact_folha_pagamento: origem folha_id e destino colaborador_id.
6. Crie ocorre_em de FolhaPagamento para DataPagamento, mesma mapping table: origem folha_id, destino ano_mes. As correspondencias precisam usar as chaves das entidades.
7. Em Instances/Overview, confira as linhas. No Relationship graph, expanda e execute consulta padrao. Filtre Colaborador.Pais = Brasil no Query builder e confira o resultado. Se mudar a origem, atualize o graph model antes de comparar.

### Criar Data Agent

1. New item > Data agent, nome ContosoRH_OntologyAgent, se a oferta atual suportar esta fonte.
2. Add a data source > ContosoRH_Ontology. Confira as tres entidades e acesso autorizado a fonte.
3. Aplique as instrucoes de [prompts](04-prompts-copilot-e-data-agent.md), somente leitura, sem PII cadastral ou acao automatica.
4. Teste contagem de colaboradores e folha por competencia contra a Gold, com filtros/corte/unidade registrados. Inspecione fonte e trace sanitizado, nao apenas fluencia.
5. Teste acesso negado com identidade de teste aprovada. Prompt nao substitui RBAC na ferramenta/fonte.
6. Publique/compartilhe somente para grupo autorizado apos validar. Registre versao e escopo em local privado. Criar Gold nao publica agente automaticamente.

Fonte indisponivel, permissao negada ou resposta sem evidencia ficam bloqueadas, nunca PASS. Fabric Data Agent nao e Foundry Agent. Para Lab 4, forneca handoff autorizado com schema real, chaves, corte, filtros, moeda, referencia numerica e identidade/permissoes; nao publique IDs privados aqui.

## 11. Validar, relatar e encerrar

Relate camadas, checks, relacoes, relatorio e opcionais realmente executados. Nao declare integracao ou beneficio financeiro sem evidencia. Preserve resultados sanitizados com acesso/retencao aprovados.

Pare a sessao Spark. Com aprovacao especifica, exclua agente/ontologia, relatorio/modelo e notebook/Lakehouse exclusivos; workspace apenas se exclusivo e dispensavel. Preserve fonte autorizada do Lab 4 quando combinado, com responsavel/custo/prazo. Nunca exclua, pause ou redimensione capacidade compartilhada.