# Azure Day - Frontier Acceleration Labs

Material de participantes: Lab 1 Java, Lab 2 SQL Server, Lab 3 Microsoft Fabric e Lab 4 Microsoft Foundry. Use apenas ambientes de treinamento autorizados e dados sinteticos.

## Disponibilidade

Esta edicao de materiais de clientes e publica, com autorizacao da organizacao. Consulte o [manual de preparacao](Manual-Setup-Cliente.md) ou o [manual em PDF](Manual-Setup-Cliente.pdf). A disponibilidade publica nao cria uma licenca open source; os termos de terceiros continuam aplicaveis.

O Lab 1 inclui [fontes Java e testes](labs/lab-01-apps/modernizacao-java/README.md): baseline legado H2 v2 (JDK 8 / Boot 1.5.22.RELEASE) e referencia final (JDK 21 / Boot 4.0.9 / Jackson Databind 3.1.7 observado historicamente). Nao inclui binarios, dependencias, JDK ou Maven. O participante faz seu proprio build em copia nova, apos revisao e autorizacao. A preparacao desta publicacao foi somente estatica, sem executar Java ou comprovar runtime.

Os Labs 2, 3 e 4 dependem das ferramentas, contas, permissoes e servicos descritos nos respectivos setups. A presenca dos arquivos nao comprova execucao em Azure, Fabric ou Foundry. O Lab 3 pode usar os CSVs sem executar o Lab 2; o Lab 4 oferece uma fonte sintetica independente e uma rota Fabric condicional.

## Seguranca e custos

Nao use producao, dados reais ou credenciais compartilhadas. Nao publique senhas, tokens, endpoints privados, identificadores do ambiente ou resultados com dados pessoais. Use variaveis de sessao ou um cofre aprovado.

Antes de qualquer operacao paga, confirme ambiente, identidade, regiao, quota, permissoes minimas, custo estimado, limites de consumo e prazo de encerramento. Orcamentos com alertas nao sao tetos automaticos. Nao crie trials ou aceite termos automaticamente.

Revise comandos e sugestoes do Copilot antes de aprovar. Nao habilite aprovacao automatica. Operacoes de carga podem sobrescrever tabelas; scripts SQL incluem falhas intencionais para estudo e devem ficar em sandbox descartavel.

Ao terminar, pare sessoes e remova somente os recursos exclusivos autorizados. Nao exclua nem pause capacidades ou recursos compartilhados. Parar uma aplicacao nao necessariamente encerra a cobranca do plano ou dos recursos associados.

## Conteudo

Comece pela [preparacao comum](PREPARACAO.md). Esta edicao consolida setup, passo a passo, prompts e consulta rapida de cada lab em Markdown, sem dependencia de imagens ou anexos.

| Lab | Entrada | Setup | Passo a passo |
| --- | --- | --- | --- |
| 1 Java | [Indice Java](labs/lab-01-apps/README.md) | [Preparacao Java](labs/lab-01-apps/SETUP-CLIENTE.md) | [Roteiro Java](labs/lab-01-apps/03-guia-completo.md) |
| 2 SQL | [Indice SQL](labs/lab-02-infra/modernizacao-sqlserver/README.md) | [Preparacao SQL](labs/lab-02-infra/modernizacao-sqlserver/SETUP-CLIENTE.md) | [Roteiro SQL](labs/lab-02-infra/modernizacao-sqlserver/03-guia-completo.md) |
| 3 Fabric | [Indice Fabric](labs/lab-03-dados/README.md) | [Preparacao Fabric](labs/lab-03-dados/SETUP-CLIENTE.md) | [Roteiro Fabric](labs/lab-03-dados/03-passos-detalhados.md) |
| 4 Foundry | [Indice Foundry](labs/lab-04-ia/README.md) | [Preparacao Foundry](labs/lab-04-ia/SETUP-CLIENTE.md) | [Roteiro Foundry](labs/lab-04-ia/02-guia-completo.md) |

Os indices de cada lab tambem levam aos prompts, criterios de aceite e arquivos auxiliares. As verificacoes locais de arquivos nao executam os laboratorios nem comprovam prontidao de servicos.