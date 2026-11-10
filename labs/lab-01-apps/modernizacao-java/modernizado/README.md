# Referencia final modernizada

Java 21 / Spring Boot 4.0.9, Jackson Databind 3.1.7 observado historicamente
(gerenciado pelo parent, nao fixado diretamente no POM), H2 em memoria.
Somente fontes e testes; nenhum JAR ou biblioteca esta incluido.
Use JDK 21 completo e Maven 3.9.x. O launcher mantem datas JSON numericas
com `spring.jackson.use-jackson2-defaults=true`.

Siga a [operacao local completa](../README.md): copie esta pasta para um destino
novo, execute `build.ps1 -Goal verify` com os caminhos JDK21/Maven fornecidos
por voce, inicie por `start.ps1 -Port 18082`, aguarde Started, confira GET vazio,
execute `bootstrap.ps1 -Port 18082`, compare o GET com a fixture e use `stop.ps1`.
A porta padrao do script e 18081; especifique 18082 para comparar as variantes.
`-Goal test` nao gera o JAR do launcher. Os 18 testes historicos nao foram
reexecutados aqui. Referencia fornecida nao comprova modernizacao pelo participante.

[Escopo de uso](../AVISO-DE-USO.md).