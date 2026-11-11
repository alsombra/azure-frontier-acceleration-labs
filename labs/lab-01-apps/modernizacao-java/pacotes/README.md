# Lab 1 - Pacotes Java de fontes

Pacotes disponibilizados neste repositorio cliente por autorizacao expressa
da organizadora em 11/11/2026. Aplicam-se os limites do
[aviso de uso](../AVISO-DE-USO.md). Esta autorizacao de envio nao e uma
licenca open source nem certifica direitos ou conformidade de terceiros.

| Etapa | Pacote | Integridade |
| --- | --- | --- |
| Legado H2 v2: Java 8 / Spring Boot 1.5.22 | [Fontes ZIP](contoso-rh-legado-candidato-fontes-h2-v2.zip) | [SHA256](contoso-rh-legado-candidato-fontes-h2-v2.zip.sha256) |
| Referencia modernizada: Java 21 / Spring Boot 4.0.9 | [Fontes ZIP](contoso-rh-modernizado-fontes.zip) | [SHA256](contoso-rh-modernizado-fontes.zip.sha256) |

## Uso local

1. Baixe o ZIP da etapa e seu SHA256. Compare o resultado de
   `Get-FileHash -Algorithm SHA256 <arquivo.zip>` com o checksum fornecido.
2. Extraia em uma pasta nova. Preserve o legado e trabalhe em outra copia.
3. Abra a pasta extraida no VS Code e leia seu README antes dos scripts.
4. Use JDK 8 completo para o legado, JDK 21 para o modernizado e Maven 3.9.x.
   Informe caminhos reais nos parametros dos scripts fornecidos.
5. Execute build, start, aguarde `Started ContosoRhApplication`, execute
   bootstrap, confira o GET de ferias e encerre com stop, conforme o README
   do pacote. Use portas distintas para as duas variantes.

Estes ZIPs sao fontes, nao compilados. Nao incluem JDK, Maven ou bibliotecas
incorporadas; o build pode baixar dependencias sob seus proprios termos.
Os arquivos fechados e seus hashes foram preservados. Avisos e registros
internos aos ZIPs retratam sua preparacao anterior, inclusive referencias
a nao publicacao naquela data; nao foram reescritos neste envio.

Use somente H2 descartavel, dados sinteticos e acesso local. O legado tem
falhas didaticas intencionais; nao use em producao nem conecte banco real.
O checkpoint modernizado fornecido nao e modernizacao feita pelo participante.
Esta entrega nao comprova novo build, testes, runtime ou deploy Azure.
Compilados e materiais internos nao fazem parte desta publicacao.