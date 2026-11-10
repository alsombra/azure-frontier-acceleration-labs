# Lab 2 SQL - Setup

Conclua a [preparacao comum](../../../PREPARACAO.md). Nao e necessario ambiente SQL para inventario, assessment ou plano por leitura.

## Ferramentas e origem

- VS Code e Copilot com acesso autorizado; cliente SQL atual aprovado pela TI para a etapa de execucao.
- Cliente que interprete separadores GO, preserve UTF-8 e interrompa em erro. Confirme versao, autenticacao e TLS, sem enviar segredos ao chat.
- Para runtime fiel: instancia Windows SQL Server 2014, engine 12.x, legalmente licenciada e isolada, disponibilizada separadamente. SQL Server 2014 esta fora do suporte estendido regular: confirme politica, eventual ESU e termos de uso. Nao o instale automaticamente.
- Alternativa autorizada: engine recente com compatibility level 120, identificado como **simulacao**, nao SQL Server 2014. Nao existe imagem Linux oficial de SQL Server 2014.
- Uma instancia local exclusiva e banco ContosoRH_Legacy ainda ausente antes do seed. Se a base ja existir ou houver carga parcial, pare para revisao; nao drope nem resete automaticamente.

Azure Data Studio foi aposentado em 28/02/2026. Nao instale ferramentas aposentadas nem presuma suporte de extensoes antigas, DMA ou DMS. Consulte https://learn.microsoft.com/sql/tools/overview-sql-tools para a ferramenta atual. SqlPackage/BACPAC e um metodo candidato; suporte a origem, versao, limites e consistencia devem ser confirmados em documentacao oficial datada antes de uso.

## Arquivos e efeitos

[O seed](legado/01-create-contosorh-legacy-sqlserver.sql) cria banco, muda compatibilidade/recovery, cria objetos e insere dados. Nao e idempotente. Execute apenas uma vez em origem local exclusiva aprovada, nunca diretamente em Azure SQL Database: ele contem CREATE DATABASE e USE.

[O validador](cli/validate-local.sql) usa SELECT e THROW no contexto ContosoRH_Legacy; nao cria nem carrega dados. E parcial: verifica compatibilidade 120, contagens e tipo VARCHAR de Nome, nao equivalencia completa ou seguranca.

## Destino Azure e custo

Antes de qualquer criacao ou transferencia, confirme assinatura autorizada, RG/destino exclusivos, regiao, SKU/preco atuais, moeda/periodo, quota, menor privilegio, rede restrita, TLS, limite de uso e prazo de limpeza. Nao solicite Owner automaticamente. Azure SQL Database e PaaS de atualizacao continua, nao SQL Server 2022/2025; nivel de destino depende de assessment e testes.

Defina backup/retorno e teste de restauracao antes de adaptar ou transferir. Recovery FULL nao comprova backup pronto. Nao crie DMS ou outros servicos pagos sem aprovacao separada. Orcamento com alerta nao e teto de gastos.

## Avanco

Leia o [roteiro](03-guia-completo.md). Sem instancia autorizada, mantenha apenas leitura e plano. Sem metodo suportado, retorno testavel ou aprovacao de custo/acesso, nao execute migracao. Nao marque LOCAL_BASELINE_OK sem executar o validador e observar a saida.