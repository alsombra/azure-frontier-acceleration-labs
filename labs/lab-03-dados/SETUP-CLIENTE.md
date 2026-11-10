# Lab 3 Fabric - Setup

Conclua a [preparacao comum](../../PREPARACAO.md). Use somente os CSVs sinteticos entregues; nao carregue dados corporativos.

## Acesso e capacidade

1. Confirme entrada com conta autorizada em https://app.fabric.microsoft.com.
2. Obtenha licenciamento individual adequado e acesso a uma capacidade Fabric autorizada para o workspace. Power BI Pro/PPU isolado nao substitui capacidade para Lakehouse/Spark. Capacidade paga nao substitui licenca individual.
3. Confirme permissao de criar workspace ou papel Contributor/Member/Admin no workspace dedicado, de acordo com as operacoes aprovadas.
4. Confirme regiao, capacidade, proprietario, janela de uso e custo. Nao ative trial, compre ou redimensione capacidade automaticamente; termos, disponibilidade e custos exigem aprovacao.
5. Valide criacao de Lakehouse e Notebook no portal autorizado. Sem acesso/capacidade, a execucao cloud fica bloqueada; o validador local ainda pode ser usado.

## Recursos opcionais

O administrador deve confirmar tenant settings, licencas/capacidade, regiao, requisitos de residencia/cross-geo e aceite de preview para Copilot, Ontology e Data Agent. Nao altere configuracoes do tenant para contornar uma negacao.

Se Copilot nao estiver disponivel, use a rota manual. Se Ontology/Data Agent nao estiverem disponiveis ou a integracao nao for suportada, conclua ate o Passo 9 e marque o Passo 10 como nao executado. Nao prometa handoff Foundry automaticamente.

## Arquivos e cuidado com escrita

Na raiz do repositorio, com Node.js LTS aprovado:

```powershell
node labs/lab-03-dados/scripts/validate-dataset.js
```

Exija LAB03_DATASET_OK antes do upload. Confira os dez CSVs e o [notebook](notebooks/ContosoRH_Notebook.ipynb). Ele usa seed_path = Files e sobrescreve tabelas bronze, silver e gold. Use Lakehouse dedicado vazio; revise cada celula antes de executar, nunca rode sobre espelhos, shortcuts ou tabelas compartilhadas.

Bronze/Silver preservam CPF e remuneracao sinteticos. Gold remove CPF e Salario cadastral da dimensao, mas mantem nomes e valores de folha. Isso nao equivale a anonimizar dados reais. Restrinja acesso e compartilhamento, inclusive dos resultados e traces.

## Nomes e encerramento

| Item | Nome didatico |
| --- | --- |
| Workspace | Contoso-RH-Lab03 |
| Lakehouse | ContosoRH_Lakehouse |
| Notebook | ContosoRH_Notebook |
| Modelo semantico | ContosoRH_SemanticModel |
| Relatorio | ContosoRH_Report |
| Ontologia opcional | ContosoRH_Ontology |
| Data Agent opcional | ContosoRH_OntologyAgent |

Nomes nao comprovam propriedade nem autorizam criacao/exclusao. Pare a sessao Spark ao terminar. Somente com aprovacao, remova itens/workspace exclusivos; nao exclua, pause ou redimensione capacidade compartilhada. Se preservar a fonte para o Lab 4, combine acesso, custo, responsavel e prazo.