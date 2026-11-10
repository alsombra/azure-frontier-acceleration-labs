# Lab 1 Java - Consulta rapida

| Etapa | Criterio de avanco |
| --- | --- |
| Setup | Fontes H2 v2/final incluidos; copia nova preservada; JDK8/JDK21 e Maven 3.9.x coerentes |
| Baseline | Build, testes e fluxo local registrados separadamente com fixture sintetica |
| Assessment | Achados ligados a arquivos e testes, sem alteracoes |
| Plano | Incrementos, versoes suportadas, retorno e aceite aprovados |
| Modernizacao | Diffs revisados; testes e contrato comparados a cada incremento |
| App Service | Artefato testado; runtime/identidade/rede/custo aprovados; recusa de acesso comprovada |
| Encerramento | Evidencias sanitizadas; processos parados; exclusao exclusiva confirmada |

Os [comandos completos](modernizacao-java/README.md) usam PowerShell 5.1, caminhos JDK/Maven fornecidos pelo participante e `maven-local.xml` explicito (usuario/global); adapte mirror/proxy somente com configuracao aprovada. Legado em `modernizacao-java/legado`, final em `modernizacao-java/modernizado`; nao ha binarios.

Build legado: `build.ps1 -JavaHome '<raiz-do-JDK8>' -MavenCommand '<caminho-do-mvn.cmd>'`; final: JDK21 com `-Goal verify`. Testes em `target/surefire-reports`; `-Goal test` no moderno nao gera JAR.

Start legado com `-Port 18081`, final com `-Port 18082` e JDK correspondente. Aguarde `Started ContosoRhApplication` em `runtime/stdout.log`; ambos os launchers usam 18081 por padrao. GET `/api/ferias/funcionario/1` deve ser `[]`; bootstrap na mesma porta; GET deve retornar id 1, funcionarioId 1, dataInicio 1798761600000, dataFim 1799625600000, dias 10, status SOLICITADA (datas numericas). `stop.ps1` mesmo em falha; reiniciar descarta H2 e reinicia a fixture.

Java 8 / Boot 1.5.22.RELEASE no baseline; Java 21 / Boot 4.0.9 / Jackson Databind 3.1.7 observado historicamente no final. Nenhum build/runtime foi executado nesta publicacao; 12/18 testes sao resultados historicos. Tickets tem falha H2 conhecida no legado. H2 nao certifica Oracle; health nao e teste funcional. Plano dedicado pode cobrar com app parada. Nao publique escrita anonima ou console H2.

[Roteiro completo](03-guia-completo.md).