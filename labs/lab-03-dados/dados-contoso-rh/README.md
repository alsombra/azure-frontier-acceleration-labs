# Dataset sintetico Contoso RH

Todos os nomes, documentos, matriculas, comentarios e valores sao ficticios para treinamento, nao informacoes de pessoas reais nem politica trabalhista/fiscal. Nao use esses valores como regras de calculo de folha. Os tres modulos representam origens logicas, nao conexoes a bancos existentes.

| Arquivo | Linhas | Chave | Conteudo |
| --- | --- | --- | --- |
| [colaboradores.csv](colaboradores.csv) | 12 | Matricula | Cadastro, pais, cargo, datas, CPF e salario sinteticos |
| [ferias.csv](ferias.csv) | 12 | FeriaId | Periodos, saldo e status |
| [tickets.csv](tickets.csv) | 12 | TicketId | Categoria, prioridade e datas |
| [beneficios.csv](beneficios.csv) | 13 | BeneficioId | Tipo, valor e ativacao |
| [holerites.csv](holerites.csv) | 14 | HoleriteId | Competencia, bruto, descontos, liquido e pagamento |
| [irrf.csv](irrf.csv) | 12 | IrrfId | Base, aliquota, retencao e deducao |
| [encargos.csv](encargos.csv) | 14 | EncargoId | Tipo, competencia, base e valor |
| [provisoes.csv](provisoes.csv) | 13 | ProvisaoId | Tipo, competencia e acumulado |
| [reembolsos.csv](reembolsos.csv) | 13 | ReembolsoId | Categoria, moeda, valor e status |
| [aprovacoes.csv](aprovacoes.csv) | 12 | AprovacaoId | Reembolso, aprovador, decisao e data |

Matricula relaciona as tabelas ao cadastro. Aprovacoes liga-se por ReembolsoId a reembolsos e por AprovadorMatricula a colaboradores. Campos vazios podem representar datas/decisoes ainda ausentes. Preserve UTF-8 e nomes de coluna; nao abra e salve em ferramenta que altere documentos ou datas automaticamente.

Carregue somente os dez CSVs na raiz Files do Lakehouse dedicado. Nao carregue este README nem crie Files/seed sem ajustar conscientemente a configuracao. O [notebook](../notebooks/ContosoRH_Notebook.ipynb) usa schemas explicitos e sobrescreve as tabelas. A Gold esperada tem dimensoes de 12 colaboradores e 2 periodos, e fato de 14 linhas. Essas contagens sao expectativas ate observadas no Fabric.

Os reembolsos possuem Moeda; a folha nao traz coluna de moeda. Nao agregue moedas diferentes como se fossem uma so nem declare unidade monetaria nao confirmada. Para o Lab 4, documente unidade, filtros e referencia aprovada, ou use a fixture independente em BRL.

Validacao local: node labs/lab-03-dados/scripts/validate-dataset.js, a partir da raiz do repositorio. Nao executa Spark. Veja o [passo a passo](../03-passos-detalhados.md).