# Frontier Acceleration Labs
## Manual de preparação do cliente

**Windows 11 x64 | Lab 01 Java | Lab 02 SQL | Lab 03 Fabric | Lab 04 Foundry**

Edição: 10 de novembro de 2026

**Escopo: preparação não comprova runtime.** Este manual não certifica execução dos labs, acesso cloud, migração ou inferência.

## Preparação por laboratório

Prepare somente os labs em que participará. As instalações abaixo não criam recursos de nuvem nem comprovam acesso, capacidade ou inferência no ambiente do participante.

| Lab | Instalar antes do evento | Acesso a confirmar |
| --- | --- | --- |
| 01 - Java | VS Code, Copilot, JDK 8 e JDK 21, Maven 3.9.x | Repositório e licença Copilot; App Service somente na etapa Azure autorizada |
| 02 - SQL | SQL Server 2022 Developer e SSMS 22 | Conta Windows na instância local CONTOSOLAB; Azure SQL somente na etapa autorizada |
| 03 - Fabric | Node.js LTS com suporte, 22+, e navegador atualizado | Conta, licença individual, capacidade e workspace Fabric autorizados |
| 04 - Foundry | Navegador atualizado; Python não é necessário na rota de portal | Projeto, modelo, quota, fonte de consulta e permissões Foundry autorizados |

Git é opcional para quem recebe ZIP. Docker não é necessário nas rotas deste manual. .NET e Lab 05 estão fora do escopo: não instale ferramentas dessas trilhas para esta edição. A rota independente do Lab 04 não exige concluir o Lab 03; a integração com Fabric é condicional.

Este manual trata da preparação, não da execução dos laboratórios. Faça as verificações locais da sua trilha, confirme os acessos com a TI e leve os bloqueios ao facilitador. Não crie recursos, não faça deploy e não execute testes de consumo cloud durante a preparação. A carga SQL da seção 4.8 é uma exceção opcional, somente com autorização expressa.

<!-- page -->

## 1. Computador e instalação básica

### 1.1. Ambiente escolhido

Use **Windows 11 x64 (Intel/AMD)** atualizado e aprovado pela organização. Confira em **Configurações > Sistema > Sobre > Tipo de sistema**. ARM64, macOS e Linux exigem outra rota combinada com a TI e o facilitador; não presuma que emulação torna o motor SQL compatível.

Como recomendação de planejamento para os quatro labs, reserve **16 GB de RAM e 60 GB livres em SSD**, além de internet para downloads. São margens práticas, não mínimos oficiais homologados. Confirme os requisitos dos produtos e espaço temporário dos instaladores. Não desinstale ferramentas nem reconfigure serviços já utilizados em outros projetos.

