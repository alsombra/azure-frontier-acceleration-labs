# Lab 3 - Microsoft Fabric e Contoso RH

Rota principal independente: dez CSVs sinteticos, notebook PySpark, camadas Bronze/Silver/Gold e modelo semantico. Nao exige executar o Lab 2 nem provisionar bancos. Ontology e Data Agent sao opcionais e sujeitos a disponibilidade, licencas, previews e politica do tenant.

1. [Setup](SETUP-CLIENTE.md).
2. [Onze passos completos](03-passos-detalhados.md).
3. [Prompts](04-prompts-copilot-e-data-agent.md).
4. [Consulta rapida](CHEAT-SHEET-CLIENTE.md).
5. [Dicionario e contagens do dataset](dados-contoso-rh/README.md).
6. [Notebook sem outputs](notebooks/ContosoRH_Notebook.ipynb).

Verificacao local, na raiz do repositorio:

```powershell
node labs/lab-03-dados/scripts/validate-dataset.js
```

Esperado: LAB03_DATASET_OK. Isso verifica arquivos e referencias do seed, nao executa Spark nem testa acesso/licenciamento Fabric.

[Voltar ao indice geral](../../README.md).