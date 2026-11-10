# Lab 1 Java - Passo a passo

## 1. Abrir e conhecer o cenario

Leia o [setup](SETUP-CLIENTE.md). A Contoso RH e ficticia. O exercicio consiste em entender uma aplicacao legada, preservar seu comportamento e planejar uma atualizacao para Java 21 e uma versao Spring Boot com suporte e compatibilidade confirmados. O banco corporativo nao faz parte desta modernizacao.

Este repositorio entrega [fontes e scripts de operacao](modernizacao-java/README.md), nao JARs: baseline H2 v2 em `modernizacao-java/legado` e referencia final em `modernizacao-java/modernizado`. Execute somente em copia nova e com autorizacao especifica. A preparacao desta publicacao nao executou Java/Maven, testes ou runtime.

Na pasta da entrega, identifique pom.xml, configuracoes, controladores, persistencia, testes e instrucoes operacionais. Nao inicie a configuracao Oracle original. Registre versoes declaradas e diferencie o que foi lido do que foi executado.

## 2. Registrar baseline local

1. Preserve original e copia de trabalho. Use JDK 8 completo para legado e JDK 21 completo para final, Maven 3.9.x e PowerShell 5.1. Informe seus caminhos pelos parametros `-JavaHome '<raiz-do-JDK8>'` ou `'<raiz-do-JDK21>'` e `-MavenCommand '<caminho-do-mvn.cmd>'`.
2. Siga os [comandos completos de build e testes](modernizacao-java/README.md). Legado usa `build.ps1` (`clean package`); moderno usa `build.ps1 -Goal verify`. Registre saida e totais reais em `target/surefire-reports`; nao confunda os 12/18 testes historicos com uma nova execucao.
3. Use `start.ps1` na copia, com JDK correto e `-Port 18081` no legado ou `-Port 18082` no moderno. Ambos tem padrao 18081. O script retorna antes de estar pronto; aguarde `Started ContosoRhApplication` em `runtime/stdout.log`. Em falha consulte stderr e pare pelo script.
4. Confira GET `http://127.0.0.1:18081/api/ferias/funcionario/1` igual a `[]` (18082 no moderno). Execute `bootstrap.ps1 -Port 18081` ou `-Port 18082` na mesma copia. Bootstrap faz GET vazio -> POST -> GET e recusa duplicacao.
5. Repita GET e compare um registro: `id=1`, `funcionarioId=1`, `dataInicio=1798761600000`, `dataFim=1799625600000`, `dias=10`, `status=SOLICITADA`. Datas devem ser numeros epoch milliseconds, nao strings ISO. O bootstrap legado nao valida todos os campos; confira-os no GET. Nao ha tela web de colaboradores.
6. Execute `stop.ps1` mesmo se bootstrap falhar. H2 e descartado ao parar; para repetir, start -> aguardar Started -> GET vazio -> bootstrap. Nao apague registro PID nem encerre processos de terceiros. Tickets tem falha H2 conhecida no legado; use ferias como baseline.

Guarde resultados sanitizados em local privado aprovado, nao neste repositorio. Zero testes nao comprova o fluxo funcional; um health check nao comprova a consulta.

## 3. Assessment por leitura

Use os [prompts](07-prompts-do-agente.md). Identifique incompatibilidades Java/Spring, javax/jakarta, persistencia, APIs web, serializacao, seguranca, configuracao e dependencias. Relacione achado, arquivo, impacto e teste. Nao atualize versoes nessa etapa e nao conecte a servicos externos automaticamente.

## 4. Planejar

Revise versoes com suporte e compatibilidade atuais em fontes oficiais datadas. Ordene incrementos por dependencia; defina criterio de aceite, retorno e testes de contrato. Planeje o App Service separadamente, sem provisionar. Nao assuma que um JDK ou framework disponivel localmente existe no runtime regional do servico.

## 5. Modernizar uma copia

1. Obtenha aprovacao do plano e do primeiro incremento.
2. Faca uma mudanca pequena na copia, preserve o baseline e revise o diff.
3. Compile e execute os testes com o JDK da etapa. Pare e investigue falhas sem suprimir testes para produzir verde.
4. Inicie o artefato novo localmente com a mesma fixture e repita o contrato do baseline.
5. Compare nomes e tipos JSON, datas/timezone, valores numericos, status HTTP, erros e efeitos de escrita. Explique diferencas aprovadas; nao conclua equivalencia por HTTP 200.
6. Repita por incremento aprovado. Registre versao/hash do artefato final e limites do que foi testado.

A referencia final incluida e fonte Java 21 / Boot 4.0.9, nao checkpoint binario. Jackson Databind 3.1.7 foi observado no classpath historico e e gerenciado pelo parent; confirme a resolucao no seu build autorizado. O launcher moderno preserva datas numericas com `spring.jackson.use-jackson2-defaults=true`. Copiar esses fontes nao comprova modernizacao feita por voce.

## 6. Deploy App Service, somente apos gates

1. Confirme todos os requisitos Azure do setup, inclusive acesso restrito, custo, quota e runtime compativel.
2. Revise previamente comandos ou operacoes no portal com alvos exatos. Crie apenas plano Linux dedicado, uma instancia, e Web App aprovados. Nomes de exemplo nao autorizam criacao.
3. Configure HTTPS e a barreira Entra/grupo ou IP aprovada. Teste recusa de acesso nao autorizado antes de enviar a aplicacao.
4. Publique somente o artefato modernizado testado, pelo metodo suportado e aprovado. Nao publique o legado vulneravel como entrega modernizada.
5. Use a URL HTTPS efetivamente retornada pelo servico. Comprove runtime e versao/hash efetivos e repita o mesmo teste funcional autorizado.
6. Compare legado local, modernizado local e Azure. Sem as tres evidencias, registre o limite em vez de declarar entrega completa.

Nao ha script de provisionamento nesta entrega; gere/revise comandos apenas quando houver aplicacao e ambiente aprovados. Nao execute comandos Azure de outros roteiros para contornar os gates.

## 7. Encerrar

Registre o que mudou, provas antes/depois, falhas e pendencias. Ganhos de manutencao, desempenho, seguranca ou custo sao hipoteses a medir, nao consequencia garantida de atualizar versoes.

Pare processos locais. Com aprovacao especifica, exclua somente Web App, plano e demais recursos exclusivos inventariados. Parar ou excluir apenas a app pode manter cobranca do plano. Confirme exclusao e recursos remanescentes; preserve compartilhados e registre custo residual, responsavel e prazo quando houver retencao.