| Ferramenta | Quem precisa | Fonte oficial |
| --- | --- | --- |
| Navegador atualizado | Todos; Edge ou Chrome aprovado pela TI | [Microsoft Edge](https://www.microsoft.com/edge/download) ou distribuição corporativa |
| Visual Studio Code estável | Java e edição de arquivos nas demais trilhas; não é necessário para conectar pelo SSMS ou usar só o portal | [Download do VS Code](https://code.visualstudio.com/download) |
| Git | Opcional, somente se escolher clone em vez de ZIP | [Download do Git](https://git-scm.com/downloads) |
| JDK 8, JDK 21 e Maven | Somente Lab 01; detalhes na seção 3 | Fontes oficiais indicadas na seção 3 |
| SQL Server e SSMS | Somente Lab 02 | Instruções completas na seção 4 |
| Node.js LTS com suporte, versão 22 ou superior | Somente validador local do Lab 03 | [Download do Node.js](https://nodejs.org/en/download) |

Use o PowerShell aprovado pela TI para os comandos locais. Não é necessário instalar outra versão só para este manual. Azure CLI não é pré-requisito comum: instale apenas se uma rota CLI aprovada do treinamento pedir, usando a [documentação oficial](https://learn.microsoft.com/cli/azure/install-azure-cli-windows). Não são necessários Python para o portal Foundry, Spark/Hadoop locais para Fabric nem Docker para as rotas atuais.

### 1.2. Instalação, rede e privacidade

- Solicite à TI as instalações que exigem administrador do Windows. Essa permissão local não concede administração de tenant, assinatura ou serviços cloud.
- Use downloads oficiais e confira o editor/assinatura digital. Termos de licença devem ser lidos e aceitos por você ou pela organização.
- Confirme proxy, certificados e acesso aos repositórios de dependências com a TI. Não desative TLS, antivírus, validação de certificados ou controles corporativos para liberar downloads.
- Faça login somente nas interfaces oficiais. Nunca envie senha, token, código MFA ou código de login ao chat, ao repositório ou ao suporte.
- Trabalhe com os dados sintéticos entregues. Não use dados reais de RH, conexões de produção, credenciais compartilhadas nem endpoints privados em prompts.

Software gratuito não significa laboratório inteiro gratuito. Windows e hardware, termos da distribuição do JDK, licença Copilot e consumo de nuvem são assuntos separados. Créditos e trials não são garantidos.

## 2. Materiais e GitHub Copilot

### 2.1. Obter e preservar os materiais

1. Abra o [repositório dos participantes](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs). O acesso publico para leitura e download nao exige convite ou autenticacao.
2. No GitHub, escolha **Code > Download ZIP** e extraia todo o conteúdo para uma pasta local autorizada. Git é opcional. Se preferir clone, use a URL apresentada pelo próprio repositório e o método de autenticação aprovado. Se não houver acesso, solicite o pacote aprovado ao facilitador.
3. Preserve a estrutura extraída, incluindo `labs`. A raiz extraída é a pasta que contém essa pasta; execute nela os comandos que este manual indica como sendo da raiz. Não trabalhe dentro do ZIP.
4. Antes de qualquer alteração, copie os fontes para um local privado autorizado **fora de toda a árvore do repositório**. Use uma pasta nova, não sobrescreva cópias anteriores e confira os arquivos copiados. No Java, preserve `modernizacao-java/legado` com `pom.xml`, `src` e scripts; use outra cópia para modernizar, conforme o roteiro operacional.
5. Continue na cópia de trabalho, não na preservada. Uma branch não substitui backup; uma cópia dos fontes não protege o banco. Se usar Git, confira raiz, branch e alterações antes de agir; não descarte trabalho existente. Um ZIP pode não conter repositório Git.

Consulte a [preparação comum do pacote](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/PREPARACAO.md) e o setup específico da sua trilha. Os links deste manual são absolutos para funcionar também fora da pasta do projeto. Os arquivos estao disponiveis publicamente; sua presenca nao comprova execucao dos laboratorios ou acesso aos servicos.

### 2.2. Editor, Chat e recursos especializados

Para o Lab 01, instale pelo VS Code os componentes oficiais **GitHub Copilot** e **Extension Pack for Java**, conferindo os publicadores GitHub e Microsoft. Faça login pela interface oficial com a conta aprovada e confirme licença, políticas e limites de uso com sua organização. Consulte a [instalação oficial do Copilot no VS Code](https://code.visualstudio.com/docs/copilot/setup).

Abra o Copilot Chat no modo **Ask/Perguntar** e peça apenas uma explicação curta sobre a diferença entre compilar e testar, sem executar ferramentas ou alterar arquivos. Uma resposta confirma uso do Chat naquele momento; **não confirma acesso ao agente especializado de modernização**, que depende de instalação, disponibilidade, licença e políticas próprias. Confirme-o com o facilitador antes da atividade. Não habilite aprovação automática de ferramentas.

Copilot não é necessário para instalar e conferir SQL pelo SSMS. No Fabric, Copilot é opcional e possui requisitos próprios do serviço; não se confunde com a licença do GitHub Copilot. Foundry pode ser preparado pelo navegador. Em qualquer trilha, revise as sugestões antes de autorizar alterações ou execução.

<!-- page -->

## 3. Lab 01: Java

### 3.1. Instalar somente a base Java

A entrega contém um **legado local H2 v2 com JDK 8** e uma **referência final com JDK 21**, com fontes e scripts. JARs e dependências não estão incluídos: a preparação das ferramentas não comprova build ou testes na sua máquina.

| Componente | Orientação | Fonte oficial |
| --- | --- | --- |
| JDK 8 completo | Distribuição aprovada pela organização, com `java` e `javac`; confira suporte e termos do fornecedor | [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=8) é uma opção a submeter à TI |
| JDK 21 completo | Microsoft Build of OpenJDK é uma opção gratuita para esta trilha, sujeita à aprovação da organização | [Microsoft Build of OpenJDK](https://learn.microsoft.com/java/openjdk/download) |
| Apache Maven 3.9.x | Use versão 3.9.6 ou posterior **dentro da linha 3.9.x**, compatível com JDK 8 | [Download do Maven](https://maven.apache.org/download.cgi) e [requisitos de instalação](https://maven.apache.org/install.html) |

Não presuma que qualquer distribuição Oracle JDK 8 é gratuita para qualquer uso. Microsoft Build of OpenJDK 21 não é uma distribuição Microsoft de JDK 8. Não substitua Maven 3.9.x por Maven 4 sem uma rota específica validada. Se os downloads atuais não oferecerem a versão compatível aprovada, solicite à TI a distribuição oficial adequada; não use sites de terceiros.

### 3.2. Conferir as versões por etapa

Mantenha ambos os JDKs instalados. Com orientação da TI, selecione o JDK da etapa no terminal de trabalho. `JAVA_HOME` deve apontar para a raiz do JDK, não para `bin`; o `PATH` precisa resolver as ferramentas correspondentes. Não altere globalmente o ambiente de outros projetos.

No **mesmo terminal PowerShell**, execute separadamente:

```powershell
java -version
javac -version
mvn.cmd -version
$env:JAVA_HOME
```

Para o legado, espere **Java 8 e javac 8**, e Maven 3.9.x usando esse JDK 8. Para a referência modernizada, repita a conferência com **Java 21 e javac 21**, e Maven usando JDK 21. O Maven pode exibir o subdiretório `jre` do JDK 8. Se houver divergência ou comando não encontrado, pare e ajuste com a TI; isso não prova ausência de instalação. Não desinstale o outro JDK para resolver o terminal.

### 3.3. Conferir fontes e isolamento

Na pasta `labs/lab-01-apps/modernizacao-java`, confira as pastas **`legado`** e **`modernizado`**, cada uma com `src`, `pom.xml` e scripts de build, start, bootstrap e stop. Preserve os fontes como indicado na seção 2 antes de qualquer alteração.

Leia o [setup Java](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-01-apps/SETUP-CLIENTE.md) e o [README operacional Java](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-01-apps/modernizacao-java/README.md) para os comandos exatos de cada etapa, incluindo os caminhos de JDK e Maven. Não execute build, bootstrap ou start apenas para concluir a leitura deste manual.

Os scripts usam **`maven-local.xml` com `-s` e `-gs`**, substituindo os settings usuais de usuário e globais. Peça à TI uma configuração local aprovada de mirror/proxy, caso necessária; a configuração corporativa usual pode não ser herdada. Não grave segredos no repositório nem contorne TLS.

Quando a execução for autorizada durante o lab, mantenha a aplicação em **loopback**, em porta livre, com dados sintéticos. Não encerre processos de outras pessoas para liberar portas. **H2 em memória é descartável e pode perder os dados ao reiniciar; não equivale a Oracle nem comprova compatibilidade SQL/PLSQL ou migração de banco.** Revise configuração, start/stop e mecanismo de recarga antes de executar.

### 3.4. Destino Azure somente quando autorizado

O destino condicional é **Azure App Service Linux**, conforme avaliação e roteiro do treinamento. Não exige Docker, Azure Container Registry, Container Apps ou AKS. Não crie recursos na preparação.

Antes de deploy, confirme assinatura e resource group de laboratório, runtime disponível e compatível, região, plano/SKU/preço, quota, permissões por operação, responsável e prazo de encerramento. Aprove o custo e configure a barreira de acesso antes de publicar: autenticação restrita ao grupo autorizado ou restrição IP explicitamente aprovada com HTTPS, com teste de recusa. Desabilite console H2 e endpoints sensíveis; não exponha escrita anônima. Instale Azure CLI somente se a rota aprovada a exigir.

**Pronto para iniciar o Lab 01:** fontes preservados, versões Java/Maven coerentes, acesso ao material e Copilot confirmado, configuração local revisada. Isso ainda não comprova build, testes, funcionamento da aplicação ou deploy Azure.

### 3.5. Checklist Java antes do evento

- [ ] TI aprovou as distribuições, licenças e instalação de JDK 8, JDK 21 e Maven 3.9.x.
- [ ] Conferi `java`, `javac`, Maven e `JAVA_HOME` no terminal de cada etapa, sem alterar outros projetos.
- [ ] VS Code, extensões Java e GitHub Copilot licenciados/autorizados estão disponíveis; acesso ao agente especializado foi confirmado separadamente com o facilitador.
- [ ] Preservei os fontes fora do repositório e localizei `legado`, `modernizado` e seus scripts na entrega.
- [ ] Configuração H2 local e mirror/proxy aprovados foram revisados; não executei build ou bootstrap nesta preparação.
- [ ] Se houver etapa App Service, o responsável confirmou custo, quota, acesso e aprovação; não criei recursos nem fiz deploy.

<!-- page -->

## 4. Lab 02: SQL Server para Azure SQL

Objetivo antes do treinamento: instalar **SQL Server 2022 Developer** e **SQL Server Management Studio (SSMS) 22**, conectar ao servidor local e conferir a instalação por consultas de leitura. A carga do banco fica para o treinamento, salvo autorização expressa do facilitador.

**Rota de preparação:** Windows x64, instância local dedicada `CONTOSOLAB`, autenticação Windows. Não exige Docker, Azure, Azure CLI, Git, Copilot ou assinatura cloud para esta preparação. Não execute preflight Azure, scripts de reset, migração ou cleanup de outros guias nesta etapa.

### 4.1. É gratuito?

- **SQL Server 2022 Developer é gratuito para desenvolvimento e teste, não para produção.** Use somente a instância de laboratório e dados fictícios. Não selecione Enterprise/Standard pagas nem confunda Developer com Evaluation, que tem prazo de avaliação.
- **SSMS é gratuito**, inclusive para uso empresarial, sem licença adicional de Visual Studio. O Visual Studio Installer é o mecanismo de instalação, não uma exigência de assinatura Visual Studio.
- Windows, computador e eventuais licenças de Copilot são separados; não estão incluídos nessa gratuidade. Copilot não é necessário para instalar e conferir a conexão.
- Recursos Azure de etapas posteriores podem gerar cobrança. Não crie recursos agora. Qualquer uso cloud exige aprovação explícita de custo, acesso e prazo de remoção; créditos gratuitos não são garantidos.

Referências: [edições do SQL Server 2022 e restrição Developer](https://learn.microsoft.com/en-us/sql/sql-server/editions-and-components-of-sql-server-2022?view=sql-server-ver16) e [FAQ oficial do SSMS: custo e licenciamento](https://learn.microsoft.com/en-us/ssms/faq).

### 4.2. Antes de começar

- Obtenha autorização da TI para instalar SQL Server e SSMS. Você ou a TI precisará de permissão de administrador do Windows para a instalação. Se não tiver essa permissão, contate a TI antes do evento; não contorne políticas corporativas.
- Em **Configurações > Sistema > Sobre > Tipo de sistema**, confira Windows e processador **x64 (Intel/AMD)**. Para esta rota, use Windows 11 em versão suportada por ambos os produtos e pela organização. Outros Windows exigem conferência nas matrizes oficiais abaixo e aprovação da TI.
- SQL Server 2022 para Windows exige processador x64. Embora o SSMS 22 tenha suporte a Windows Arm64, isso **não torna o motor SQL Server 2022 compatível com ARM**. Em ARM, macOS ou Linux, pare e combine outra rota com o facilitador; não tente instalar o motor por emulação como solução deste guia.
- Recomendação prática para o laboratório: **16 GB de RAM e 60 GB livres em SSD**, com internet para downloads e atualizações. São margens recomendadas para Windows, motor, SSMS e arquivos temporários, **não os mínimos oficiais**. O espaço efetivo depende dos componentes e do cache do instalador.
- Consulte os [requisitos oficiais do SQL Server 2022](https://learn.microsoft.com/en-us/sql/sql-server/install/hardware-and-software-requirements-for-installing-sql-server-2022?view=sql-server-ver16) e os [requisitos oficiais do SSMS 22](https://learn.microsoft.com/en-us/ssms/system-requirements), inclusive sistema operacional, .NET e reinicializações pendentes. Não altere proteções do Windows para fazer a instalação passar.
- Reserve aproximadamente **60 a 90 minutos**, além do tempo de atendimento da TI. É uma estimativa, não uma garantia: rede, atualizações e reinicializações podem prolongar o processo.
- Se já houver SQL Server instalado, não desinstale, atualize nem reconfigure instâncias existentes. Não use banco corporativo, compartilhado ou de produção. Se o nome `CONTOSOLAB` já existir, pare e solicite conferência da TI/facilitador antes de prosseguir.

### 4.3. Baixar os produtos nas fontes oficiais

1. Abra o [Microsoft Evaluation Center: SQL Server 2022](https://www.microsoft.com/en-us/evalcenter/evaluate-sql-server-2022). Procure a opção **Developer** para **SQL Server 2022**, não apenas uma avaliação de 180 dias. Pode haver solicitação de login/cadastro no site oficial; faça-a diretamente no navegador conforme a política da sua organização.
2. Confira versão e edição antes de executar qualquer instalador. Se o site não disponibilizar **2022 Developer**, redirecionar somente para SQL Server 2025 ou oferecer outra edição, **pare e contate o facilitador/TI** para obter a mídia oficial correta. Não substitua por 2025, Express, LocalDB ou Evaluation por conta própria. Não use sites de terceiros.
3. Abra [Instalar SQL Server Management Studio](https://learn.microsoft.com/en-us/ssms/install/install) e use o link **SSMS 22 installer** dessa página. O arquivo inicial abre o **Visual Studio Installer**; não é um MSI independente. Use os links atuais da Microsoft, não executáveis antigos recebidos por mensagem.
4. Confira o editor Microsoft nas propriedades/assinatura digital dos arquivos. Se a assinatura não puder ser validada ou houver alerta corporativo, pare e solicite análise da TI.

**Não instale SQL Server 2014.** O cenário didático representa um legado por meio de banco com `compatibility_level = 120` em **motor SQL Server 2022, versão principal 16**. Isso é uma simulação de compatibilidade, não um motor 2014/12.x real nem prova de equivalência de comportamento. O SQL Server 2014 está fora do suporte estendido regular.

### 4.4. Instalar SQL Server 2022 Developer

1. Execute manualmente o instalador oficial de **SQL Server 2022 Developer**. Quando o Windows solicitar elevação (UAC), prossiga somente com autorização da TI. Leia os termos; o aceite é uma decisão sua/da organização, não de um assistente automático.
2. Escolha **Custom / Personalizada**, não **Basic / Básica**. Aguarde o download da mídia e a abertura do **SQL Server Installation Center**.
3. Em **Installation / Instalação**, selecione **New SQL Server stand-alone installation / Nova instalação autônoma**. Se houver escolha entre nova instância e adicionar recursos a uma instância existente, escolha **nova instância**. Não escolha upgrade nem adicione recursos a uma instância existente.
4. Na seleção de edição, confirme **Developer** e **SQL Server 2022**. Leia os termos e confira as regras de instalação. Se houver falha, não ignore a regra: encaminhe-a à TI.
5. Se aparecer **Azure Extension for SQL Server**, desmarque a integração opcional para esta preparação local. Não vincule conta, assinatura ou recurso Azure. Se uma política impedir essa escolha, pare e consulte a TI.
6. Em **Feature Selection / Seleção de Recursos**, marque apenas **Database Engine Services / Serviços do Mecanismo de Banco de Dados**. Não adicione Analysis Services, Integration Services, PolyBase, Machine Learning ou outros recursos opcionais. Dependências obrigatórias do motor permanecem sob controle do instalador.
7. Em **Instance Configuration / Configuração da Instância**, selecione **Named instance / Instância nomeada** e informe **`CONTOSOLAB`**. Confira que o resumo indica uma nova instância dedicada e que não há conflito com instância existente.
8. Em configuração de serviços, mantenha as contas virtuais padrão propostas para a nova instância, conforme política da TI. Não informe credenciais corporativas de serviço e não habilite serviços extras para este guia.
9. Em **Database Engine Configuration / Configuração do Mecanismo de Banco de Dados**, selecione **Windows authentication mode / Modo de autenticação do Windows**. Clique em **Add Current User / Adicionar Usuário Atual** para dar administração SQL ao participante **somente nesta instância de laboratório**. Se a TI executar o instalador com outra conta, ela deve conferir e adicionar explicitamente a conta Windows do participante. Não habilite **Mixed Mode**, não configure senha `sa` e não use login SQL.
10. Mantenha os diretórios locais padrão aprovados pela TI, fora de pastas sincronizadas ou compartilhadas. Não altere diretórios/configurações de outras instâncias. Revise o resumo: **2022 Developer, CONTOSOLAB, Database Engine, Windows Authentication**. Instale e confira sucesso do motor no resultado final; reinicie se solicitado.

Para esta conexão local, **não abra portas no firewall, não habilite TCP/IP nem SQL Server Browser**. Não exponha o servidor na rede. A conexão abaixo usa Shared Memory na própria máquina. Se esse protocolo estiver indisponível por política, solicite orientação à TI.

### 4.5. Instalar o SSMS 22

1. Execute manualmente o instalador obtido na página oficial do SSMS. Autorize o UAC apenas conforme a aprovação da TI e leia os termos antes de aceitar.
2. No **Visual Studio Installer**, selecione a instalação do **SQL Server Management Studio 22**. Não é necessário instalar uma edição completa do Visual Studio.
3. Mantenha os componentes essenciais do SSMS. **Workloads e componentes adicionais são opcionais**: não selecione Hybrid and Migration, BI/SSIS/SSAS, GitHub Copilot ou outros extras para esta preparação. Se a organização precisar de algum deles durante o treinamento, o facilitador orientará separadamente.
4. Instale, conclua eventuais reinicializações e abra **SQL Server Management Studio 22** no menu Iniciar. Login em conta Microsoft/GitHub não é necessário para esta conexão local com autenticação Windows.

O SSMS é o cliente; instalá-lo sozinho não instala o motor SQL Server. Ambos os passos precisam estar concluídos.

### 4.6. Conectar e conferir sem criar banco

No SSMS, abra **Connect / Conectar > Database Engine / Mecanismo de Banco de Dados** e preencha:

| Campo | Valor |
| --- | --- |
| Tipo de servidor | Database Engine |
| Nome do servidor | `lpc:.\CONTOSOLAB` |
| Autenticação | Windows Authentication |
| Banco inicial, se solicitado | `master` |
| Encryption / Criptografia | Mantenha a configuração segura padrão; não reduza a proteção para contornar erros |
| Trust server certificate / Confiar no certificado do servidor | Não marque como solução genérica |

O prefixo `lpc:` seleciona **Shared Memory**, restrito à conexão local. Se houver erro de certificado, cadeia não confiável, nome do certificado ou criptografia, **pare e encaminhe à TI**. Ela deve conferir protocolo e requisitos de certificado da instância local. Não desabilite validação de certificados nem reduza criptografia por tentativa; não aplique exceções de laboratório a servidores remotos.

Depois de conectar, clique em **New Query / Nova Consulta**, escolha `master` e execute somente:

```sql
SELECT
	SERVERPROPERTY('Edition') AS Edicao,
	SERVERPROPERTY('ProductVersion') AS Versao,
	SERVERPROPERTY('ProductMajorVersion') AS VersaoPrincipal,
	SERVERPROPERTY('InstanceName') AS Instancia;

SELECT DB_ID(N'ContosoRH_Legacy') AS BancoDoLaboratorio;
```

Resultado esperado: edição contendo **Developer**, versão iniciada em **16.**, versão principal **16**, instância **CONTOSOLAB** e **`NULL`** para `BancoDoLaboratorio` antes da carga. Com o usuário administrador da instância, `NULL` indica que o banco ainda não existe. Se edição/versão/instância diferirem, pare e solicite conferência. Se o banco já existir, não o apague nem execute o seed: consulte o facilitador.

**A preparação prévia termina aqui.** Conexão e consultas bem-sucedidas conferem a instalação local; não validam o laboratório completo, migração, desempenho ou acesso Azure. Também não comprovam o gate `.lab/setup-sqlserver.json` com status `READY`.

### 4.7. Obter os materiais do treinamento

Abra o [repositório dos participantes](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs). O acesso publico para leitura e download nao exige convite ou autenticacao.

No GitHub, use **Code > Download ZIP** e extraia todo o conteúdo em uma pasta local autorizada. Não é necessário instalar Git. Preserve a estrutura de pastas. Se o download/acesso não estiver disponível, solicite o pacote aprovado ao facilitador; não busque fontes de outro repositório.

Referências do material cliente, independentes do local em que este manual foi recebido:

- [Orientações do Lab 02](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-02-infra/modernizacao-sqlserver/README.md).
- [Seed do banco fictício](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-02-infra/modernizacao-sqlserver/legado/01-create-contosorh-legacy-sqlserver.sql).
- [Validação local parcial](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-02-infra/modernizacao-sqlserver/cli/validate-local.sql).

Se algum arquivo não estiver disponível na entrega recebida, pare e solicite a versão aprovada. Não improvise um seed.

### 4.8. Opcional: carga somente com autorização do facilitador

**Não execute esta seção antes do evento por padrão.** O seed cria banco, tabelas, procedures, trigger e dados fictícios com deficiências intencionais para estudo. Não é idempotente: repetir pode produzir erros e duplicar dados; os lotes posteriores podem continuar mesmo após erro em lote anterior. Nunca execute em instância compartilhada ou de produção.

1. Obtenha autorização expressa e confira novamente a conexão `lpc:.\CONTOSOLAB`, edição Developer, motor 16 e `DB_ID(N'ContosoRH_Legacy') = NULL` pela consulta da seção 4.6. Se já existir banco ou houver dúvida sobre o destino, pare.
2. No SSMS, use **File > Open > File / Arquivo > Abrir > Arquivo** e abra, a partir da raiz extraída do repositório, `labs/lab-02-infra/modernizacao-sqlserver/legado/01-create-contosorh-legacy-sqlserver.sql`.
3. Confira a leitura **UTF-8** e a exibição correta dos nomes acentuados; não salve uma conversão de codificação sobre o original. O SSMS interpreta os separadores de lote **`GO`** do arquivo. Não remova os `GO` nem execute o texto em ferramenta que não os suporte.
4. Confirme servidor e banco inicial **`master`** na janela desse arquivo. Revise o script completo; ele muda o contexto para `ContosoRH_Legacy` durante a execução. Sem seleção parcial de texto, pressione **F5 / Execute** **uma única vez**. Se houver erro, pare e informe o facilitador; não repita nem use reset/DROP para tentar corrigir.
5. Abra pelo mesmo menu `labs/lab-02-infra/modernizacao-sqlserver/cli/validate-local.sql`. Confira novamente a instância e selecione explicitamente **`ContosoRH_Legacy`** na lista de bancos dessa janela antes de executar. Esse arquivo exige o banco correto e compatibilidade **120**.
6. O resultado esperado do validador é **`LOCAL_BASELINE_OK`**, com **15 colaboradores, 25 férias, 30 tickets e 40 benefícios**. É uma **validação parcial**: não verifica toda a integridade, Unicode, segurança, migração ou equivalência com SQL Server 2014.
7. Se orientado pelo facilitador, em nova consulta no banco `ContosoRH_Legacy`, execute:

```sql
USE ContosoRH_Legacy;
GO
EXEC dbo.sp_SaldoFerias @colaboradorId = 1;
GO
```

No seed original sem alterações, o esperado é **`SaldoFerias = 20`** (30 menos 10 dias aprovados). Essa consulta chama uma procedure de leitura do seed; não demonstra que a modernização foi validada. Compatibilidade 120 continua sendo simulação sobre motor 2022.

### 4.9. Checklist e suporte antes do evento

- [ ] Autorização da TI e Windows x64 compatível confirmados.
- [ ] SQL Server **2022 Developer**, instância dedicada **CONTOSOLAB**, instalado sem alterar instâncias existentes.
- [ ] Autenticação Windows com o participante autorizado nessa instância; sem senha `sa`.
- [ ] SSMS instalado e conexão **`lpc:.\CONTOSOLAB`** concluída sem enfraquecer validação de certificados.
- [ ] Consulta de leitura retornou Developer, **16.x**, **CONTOSOLAB** e banco ausente (`NULL`), salvo carga expressamente autorizada.
- [ ] Nenhuma porta aberta, nenhum recurso cloud criado e nenhum seed executado sem autorização.
- [ ] Acesso aos materiais confirmado ou solicitado ao facilitador.

Para pedir ajuda, envie somente: etapa que falhou, versão/edição do SQL, versão do SSMS e mensagem de erro sanitizada. Oculte nomes pessoais, conta Windows, nome da máquina, caminhos privados, endereços, credenciais, tokens e identificadores do ambiente. Não envie logs integrais ou capturas sem revisar. Não compartilhe dados reais de RH.

Se o serviço não for encontrado, confira no aplicativo **Serviços** do Windows se **SQL Server (CONTOSOLAB)** existe e está em execução; solicite à TI qualquer ação administrativa. Em erro de login, peça que ela confira a conta Windows autorizada. Em falha de instalação, política, arquitetura ou certificado, encaminhe o problema antes do treinamento. Não use produção como alternativa.

### 4.10. Etapa Azure durante o treinamento

Esta etapa é separada da instalação local e depende da avaliação e da rota aprovadas pelo facilitador:

- Subscription e resource group de laboratório autorizados, região aprovada e provider `Microsoft.Sql` registrado pela equipe responsável.
- Permissões mínimas necessárias às operações aprovadas, limitadas ao escopo do laboratório. Não solicite Owner por padrão; permissões Entra para definir o administrador SQL devem ser concedidas separadamente quando necessárias.
- Destino, SKU, capacidade e custo definidos **após a avaliação**, sem exigir Standard S2 como baseline universal. Confirme quota, orçamento, responsável e prazo de remoção antes de criar recursos. Alertas de orçamento não são um teto rígido nem interrompem automaticamente a cobrança.
- Rede e autenticação aprovadas para o destino Azure, sem firewall irrestrito. Credenciais temporárias somente na sessão ou em secret store, nunca no material compartilhado.

`sqlcmd` e `SqlPackage` são ferramentas opcionais da rota CLI durante o treinamento; **não precisam ser instalados para esta preparação local**. Se essa rota for escolhida, siga o roteiro validado do facilitador e os downloads oficiais: [sqlcmd](https://learn.microsoft.com/sql/tools/sqlcmd/sqlcmd-utility) e [SqlPackage](https://learn.microsoft.com/sql/tools/sqlpackage/sqlpackage-download). VS Code com a extensão Microsoft MSSQL (`ms-mssql.mssql`) é uma alternativa de cliente, não um requisito para os passos de SSMS acima.

Não instale Azure Data Studio para um novo ambiente: foi aposentado em 28/02/2026 e não recebe correções de segurança. A extensão MSSQL não reproduz automaticamente o antigo assistente Azure SQL Migration; DMA/DmaCmd e DMS não fazem parte da instalação básica. Referência: [aposentadoria do Azure Data Studio](https://learn.microsoft.com/en-us/azure-data-studio/whats-happening-azure-data-studio).

<!-- page -->

## 5. Lab 03: Fabric

### 5.1. Preparação local do dataset

Instale **Node.js LTS com suporte, versão 22 ou superior**, conforme aprovação da TI. No PowerShell, confira `node --version`. Na raiz extraída do pacote, execute:

```powershell
node labs/lab-03-dados/scripts/validate-dataset.js
```

O resultado esperado termina em **`LAB03_DATASET_OK`**. O validador usa bibliotecas padrão do Node.js: **não precisa de `npm install`**. Ele confere os dez CSVs sintéticos locais, incluindo estrutura e referências verificadas pelo script; não comprova upload, execução do notebook, acesso ou capacidade cloud.

Confira a pasta `labs/lab-03-dados/dados-contoso-rh` e o notebook `labs/lab-03-dados/notebooks/ContosoRH_Notebook.ipynb`. Leia o [setup Fabric](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-03-dados/SETUP-CLIENTE.md) e confira o [notebook entregue](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-03-dados/notebooks/ContosoRH_Notebook.ipynb), sem executá-lo agora.

Não instale Spark, Hadoop, Java ou Python locais para esta trilha. O processamento ocorre no Fabric autorizado. Power BI Desktop é opcional, não requisito para preparar esta rota de navegador.

### 5.2. Acesso, licença e capacidade

Confirme com a TI e o administrador:

- Conta autorizada para entrar em [Microsoft Fabric](https://app.fabric.microsoft.com), com licença individual adequada às atividades. Licença Power BI Pro/PPU isolada não substitui capacidade para Lakehouse/Spark; capacidade não substitui licenciamento individual.
- Workspace **dedicado ao laboratório**, associado a capacidade Fabric autorizada, com região, responsável, janela e custo conhecidos. Não ative trial, compre ou redimensione capacidade durante a preparação. Não há garantia de trial ou de uma SKU fixa como F2.
- Menor privilégio para cada operação. Contributor pode atender às atividades de criação de itens em workspace existente; criação de workspace e administração exigem permissões próprias. Não solicite Admin para todos por padrão. Consulte os [papéis de workspace](https://learn.microsoft.com/fabric/fundamentals/roles-workspaces).
- Autorização para criar Lakehouse e Notebook **durante o laboratório**, não agora. Ver o portal não prova que essas operações são permitidas. Sem licença, capacidade ou acesso adequado, registre o bloqueio cloud; a validação local pode ser concluída separadamente.

Para conferir os requisitos vigentes, consulte as [licenças do Fabric](https://learn.microsoft.com/fabric/enterprise/licenses). Nenhuma verificação deste manual certifica que o ambiente cloud do participante já foi testado.

### 5.3. Escrita, recursos opcionais e encerramento

O notebook usa `seed_path = Files` e escrita **overwrite** nas tabelas Bronze, Silver e Gold. Use **Lakehouse dedicado vazio**, confira o destino associado e revise cada célula antes de executá-la durante a sessão autorizada. Não rode sobre tabelas compartilhadas, espelhos ou shortcuts. Dados e resultados continuam sintéticos; remover algumas colunas não comprova anonimização de dados reais.

Copilot, Ontology e Data Agent são **opcionais**. O administrador deve conferir requisitos atuais de capacidade, licença, tenant, região, residência/cross-geo e preview aceito. Não altere configurações do tenant para contornar indisponibilidade. Sem Copilot, siga a rota manual. Sem Ontology/Data Agent ou integração suportada, conclua até o **Passo 9** e registre o Passo 10 como não executado. Isso não impede a rota independente do Lab 04.

Ao terminar o lab, pare sua sessão Spark. Exclusão de itens ou workspace exclusivos exige aprovação; **não exclua, pause ou redimensione capacidade compartilhada**. Se houver preservação da fonte para integração posterior, combine identidade de acesso, custo, responsável e prazo.

**Pronto para iniciar o Lab 03:** Node e dataset local conferidos, materiais disponíveis e acessos/licença/capacidade confirmados ou bloqueios explicitados. A criação e a execução cloud ficam para o treinamento autorizado.

### 5.4. Checklist Fabric antes do evento

- [ ] Instalei Node.js LTS com suporte, 22+, aprovado pela TI, e conferi `node --version`.
- [ ] Na raiz extraída, executei `node labs/lab-03-dados/scripts/validate-dataset.js` e obtive `LAB03_DATASET_OK`.
- [ ] Localizei os dez CSVs e o notebook; não instalei Spark/Hadoop/Java locais para esta trilha.
- [ ] Administrador confirmou conta, licença individual, capacidade, workspace dedicado e permissões, ou o bloqueio cloud foi comunicado.
- [ ] Custos e janela foram aprovados, sem ativar trial ou criar itens; Copilot, Ontology e Data Agent continuam opcionais.
- [ ] Revisei o risco de overwrite; execução e verificação do Lakehouse/Notebook ficam para a sessão autorizada.

<!-- page -->

## 6. Lab 04: Foundry

### 6.1. Rota de navegador e acesso

A preparação usa navegador atualizado e acesso ao [Microsoft Foundry](https://ai.azure.com). **Não exige Python, pip, SDK, CLI nem arquivo `.env`** na rota de portal. Consulte o [setup Foundry](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-04-ia/SETUP-CLIENTE.md) e o [guia da fonte e da execução](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-04-ia/02-guia-completo.md).

Antes da sessão, confirme com o administrador a identidade autorizada, assinatura quando aplicável, projeto/recurso de laboratório, região e modelo/deployment/versão compatíveis com a atividade. Verifique disponibilidade e quota, ferramenta de consulta suportada, identidade usada por ela, custos e permissões. Não crie projeto, agente, deployment ou conexão durante a preparação. Consulte a [documentação oficial do Foundry](https://learn.microsoft.com/azure/ai-foundry/).

**Entrar no portal não comprova inferência.** Permissão de leitura da interface não equivale a criar agentes, implantar modelos, executar inferência ou ler a fonte. O administrador deve aprovar separadamente o menor privilégio para cada operação e escopo; não eleve permissões para contornar uma negação.

### 6.2. Escolher uma fonte real de consulta

| Rota | O que precisa ser confirmado |
| --- | --- |
| Independente do Lab 03 | Fixture sintética **`lab04-rh-v1`**, descrita no guia, com ferramenta de recuperação/RAG realmente suportada e autorizada, ingestão e leitura permitidas e citação verificável |
| Integração Fabric, somente se aprovada | Tabelas Gold reais validadas, schema/chaves/tipos, corte/versão, filtros e referências numéricas; identidade e leitura da fonte; suporte efetivo da conexão/ferramenta ou Data Agent, conforme a rota |

O **Lab 03 não é obrigatório** para a rota independente. A integração Fabric depende de suporte vigente da ferramenta, capacidade, tenant, região, licenças e preview, quando aplicáveis. Modelo semântico, Fabric Data Agent e agente Foundry são componentes distintos. Gold pronta não comprova Data Agent publicado nem Foundry conectado; confirme propagação de identidade e suporte da autenticação, sem presumir passthrough ou service principal.

**Upload isolado ou dados colados nas instruções não comprovam recuperação/RAG.** É necessário verificar a consulta real à fonte e a citação. Sem ferramenta disponível ou leitura autorizada, identifique a atividade apenas como simulação e não aceite respostas factuais como se estivessem fundamentadas em recuperação real. Não use a fixture independente para declarar integração Fabric.

### 6.3. Consumo e critério de avanço

Na preparação, confirme as condições com o administrador, sem chamadas ao modelo. Um teste real pequeno é **opcional**, somente se explicitamente aprovado e conduzido pelo administrador/facilitador **durante a sessão autorizada**. Antes dele, defina limites de tokens de entrada/saída, número de chamadas, tempo e custo, com regra de parada. Não automatize tentativas, não faça chamadas para apenas conferir login e não use dados reais.

Quota não é orçamento nem autorização de gasto. Região ou licença disponíveis não garantem modelo, deployment, ferramenta ou fonte acessível. Não há garantia de créditos gratuitos. Login, criação, inferência e recuperação são verificações diferentes; registre o que foi de fato demonstrado e o que permanece bloqueado.

**Pronto para iniciar o Lab 04:** rota e fonte escolhidas, acesso e permissões confirmados ou bloqueios explícitos, limites aprovados e nenhum consumo iniciado na preparação. Testes e avaliações ficam para a sessão; sem recuperação real, não há comprovação de resposta factual fundamentada.

### 6.4. Checklist Foundry antes do evento

- [ ] Navegador atualizado e login oficial disponíveis; Python não é necessário na rota de portal.
- [ ] Escolhi a fixture independente `lab04-rh-v1` ou a integração Fabric condicional; Lab 03 não foi tratado como obrigatório.
- [ ] Administrador confirmou projeto, modelo/deployment/versão, região, quota, ferramenta RAG e acesso à fonte, ou comunicou o bloqueio.
- [ ] Permissões de interface, agente/conexão, deployment, inferência e leitura foram confirmadas por operação e escopo.
- [ ] Custos, tokens, número de chamadas, tempo e regra de parada estão aprovados; não iniciei consumo nem provisionamento.
- [ ] Recuperação real e citação serão verificadas na sessão autorizada; login ou upload não foram tratados como prova de RAG.

<!-- page -->

## 7. Aprovações, checklist final e suporte

### 7.1. Confirmar com a TI e os responsáveis

| Tema | Confirmação necessária |
| --- | --- |
| Computador | Windows x64, espaço, instalação autorizada, reinicializações e políticas de rede/certificados |
| Contas e licenças | Acesso ao material privado e ao Copilot quando usado; licença individual e capacidade Fabric; identidade/projeto Foundry |
| Escopo cloud | Assinatura e resource group somente para a atividade que os utilizar; workspace/projeto dedicado, região e responsável |
| Permissões | Menor privilégio por operação: leitura, criação, deployment, inferência e fonte; nenhuma concessão automática de Owner, User Access Administrator ou administrador Entra |
| Consumo | Preço vigente, estimativa com moeda/período, quota, limites operacionais, alertas, janela, regra de parada e responsável pelo acompanhamento |
| Encerramento | Itens temporários exatos, autorização de remoção, prazo e preservação de recursos compartilhados |

**Alertas de orçamento não são teto rígido de gasto e não interrompem automaticamente a cobrança.** Quota é limite técnico, não saldo financeiro. Trial, capacidade, SKU, gratuidade e créditos dependem dos termos e da aprovação; não os ative por conta própria.

Criar, implantar, executar teste de consumo e excluir recursos exigem aprovação explícita dos destinos exatos. Administração de Windows não autoriza operações cloud. Use somente sandbox aprovado; não use produção como alternativa. Não pause capacidade compartilhada nem exclua a fonte usada por outra trilha.

### 7.2. Checklist do participante

- [ ] Selecionei somente os labs em que participarei e preparei as ferramentas correspondentes.
- [ ] Conferi Windows x64, espaço recomendado e aprovação da TI, ou comuniquei a necessidade de outra rota.
- [ ] Obtive e extraí o pacote aprovado; confirmei acesso aos materiais sem compartilhar credenciais.
- [ ] Preservei uma cópia independente dos fontes antes de alterações; não tratei branch como backup.
- [ ] **Java, se escolhido:** JDK 8/21 e Maven 3.9.x conferidos no terminal de cada etapa; fontes, Copilot e configuração local revisados.
- [ ] **SQL, se escolhido:** concluí a checklist da seção 4.9, sem seed salvo autorização expressa.
- [ ] **Fabric, se escolhido:** obtive `LAB03_DATASET_OK`; confirmei licença, capacidade, workspace e permissões, ou registrei o bloqueio cloud.
- [ ] **Foundry, se escolhido:** confirmei rota independente ou integração condicional, fonte/ferramenta, identidade, permissões e limites; não confundi login com inferência ou recuperação.
- [ ] Não criei recursos cloud, não iniciei consumo nem executei deploy durante a preparação; pendências foram comunicadas.

### 7.3. Pedir ajuda com segurança

Envie ao facilitador apenas **lab, etapa, versão da ferramenta, resultado esperado e erro sanitizado**. Indique se o bloqueio é local, de acesso, licença, capacidade, quota ou fonte. A TI trata instalação, proxy e certificados; o administrador do serviço trata identidade, permissões e recursos autorizados.

Oculte nomes pessoais, conta Windows, máquina, caminhos privados, IDs e endpoints do ambiente, dados de RH, senhas, tokens, strings de conexão e códigos de login/MFA. Não envie logs integrais ou capturas sem revisão. Identificadores necessários ao atendimento devem seguir o canal privado seguro aprovado pela organização, nunca o chat ou material compartilhado.

Receber este manual ou concluir uma instalação não certifica o laboratório inteiro. Leve as evidências locais e as pendências de acesso; avance nas operações do treinamento somente após a aprovação correspondente.