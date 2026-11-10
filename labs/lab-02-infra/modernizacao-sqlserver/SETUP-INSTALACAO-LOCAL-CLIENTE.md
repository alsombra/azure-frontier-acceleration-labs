# Preparacao do participante: SQL Server local antes do treinamento

Este guia pode ser enviado separadamente. Objetivo: instalar SQL Server 2022 Developer e SQL Server Management Studio (SSMS), conectar ao servidor local e conferir a instalacao por consultas de leitura. A carga do banco fica para o treinamento, salvo autorizacao expressa do facilitador.

**Rota deste guia:** Windows x64, instancia local dedicada `CONTOSOLAB`, autenticacao Windows. Nao exige Docker, Azure, Azure CLI, Git, Copilot ou uma assinatura cloud para esta preparacao. Nao execute preflight Azure, scripts de reset, migracao ou cleanup de outros guias nesta etapa.

## 1. E gratuito?

- **SQL Server 2022 Developer e gratuito para desenvolvimento e teste, nao para producao.** Use somente a instancia de laboratorio e dados ficticios. Nao selecione Enterprise/Standard pagas nem confunda Developer com Evaluation, que tem prazo de avaliacao.
- **SSMS e gratuito**, inclusive para uso empresarial, sem licenca adicional de Visual Studio. O Visual Studio Installer e o mecanismo de instalacao, nao uma exigencia de assinatura Visual Studio.
- Windows, computador e eventuais licencas de Copilot sao separados; nao estao incluidos nessa gratuidade. Copilot nao e necessario para instalar e conferir a conexao.
- Recursos Azure de etapas posteriores podem gerar cobranca. Nao crie recursos agora. Qualquer uso cloud exige aprovacao explicita de custo, acesso e prazo de remocao; creditos gratuitos nao sao garantidos.

