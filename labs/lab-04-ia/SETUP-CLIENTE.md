# Lab 4 Foundry - Setup

Conclua a [preparacao comum](../../PREPARACAO.md). Nao e necessario Python para a rota de portal desta entrega. Nao use dados reais, endpoints privados ou credenciais no chat/repositorio.

## Antes de qualquer servico

1. Obtenha aprovacao explicita da rota, escopo e consumo. Nomes didaticos nao autorizam criacao.
2. Confirme conta/identidade, assinatura, projeto/recurso e se o RG e exclusivo ou compartilhado. Defina proprietario, janela e prazo de encerramento por canal privado seguro.
3. Confirme modelo/deployment/versao, disponibilidade, regiao, quota e compatibilidade da ferramenta na documentacao oficial datada e no ambiente autorizado. Nao presuma gratuidade ou uma SKU fixa.
4. Revise licencas, tenant settings, capacidade quando aplicavel, residencia/cross-geo e aceite de preview com o administrador.
5. Confirme menor privilegio para UI, inferencia, fonte, agente/connection e deployment. Acesso a UI nao comprova todas as permissoes.
6. Aprove custo estimado com moeda/periodo, limite de gasto, tokens de entrada/saida, numero de chamadas, tempo e regra de parada. Quota nao e autorizacao de custo; orcamento com alerta nao e teto automatico.

## Escolher a fonte

**Independente:** confira a fixture lab04-rh-v1 do [guia](02-guia-completo.md). Exige ferramenta de recuperacao/RAG compativel e autorizada, ingestao, permissao de leitura e citacao verificadas. Colar dados em instrucao ou fazer upload nao comprova recuperacao. Sem ferramenta, e apenas simulacao identificada.

**Fabric:** receba tabelas Gold efetivas, schema/chaves/tipos, corte/versao, filtros, moeda, referencias numericas e identidade/permissoes. Gold validada nao implica Data Agent publicado nem conexao Foundry. Confirme suporte vigente da integracao, idiomas, preview, regiao, tenant/capacidade/licenca e propagacao da identidade. Nao presuma suporte a service principal ou passthrough. Sem acesso real por fonte, bloqueie resposta factual.

Fabric Data Agent, Foundry Agent e modelo semantico sao componentes distintos. Nao use a fixture independente para declarar Fabric conectado.

## Autenticacao e privacidade

Use login oficial no portal autorizado, nunca senha/token/codigo de login no chat. Configuracao sensivel fica em sessao ou cofre aprovado. Nao publique .env, endpoints/IDs privados, segredos ou traces com dados pessoais.

Nao eleve permissoes para contornar negacao. Prompt nao implementa RBAC; configure autorizacoes na fonte/ferramenta e teste recusa com identidade de teste aprovada.

## Avanco e encerramento

Sem qualquer gate, pare antes do servico real. Os [casos](evaluation/cases.json) podem ser lidos localmente, mas isso nao executa agente. Registre avaliacoes em local privado aprovado com acesso/retencao definidos.

Limpeza exige aprovacao explicita dos itens temporarios exatos. Nao exclua recursos compartilhados ou fonte Fabric do Lab 3, nem pause capacidade compartilhada. Se preservar, registre handoff, custo, responsavel e prazo.