# Legado H2 v2

Baseline didatico Java 8 / Spring Boot 1.5.22.RELEASE, somente fontes e testes.
Use JDK 8 completo e Maven 3.9.x. Nao use a configuracao Oracle original.
Dependencias obsoletas, uso de sun.misc e falha conhecida de Tickets permanecem
para estudo; nao e uma aplicacao segura para producao.

Siga a [operacao local completa](../README.md): copie esta pasta para um destino
novo, execute `build.ps1` com os caminhos JDK8/Maven fornecidos por voce,
inicie por `start.ps1 -Port 18081`, aguarde Started, confira GET vazio,
execute `bootstrap.ps1 -Port 18081`, compare o GET com a fixture e use `stop.ps1`.
O build executa testes e gera o JAR/checksum exigidos pelo launcher.
H2 e descartado ao parar. Os 12 testes historicos nao foram reexecutados aqui.

[Escopo de uso](../AVISO-DE-USO.md).