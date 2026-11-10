# Preparacao do participante

## 1. Obter e abrir o material

Este repositorio e publico; use a branch main. A leitura e o download nao exigem convite ou autenticacao. Baixe o ZIP pelo menu Code e extraia-o antes de abrir a pasta no VS Code, ou clone com Git. Nao trabalhe dentro do ZIP. Nao envie suas credenciais ao chat.

Leia o [indice](README.md) e o setup do lab escolhido. Esta edicao consolida os procedimentos de participantes em Markdown; imagens e anexos nao sao necessarios para seguir os passos. O Lab 1 inclui fontes Java, testes e scripts, mas nao binarios Java/.NET, engines externos, bibliotecas ou instaladores embarcados. As dependencias H2/Spring sao resolvidas separadamente pelo Maven.

## 2. Base local

Use computador e navegador atualizados, com software aprovado pela TI. Windows 11 e PowerShell sao a referencia dos exemplos Java; Fabric e Foundry usam principalmente o navegador. Confirme suporte da ferramenta e arquitetura x64/ARM64 antes de instalar. Reserve espaco e memoria para sua trilha, sem presumir que toda maquina pode executar banco e IDE simultaneamente.

| Ferramenta | Quando usar | Origem oficial |
| --- | --- | --- |
| VS Code e GitHub Copilot | Leitura e assistencia nos roteiros; conta e entitlement autorizados | https://code.visualstudio.com/ e https://docs.github.com/copilot |
| Git | Clone e controle de mudancas, opcional para quem usa ZIP | https://git-scm.com/downloads |
| Node.js LTS com suporte, 22+ | Validador local dos CSVs do Lab 3 | https://nodejs.org/en/download |
| JDK 8 e 21; Maven 3.9.x | Lab 1, build local dos fontes incluidos | https://learn.microsoft.com/java/openjdk/ e https://maven.apache.org/download.cgi |
| Cliente SQL atual com suporte a GO | Lab 2, conforme a instancia autorizada | https://learn.microsoft.com/sql/tools/overview-sql-tools |
| Azure CLI | Apenas se o procedimento Azure aprovado exigir | https://learn.microsoft.com/cli/azure/install-azure-cli |

JDK 8 para legado pode exigir outra distribuicao aprovada e termos proprios. Nao instale versoes sem suporte ou aceite licencas automaticamente. Nao e necessario Docker para a rota Java App Service nem para fazer assessment SQL por leitura. Os Labs 3 e 4 nao exigem Python local na rota de portal desta entrega.

## 3. Verificar as ferramentas

No terminal do VS Code, execute somente os comandos da sua trilha, um por vez:

```powershell
git --version
node --version
java -version
javac -version
mvn.cmd -version
```

No Java, compare o JDK usado por Maven, java e javac. JAVA_HOME deve apontar para a raiz do JDK, nao bin. Nao altere o PATH global sem revisao; use o terminal e o JDK corretos para cada etapa. Comando nao encontrado e um bloqueio a diagnosticar, nao prova de que a ferramenta esteja ausente do disco.

No Copilot Chat, peca: "Explique a diferenca entre compilar e executar testes em tres linhas. Nao execute ferramentas nem altere arquivos." Uma resposta do Chat nao comprova acesso a todos os agentes especializados; confirme-os separadamente se sua trilha os utilizar.

## 4. Acesso, custos e parada

Obtenha do responsavel pelo ambiente, por canal seguro: conta e escopo autorizados, regiao, permissoes minimas, quota, fonte de preco vigente, moeda/periodo, limite de uso, responsavel e prazo de limpeza. Nao registre valores privados neste repositorio. Licenca individual e capacidade de servico sao requisitos distintos.

Nao use producao nem dados pessoais reais. Nao compartilhe telas com credenciais, identificadores privados, CPF ou remuneracao individual. Revise os efeitos antes de cada escrita, carga, provisionamento ou exclusao. Preserve recursos compartilhados. Pare se faltar propriedade comprovada do alvo, autorizacao, quota, suporte ou controle de custo.

## 5. Escolher a trilha

- [Lab 1 Java](labs/lab-01-apps/README.md): fontes legado H2 v2 e final, testes e scripts; build local necessario.
- [Lab 2 SQL](labs/lab-02-infra/modernizacao-sqlserver/README.md): assessment por leitura ou instancia local autorizada.
- [Lab 3 Fabric](labs/lab-03-dados/README.md): dez CSVs e notebook; independente do Lab 2.
- [Lab 4 Foundry](labs/lab-04-ia/README.md): fonte sintetica independente ou integracao Fabric condicional.

Registre cada etapa como pendente, executada com evidencia, falhou ou bloqueada. Build, teste, inicializacao e deploy sao verificacoes diferentes; um validador local nao comprova acesso ou runtime cloud.