Referencias: [edicoes do SQL Server 2022 e restricao Developer](https://learn.microsoft.com/en-us/sql/sql-server/editions-and-components-of-sql-server-2022?view=sql-server-ver16) e [FAQ oficial do SSMS: custo e licenciamento](https://learn.microsoft.com/en-us/ssms/faq).

## 2. Antes de comecar

- Obtenha autorizacao da TI para instalar SQL Server e SSMS. Voce ou a TI precisara de permissao de administrador do Windows para a instalacao. Se nao tiver essa permissao, contate a TI antes do evento; nao contorne politicas corporativas.
- Em **Configuracoes > Sistema > Sobre > Tipo de sistema**, confira Windows e processador **x64 (Intel/AMD)**. Para esta rota, use Windows 11 em versao suportada por ambos os produtos e pela organizacao. Outros Windows exigem conferencia nas matrizes oficiais abaixo e aprovacao da TI.
- SQL Server 2022 para Windows exige processador x64. Embora o SSMS 22 tenha suporte a Windows Arm64, isso **nao torna o motor SQL Server 2022 compativel com ARM**. Em ARM, macOS ou Linux, pare e combine outra rota com o facilitador; nao tente instalar o motor por emulacao como solucao deste guia.
- Recomendacao pratica para o laboratorio: **16 GB de RAM e 60 GB livres em SSD**, com internet para downloads e atualizacoes. Sao margens recomendadas para Windows, motor, SSMS e arquivos temporarios, **nao os minimos oficiais**. O espaco efetivo depende dos componentes e do cache do instalador.
- Consulte os [requisitos oficiais do SQL Server 2022](https://learn.microsoft.com/en-us/sql/sql-server/install/hardware-and-software-requirements-for-installing-sql-server-2022?view=sql-server-ver16) e os [requisitos oficiais do SSMS 22](https://learn.microsoft.com/en-us/ssms/system-requirements), inclusive sistema operacional, .NET e reinicializacoes pendentes. Nao altere protecoes do Windows para fazer a instalacao passar.
- Reserve aproximadamente **60 a 90 minutos**, alem do tempo de atendimento da TI. E uma estimativa, nao uma garantia: rede, atualizacoes e reinicializacoes podem prolongar o processo.
- Se ja houver SQL Server instalado, nao desinstale, atualize nem reconfigure instancias existentes. Nao use banco corporativo, compartilhado ou de producao. Se o nome `CONTOSOLAB` ja existir, pare e solicite conferencia da TI/facilitador antes de prosseguir.

## 3. Baixar os produtos nas fontes oficiais

1. Abra o [Microsoft Evaluation Center: SQL Server 2022](https://www.microsoft.com/en-us/evalcenter/evaluate-sql-server-2022). Procure a opcao **Developer** para **SQL Server 2022**, nao apenas uma avaliacao de 180 dias. Pode haver solicitacao de login/cadastro no site oficial; faca-a diretamente no navegador conforme a politica da sua organizacao.
2. Confira versao e edicao antes de executar qualquer instalador. Se o site nao disponibilizar **2022 Developer**, redirecionar somente para SQL Server 2025 ou oferecer outra edicao, **pare e contate o facilitador/TI** para obter a midia oficial correta. Nao substitua por 2025, Express, LocalDB ou Evaluation por conta propria. Nao use sites de terceiros.
3. Abra [Instalar SQL Server Management Studio](https://learn.microsoft.com/en-us/ssms/install/install) e use o link **SSMS 22 installer** dessa pagina. O arquivo inicial abre o **Visual Studio Installer**; nao e um MSI independente. Use os links atuais da Microsoft, nao executaveis antigos recebidos por mensagem.
4. Confira o editor Microsoft nas propriedades/assinatura digital dos arquivos. Se a assinatura nao puder ser validada ou houver alerta corporativo, pare e solicite analise da TI.

**Nao instale SQL Server 2014.** O cenario didatico representa um legado por meio de banco com `compatibility_level = 120` em **motor SQL Server 2022, versao principal 16**. Isso e uma simulacao de compatibilidade, nao um motor 2014/12.x real nem prova de equivalencia de comportamento. O SQL Server 2014 esta fora do suporte estendido regular.

## 4. Instalar SQL Server 2022 Developer

1. Execute manualmente o instalador oficial de **SQL Server 2022 Developer**. Quando o Windows solicitar elevacao (UAC), prossiga somente com autorizacao da TI. Leia os termos; o aceite e uma decisao sua/da organizacao, nao de um assistente automatico.
2. Escolha **Custom / Personalizada**, nao **Basic / Basica**. Aguarde o download da midia e a abertura do **SQL Server Installation Center**.
3. Em **Installation / Instalacao**, selecione **New SQL Server stand-alone installation / Nova instalacao autonoma**. Se houver escolha entre nova instancia e adicionar recursos a uma instancia existente, escolha **nova instancia**. Nao escolha upgrade nem adicione recursos a uma instancia existente.
4. Na selecao de edicao, confirme **Developer** e **SQL Server 2022**. Leia os termos e confira as regras de instalacao. Se houver falha, nao ignore a regra: encaminhe-a a TI.
5. Se aparecer **Azure Extension for SQL Server**, desmarque a integracao opcional para esta preparacao local. Nao vincule conta, assinatura ou recurso Azure. Se uma politica impedir essa escolha, pare e consulte a TI.
6. Em **Feature Selection / Selecao de Recursos**, marque apenas **Database Engine Services / Servicos do Mecanismo de Banco de Dados**. Nao adicione Analysis Services, Integration Services, PolyBase, Machine Learning ou outros recursos opcionais. Dependencias obrigatorias do motor permanecem sob controle do instalador.
7. Em **Instance Configuration / Configuracao da Instancia**, selecione **Named instance / Instancia nomeada** e informe **`CONTOSOLAB`**. Confira que o resumo indica uma nova instancia dedicada e que nao ha conflito com instancia existente.
8. Em configuracao de servicos, mantenha as contas virtuais padrao propostas para a nova instancia, conforme politica da TI. Nao informe credenciais corporativas de servico e nao habilite servicos extras para este guia.
9. Em **Database Engine Configuration / Configuracao do Mecanismo de Banco de Dados**, selecione **Windows authentication mode / Modo de autenticacao do Windows**. Clique em **Add Current User / Adicionar Usuario Atual** para dar administracao SQL ao participante **somente nesta instancia de laboratorio**. Se a TI executar o instalador com outra conta, ela deve conferir e adicionar explicitamente a conta Windows do participante. Nao habilite **Mixed Mode**, nao configure senha `sa` e nao use login SQL.
10. Mantenha os diretorios locais padrao aprovados pela TI, fora de pastas sincronizadas ou compartilhadas. Nao altere diretorios/configuracoes de outras instancias. Revise o resumo: **2022 Developer, CONTOSOLAB, Database Engine, Windows Authentication**. Instale e confira sucesso do motor no resultado final; reinicie se solicitado.

Para esta conexao local, **nao abra portas no firewall, nao habilite TCP/IP nem SQL Server Browser**. Nao exponha o servidor na rede. A conexao abaixo usa Shared Memory na propria maquina. Se esse protocolo estiver indisponivel por politica, solicite orientacao a TI.

## 5. Instalar o SSMS 22

1. Execute manualmente o instalador obtido na pagina oficial do SSMS. Autorize o UAC apenas conforme a aprovacao da TI e leia os termos antes de aceitar.
2. No **Visual Studio Installer**, selecione a instalacao do **SQL Server Management Studio 22**. Nao e necessario instalar uma edicao completa do Visual Studio.
3. Mantenha os componentes essenciais do SSMS. **Workloads e componentes adicionais sao opcionais**: nao selecione Hybrid and Migration, BI/SSIS/SSAS, GitHub Copilot ou outros extras para esta preparacao. Se a organizacao precisar de algum deles durante o treinamento, o facilitador orientara separadamente.
4. Instale, conclua eventuais reinicializacoes e abra **SQL Server Management Studio 22** no menu Iniciar. Login em conta Microsoft/GitHub nao e necessario para esta conexao local com autenticacao Windows.

O SSMS e o cliente; instala-lo sozinho nao instala o motor SQL Server. Ambos os passos precisam estar concluidos.

## 6. Conectar e conferir sem criar banco

No SSMS, abra **Connect / Conectar > Database Engine / Mecanismo de Banco de Dados** e preencha:

| Campo | Valor |
| --- | --- |
| Tipo de servidor | Database Engine |
| Nome do servidor | `lpc:.\CONTOSOLAB` |
| Autenticacao | Windows Authentication |
| Banco inicial, se solicitado | `master` |
| Encryption / Criptografia | Mantenha a configuracao segura padrao; nao reduza a protecao para contornar erros |
| Trust server certificate / Confiar no certificado do servidor | Nao marque como solucao generica |

O prefixo `lpc:` seleciona **Shared Memory**, restrito a conexao local. Se houver erro de certificado, cadeia nao confiavel, nome do certificado ou criptografia, **pare e encaminhe a TI**. Ela deve conferir protocolo e requisitos de certificado da instancia local. Nao desabilite validacao de certificados nem reduza criptografia por tentativa; nao aplique excecoes de laboratorio a servidores remotos.

Depois de conectar, clique em **New Query / Nova Consulta**, escolha `master` e execute somente:

```sql
SELECT
    SERVERPROPERTY('Edition') AS Edicao,
    SERVERPROPERTY('ProductVersion') AS Versao,
    SERVERPROPERTY('ProductMajorVersion') AS VersaoPrincipal,
    SERVERPROPERTY('InstanceName') AS Instancia;

SELECT DB_ID(N'ContosoRH_Legacy') AS BancoDoLaboratorio;
```

Resultado esperado: edicao contendo **Developer**, versao iniciada em **16.**, versao principal **16**, instancia **CONTOSOLAB** e **`NULL`** para `BancoDoLaboratorio` antes da carga. Com o usuario administrador da instancia, `NULL` indica que o banco ainda nao existe. Se edicao/versao/instancia diferirem, pare e solicite conferencia. Se o banco ja existir, nao o apague nem execute o seed: consulte o facilitador.

**A preparacao previa termina aqui.** Conexao e consultas bem-sucedidas conferem a instalacao local; nao validam o laboratorio completo, migracao, desempenho ou acesso Azure.

## 7. Obter os materiais do treinamento

Abra o [repositorio dos participantes](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs). O acesso publico para leitura e download nao exige convite ou autenticacao.

No GitHub, use **Code > Download ZIP** e extraia todo o conteudo em uma pasta local autorizada. Nao e necessario instalar Git. Preserve a estrutura de pastas. Se o download/acesso nao estiver disponivel, solicite o pacote aprovado ao facilitador; nao busque fontes de outro repositorio.

Referencias do material cliente, independentes do local em que este guia foi recebido:

- [Orientacoes do Lab 02](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-02-infra/modernizacao-sqlserver/README.md).
- [Seed do banco ficticio](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-02-infra/modernizacao-sqlserver/legado/01-create-contosorh-legacy-sqlserver.sql).
- [Validacao local parcial](https://github.com/thaiseosbatista/azure-frontier-acceleration-labs/blob/main/labs/lab-02-infra/modernizacao-sqlserver/cli/validate-local.sql).

Se algum arquivo nao estiver disponivel na entrega recebida, pare e solicite a versao aprovada. Nao improvise um seed.

## 8. Opcional: carga somente com autorizacao do facilitador

**Nao execute esta secao antes do evento por padrao.** O seed cria banco, tabelas, procedures, trigger e dados ficticios com deficiencias intencionais para estudo. Nao e idempotente: repetir pode produzir erros e duplicar dados; os lotes posteriores podem continuar mesmo apos erro em lote anterior. Nunca execute em instancia compartilhada ou de producao.

1. Obtenha autorizacao expressa e confira novamente a conexao `lpc:.\CONTOSOLAB`, edicao Developer, motor 16 e `DB_ID(N'ContosoRH_Legacy') = NULL` pela consulta da secao 6. Se ja existir banco ou houver duvida sobre o destino, pare.
2. No SSMS, use **File > Open > File / Arquivo > Abrir > Arquivo** e abra, a partir da raiz extraida do repositorio, `labs/lab-02-infra/modernizacao-sqlserver/legado/01-create-contosorh-legacy-sqlserver.sql`.
3. Confira a leitura **UTF-8** e a exibicao correta dos nomes acentuados; nao salve uma conversao de codificacao sobre o original. O SSMS interpreta os separadores de lote **`GO`** do arquivo. Nao remova os `GO` nem execute o texto em ferramenta que nao os suporte.
4. Confirme servidor e banco inicial **`master`** na janela desse arquivo. Revise o script completo; ele muda o contexto para `ContosoRH_Legacy` durante a execucao. Sem selecao parcial de texto, pressione **F5 / Execute** **uma unica vez**. Se houver erro, pare e informe o facilitador; nao repita nem use reset/DROP para tentar corrigir.
5. Abra pelo mesmo menu `labs/lab-02-infra/modernizacao-sqlserver/cli/validate-local.sql`. Confira novamente a instancia e selecione explicitamente **`ContosoRH_Legacy`** na lista de bancos dessa janela antes de executar. Esse arquivo exige o banco correto e compatibilidade **120**.
6. O resultado esperado do validador e **`LOCAL_BASELINE_OK`**, com **15 colaboradores, 25 ferias, 30 tickets e 40 beneficios**. E uma **validacao parcial**: nao verifica toda a integridade, Unicode, seguranca, migracao ou equivalencia com SQL Server 2014.
7. Se orientado pelo facilitador, em nova consulta no banco `ContosoRH_Legacy`, execute:

```sql
USE ContosoRH_Legacy;
GO
EXEC dbo.sp_SaldoFerias @colaboradorId = 1;
GO
```

No seed original sem alteracoes, o esperado e **`SaldoFerias = 20`** (30 menos 10 dias aprovados). Essa consulta chama uma procedure de leitura do seed; nao demonstra que a modernizacao foi validada. Compatibilidade 120 continua sendo simulacao sobre motor 2022.

## 9. Checklist e suporte antes do evento

- [ ] Autorizacao da TI e Windows x64 compativel confirmados.
- [ ] SQL Server **2022 Developer**, instancia dedicada **CONTOSOLAB**, instalado sem alterar instancias existentes.
- [ ] Autenticacao Windows com o participante autorizado nessa instancia; sem senha `sa`.
- [ ] SSMS instalado e conexao **`lpc:.\CONTOSOLAB`** concluida sem enfraquecer validacao de certificados.
- [ ] Consulta de leitura retornou Developer, **16.x**, **CONTOSOLAB** e banco ausente (`NULL`), salvo carga expressamente autorizada.
- [ ] Nenhuma porta aberta, nenhum recurso cloud criado e nenhum seed executado sem autorizacao.
- [ ] Acesso aos materiais confirmado ou solicitado ao facilitador.

Para pedir ajuda, envie somente: etapa que falhou, versao/edicao do SQL, versao do SSMS e mensagem de erro sanitizada. Oculte nomes pessoais, conta Windows, nome da maquina, caminhos privados, enderecos, credenciais, tokens e identificadores do ambiente. Nao envie logs integrais ou capturas sem revisar. Nao compartilhe dados reais de RH.

Se o servico nao for encontrado, confira no aplicativo **Servicos** do Windows se **SQL Server (CONTOSOLAB)** existe e esta em execucao; solicite a TI qualquer acao administrativa. Em erro de login, peca que ela confira a conta Windows autorizada. Em falha de instalacao, politica, arquitetura ou certificado, encaminhe o problema antes do treinamento. Nao use producao como alternativa.