# Lab 1 Java - Prompts

Use um bloco por vez no Copilot Chat, com uma copia nova dos [fontes incluidos](modernizacao-java/README.md) aberta. Preserve `modernizacao-java/legado` (H2 v2, Java 8 / Boot 1.5.22.RELEASE) e use `modernizacao-java/modernizado` somente como referencia final (Java 21 / Boot 4.0.9, Jackson Databind 3.1.7 observado historicamente). Nao forneca segredos ao chat. Revise cada permissao; nao gere um legado substituto. Os fontes e scripts foram apenas validados estaticamente nesta publicacao.

## Inventario e assessment

```text
Leia somente a aplicacao Java recebida e suas instrucoes. Nao altere arquivos,
execute comandos, instale ou conecte servicos. Se faltarem fontes, pare.
Identifique JDK, framework, dependencias, endpoints, persistencia, configuracao,
testes e launchers. Nao reproduza segredos. Diferencie fatos e hipoteses.
Analise riscos da atualizacao para Java 21 e Spring Boot compativel: APIs,
javax/jakarta, JPA, serializacao JSON/datas, seguranca e configuracao.
Mostre arquivo, evidencia, impacto e teste de cada risco. Nao declare sucesso.
```

## Plano

```text
Use o assessment revisado; se ausente, solicite-o. Nao execute nem altere nada.
Planeje baseline local, preservacao do original, incrementos pequenos em copia,
versoes com suporte a confirmar em fonte oficial datada, testes e retorno.
Separe compilar, testar, iniciar e validar o fluxo. H2 nao certifica Oracle.
Planeje App Service Linux com uma instancia, sem criar recursos. Exija runtime
regional compativel, identidade/grupo ou IP autorizado com HTTPS antes de deploy,
quota, custo/limites e limpeza. Aguarde aprovacao antes de implementar.
```

## Baseline e implementacao

```text
Primeiro apenas leia e proponha comandos sanitizados da entrega para baseline.
Nao execute ate revisao e aprovacao especifica. Confirme copia independente,
JDK/Maven coerentes, ambiente local sintetico e loopback. Nao conecte Oracle.
Leia build.ps1 e maven-local.xml: -s e -gs sao explicitos; confirme configuracao
organizacional aprovada e caminhos JDK/Maven fornecidos pelo participante.
Use build legado com JDK8; no final JDK21 e -Goal verify. Nao pule testes.
Use start.ps1 -Port 18081 (legado) ou -Port 18082 (final); ambos tem padrao 18081.
Aguarde Started ContosoRhApplication em runtime/stdout.log; PID nao e prontidao.
Confirme GET /api/ferias/funcionario/1 vazio, bootstrap.ps1 na mesma porta e GET:
id=1 funcionarioId=1 dataInicio=1798761600000 dataFim=1799625600000 dias=10
status=SOLICITADA, datas numericas. Execute stop.ps1 mesmo se bootstrap falhar.
Nao use Tickets como baseline legado nem trate resultados historicos como novos.
Depois de cada autorizacao, execute somente o comando aprovado, registre codigo
de saida, testes/falhas e resultado observado. Pare em erro. Preserve original.
Implemente somente um incremento aprovado na copia e revise o diff. Compile,
rode testes e compare o mesmo fluxo/fixture antes e depois; nao suprima testes.
Nao exponha segredos, invente saidas ou atribua ao participante um build fornecido.
Nao crie recursos nem publique automaticamente.
```

## Revisao de deploy e encerramento

```text
Leia o registro sanitizado fornecido, sem comandos ou conexoes iniciais.
Confira se artefato local e testes sustentam deploy em App Service Linux.
Liste gates ausentes de runtime, acesso restrito, quota, custo e propriedade.
Sem qualquer gate, bloqueie deploy. Proponha operacoes com alvos exatos para
revisao separada, nunca aprove criacao ou exclusao por nome parecido.
Compare baseline, modernizado local e Azure somente com evidencias reais.
Resuma diferencas e limites; nao declare economia ou seguranca integral.
Limpeza exige aprovacao especifica e deve preservar recursos compartilhados.
```