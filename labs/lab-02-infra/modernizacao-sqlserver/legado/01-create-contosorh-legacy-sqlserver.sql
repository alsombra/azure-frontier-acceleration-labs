/* =====================================================================
   Contoso RH — Core de RH (AS-IS)
   Base: ContosoRH_Legacy | Origem: SQL Server 2014 (compatibility 120)
   ===================================================================== */

-- Cria a base com compatibility level 120 e recovery FULL sem rotina de backup
CREATE DATABASE ContosoRH_Legacy;
GO
ALTER DATABASE ContosoRH_Legacy SET COMPATIBILITY_LEVEL = 120;
ALTER DATABASE ContosoRH_Legacy SET RECOVERY FULL;
GO

-- Seleciona a base recém-criada como contexto de trabalho
USE ContosoRH_Legacy;
GO

-- Cadastro central de colaboradores (VARCHAR, MONEY, NTEXT, CPF em texto claro)
CREATE TABLE dbo.Colaboradores (
    Id            INT IDENTITY(1,1) PRIMARY KEY,
    Nome          VARCHAR(100),
    Matricula     VARCHAR(20),
    CPF           VARCHAR(14),
    Email         VARCHAR(150),
    Departamento  VARCHAR(50),
    Cargo         VARCHAR(50),
    Pais          VARCHAR(50),
    DataAdmissao  DATETIME,
    Salario       MONEY,
    Ativo         BIT,
    Observacoes   NTEXT
);
GO

-- Solicitações de férias (sem FOREIGN KEY, sem CHECK no campo Status)
CREATE TABLE dbo.Ferias (
    Id              INT IDENTITY(1,1) PRIMARY KEY,
    ColaboradorId   INT,
    DataInicio      DATETIME,
    DataFim         DATETIME,
    DiasSolicitados INT,
    Status          VARCHAR(20),
    AprovadorId     INT,
    CriadoEm        DATETIME DEFAULT GETDATE()
);
GO

-- Tickets de atendimento de RH (TEXT na coluna Descricao)
CREATE TABLE dbo.Tickets (
    Id            INT IDENTITY(1,1) PRIMARY KEY,
    ColaboradorId INT,
    Categoria     VARCHAR(50),
    Assunto       VARCHAR(200),
    Descricao     TEXT,
    Prioridade    VARCHAR(10),
    Status        VARCHAR(20),
    AbertoEm      DATETIME DEFAULT GETDATE(),
    FechadoEm     DATETIME NULL
);
GO

-- Benefícios concedidos por colaborador (MONEY na coluna Valor)
CREATE TABLE dbo.Beneficios (
    Id            INT IDENTITY(1,1) PRIMARY KEY,
    ColaboradorId INT,
    TipoBeneficio VARCHAR(50),
    Valor         MONEY,
    Ativo         BIT,
    AtivadoEm     DATETIME
);
GO

-- Tabela de log consumida pela trigger de auditoria (sem índice)
CREATE TABLE dbo.FeriasLog (
    Id          INT IDENTITY(1,1) PRIMARY KEY,
    FeriasId    INT,
    Mensagem    VARCHAR(200),
    RegistadoEm DATETIME DEFAULT GETDATE()
);
GO

-- Busca colaboradores por nome usando SQL dinâmico concatenado
CREATE PROCEDURE dbo.sp_BuscarColaborador
    @filtro VARCHAR(100)
AS
BEGIN
    DECLARE @sql NVARCHAR(500);
    SET @sql = 'SELECT * FROM dbo.Colaboradores WHERE Nome LIKE ''%' + @filtro + '%''';
    EXEC(@sql);
END;
GO

-- Calcula o saldo de férias (30 - dias aprovados) percorrendo um cursor
CREATE PROCEDURE dbo.sp_SaldoFerias
    @colaboradorId INT
AS
BEGIN
    DECLARE @dias INT = 0, @d INT;
    DECLARE c CURSOR FOR
        SELECT DiasSolicitados FROM dbo.Ferias
        WHERE ColaboradorId = @colaboradorId AND Status = 'Aprovado';
    OPEN c;
    FETCH NEXT FROM c INTO @d;
    WHILE @@FETCH_STATUS = 0
    BEGIN
        SET @dias = @dias + @d;
        FETCH NEXT FROM c INTO @d;
    END
    CLOSE c; DEALLOCATE c;
    SELECT 30 - @dias AS SaldoFerias;
END;
GO

-- Trigger AFTER INSERT que registra cada nova solicitação na tabela de log
CREATE TRIGGER dbo.trg_Ferias_Audit
ON dbo.Ferias
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.FeriasLog (FeriasId, Mensagem)
    SELECT i.Id, 'Ferias criada para colaborador ' + CAST(i.ColaboradorId AS VARCHAR(10))
    FROM inserted i;
END;
GO

-- Popula 15 colaboradores de 6 países com nomes acentuados e CPFs fictícios
INSERT INTO dbo.Colaboradores (Nome, Matricula, CPF, Email, Departamento, Cargo, Pais, DataAdmissao, Salario, Ativo, Observacoes) VALUES
('José da Silva',          'BR0001', '111.111.111-11', 'jose.silva@contoso.com',     'RH',          'Analista',      'Brasil',   '2015-03-10', 8500.00,  1, 'Colaborador veterano do time de RH'),
('Müller Schäfer',         'DE0002', '222.222.222-22', 'm.schafer@contoso.com',      'Financeiro',  'Gerente',       'Alemanha', '2018-07-22', 15200.00, 1, 'Relocado de Munique em 2018'),
('João São Paulo',         'BR0003', '333.333.333-33', 'joao.sp@contoso.com',        'TI',          'Desenvolvedor', 'Brasil',   '2020-01-15', 11000.00, 1, NULL),
('Aiko Tanaka',            'JP0004', '444.444.444-44', 'aiko.tanaka@contoso.com',    'Operações',   'Coordenadora',  'Japão',    '2019-11-05', 9800.00,  1, NULL),
('Priya Iyer',             'IN0005', '555.555.555-55', 'priya.iyer@contoso.com',     'TI',          'Arquiteta',     'Índia',    '2017-05-30', 13400.00, 1, 'Time de plataforma'),
('María Hernández',        'MX0006', '666.666.666-66', 'maria.hernandez@contoso.com','Vendas',      'Executiva',     'México',   '2016-09-12', 10250.00, 1, 'Cobertura LATAM'),
('Michael Johnson',        'US0007', '777.777.777-77', 'mjohnson@contoso.com',       'Financeiro',  'Controller',    'EUA',      '2014-02-18', 16800.00, 1, 'Sede em Redmond'),
('Ana Conceição',          'BR0008', '888.888.888-88', 'ana.conceicao@contoso.com',  'RH',          'Coordenadora',  'Brasil',   '2021-06-01', 9400.00,  1, NULL),
('Sönke Brandt',           'DE0009', '999.999.999-99', 'sonke.brandt@contoso.com',   'Operações',   'Especialista',  'Alemanha', '2022-03-14', 8900.00,  1, 'Logística EMEA'),
('Rajesh Kumar',           'IN0010', '101.010.101-01', 'rajesh.kumar@contoso.com',   'TI',          'Tech Lead',     'Índia',    '2018-10-09', 14100.00, 1, NULL),
('Sarah Williams',         'US0011', '121.212.121-21', 'swilliams@contoso.com',      'Vendas',      'Diretora',      'EUA',      '2013-04-25', 21000.00, 1, 'Líder de conta global'),
('José María Gómez',       'MX0012', '131.313.131-31', 'jm.gomez@contoso.com',       'TI',          'Analista',      'México',   '2020-08-19', 8700.00,  1, 'Suporte N2'),
('Yuki Nakamura',          'JP0013', '141.414.141-41', 'yuki.nakamura@contoso.com',  'Operações',   'Gerente',       'Japão',    '2015-12-03', 13900.00, 1, NULL),
('Conceição Évora',        'BR0014', '151.515.151-51', 'c.evora@contoso.com',        'Financeiro',  'Analista',      'Brasil',   '2023-01-30', 7600.00,  0, 'Afastada desde abr/2025'),
('Friedrich Groß',         'DE0015', '161.616.161-61', 'f.gross@contoso.com',        'RH',          'Gerente',       'Alemanha', '2017-07-07', 15600.00, 1, 'Business partner DACH');
GO

-- Popula 25 solicitações de férias com picos em dezembro e março
INSERT INTO dbo.Ferias (ColaboradorId, DataInicio, DataFim, DiasSolicitados, Status, AprovadorId) VALUES
(1,  '2025-12-20', '2025-12-30', 10, 'Aprovado',  7),
(2,  '2026-03-05', '2026-03-15', 10, 'Pendente',  NULL),
(3,  '2025-12-22', '2026-01-05', 14, 'Aprovado',  1),
(4,  '2026-03-10', '2026-03-20', 10, 'Pendente',  NULL),
(5,  '2025-12-18', '2025-12-28', 10, 'Aprovado',  10),
(6,  '2026-03-01', '2026-03-08', 7,  'Rejeitado', 11),
(7,  '2025-12-23', '2026-01-02', 10, 'Aprovado',  11),
(8,  '2026-03-12', '2026-03-22', 10, 'Pendente',  NULL),
(9,  '2025-12-15', '2025-12-25', 10, 'Aprovado',  15),
(10, '2026-03-03', '2026-03-13', 10, 'Aprovado',  10),
(11, '2025-12-20', '2025-12-31', 11, 'Aprovado',  7),
(12, '2026-03-07', '2026-03-14', 7,  'Rejeitado', 10),
(13, '2025-12-19', '2025-12-29', 10, 'Aprovado',  4),
(14, '2026-03-09', '2026-03-19', 10, 'Pendente',  NULL),
(15, '2025-12-21', '2025-12-31', 10, 'Aprovado',  8),
(1,  '2026-03-16', '2026-03-23', 7,  'Pendente',  NULL),
(3,  '2026-07-01', '2026-07-10', 9,  'Aprovado',  1),
(5,  '2026-03-25', '2026-04-04', 10, 'Pendente',  NULL),
(7,  '2026-08-05', '2026-08-15', 10, 'Aprovado',  11),
(9,  '2026-03-02', '2026-03-09', 7,  'Rejeitado', 15),
(10, '2025-12-26', '2026-01-04', 9,  'Aprovado',  10),
(11, '2026-03-18', '2026-03-28', 10, 'Pendente',  NULL),
(13, '2026-06-10', '2026-06-20', 10, 'Aprovado',  4),
(2,  '2025-12-22', '2025-12-29', 7,  'Aprovado',  15),
(6,  '2026-03-11', '2026-03-21', 10, 'Aprovado',  11);
GO

-- Popula 30 tickets distribuídos nas 5 categorias de atendimento
INSERT INTO dbo.Tickets (ColaboradorId, Categoria, Assunto, Descricao, Prioridade, Status, AbertoEm, FechadoEm) VALUES
(1,  'Holerite',  'Holerite de dezembro não disponível',     'O holerite de 12/2025 não apareceu no portal.',            'Alta',  'Fechado', '2025-12-05', '2025-12-07'),
(2,  'Beneficio', 'Inclusão de dependente no plano de saúde', 'Solicito inclusão de dependente no plano médico.',         'Media', 'Aberto',  '2026-03-02', NULL),
(3,  'Reembolso', 'Reembolso de curso técnico',               'Envio nota fiscal de curso de certificação em nuvem.',     'Baixa', 'Fechado', '2026-01-10', '2026-01-18'),
(4,  'Acesso',    'Acesso ao sistema de ponto bloqueado',     'Não consigo registrar o ponto desde ontem.',               'Alta',  'Aberto',  '2026-02-14', NULL),
(5,  'Outro',     'Atualização de endereço residencial',      'Mudei de endereço, favor atualizar cadastro.',             'Baixa', 'Fechado', '2026-01-22', '2026-01-23'),
(6,  'Holerite',  'Divergência de valor no holerite',         'Valor líquido diferente do esperado em 02/2026.',          'Alta',  'Aberto',  '2026-02-28', NULL),
(7,  'Reembolso', 'Reembolso de viagem corporativa',          'Despesas da viagem a Redmond aguardando reembolso.',       'Media', 'Fechado', '2026-01-15', '2026-01-25'),
(8,  'Beneficio', 'Cancelamento de vale-refeição',            'Solicito cancelar o VR a partir do próximo mês.',          'Media', 'Aberto',  '2026-03-04', NULL),
(9,  'Acesso',    'Reset de senha do portal RH',              'Esqueci a senha e o autoatendimento falhou.',              'Alta',  'Fechado', '2026-02-01', '2026-02-01'),
(10, 'Outro',     'Declaração de tempo de serviço',           'Preciso de declaração para financiamento bancário.',       'Baixa', 'Fechado', '2026-01-08', '2026-01-12'),
(11, 'Holerite',  'Informe de rendimentos anual',             'Solicito o informe de rendimentos de 2025 para IR.',       'Media', 'Aberto',  '2026-03-06', NULL),
(12, 'Beneficio', 'Ativação do Gympass',                      'Quero ativar o benefício de academia.',                    'Baixa', 'Fechado', '2026-02-10', '2026-02-12'),
(13, 'Reembolso', 'Reembolso de plano odontológico',          'Atendimento fora da rede, envio recibo.',                  'Media', 'Aberto',  '2026-02-20', NULL),
(14, 'Acesso',    'Reativação de conta após afastamento',     'Retornando do afastamento, conta segue inativa.',          'Alta',  'Aberto',  '2026-03-01', NULL),
(15, 'Outro',     'Solicitação de crachá novo',               'Perdi o crachá, preciso de segunda via.',                  'Baixa', 'Fechado', '2026-01-30', '2026-02-02'),
(1,  'Beneficio', 'Mudança de plano odontológico',            'Quero migrar para o plano odonto premium.',                'Baixa', 'Aberto',  '2026-03-09', NULL),
(3,  'Holerite',  'Holerite com desconto indevido',           'Desconto de empréstimo já quitado apareceu de novo.',      'Alta',  'Aberto',  '2026-03-03', NULL),
(5,  'Reembolso', 'Reembolso de material de home office',     'Compra de cadeira ergonômica conforme política.',          'Media', 'Fechado', '2026-01-19', '2026-01-29'),
(7,  'Acesso',    'Permissão para VPN corporativa',           'Preciso de acesso VPN para trabalho remoto.',              'Media', 'Fechado', '2026-02-05', '2026-02-06'),
(9,  'Outro',     'Atualização de dados bancários',           'Troquei de banco, favor atualizar conta salário.',         'Media', 'Aberto',  '2026-03-07', NULL),
(10, 'Holerite',  'Adiantamento de 13º salário',              'Consulta sobre antecipação da primeira parcela.',          'Baixa', 'Fechado', '2025-11-20', '2025-11-25'),
(11, 'Beneficio', 'Inclusão no plano de previdência',         'Quero aderir ao plano de previdência privada.',            'Media', 'Aberto',  '2026-03-10', NULL),
(12, 'Reembolso', 'Reembolso de despesa médica',              'Consulta particular fora da rede credenciada.',            'Baixa', 'Aberto',  '2026-02-26', NULL),
(13, 'Acesso',    'Acesso ao prédio fora do horário',         'Necessito de acesso noturno para plantão.',                'Alta',  'Fechado', '2026-01-12', '2026-01-14'),
(14, 'Outro',     'Carta de recomendação interna',            'Solicito carta para transferência de área.',               'Baixa', 'Aberto',  '2026-03-05', NULL),
(15, 'Holerite',  'Erro no cálculo de horas extras',          'Horas extras de janeiro não foram pagas.',                 'Alta',  'Aberto',  '2026-02-15', NULL),
(2,  'Reembolso', 'Reembolso de táxi para reunião',           'Deslocamento a cliente, envio comprovante.',               'Baixa', 'Fechado', '2026-01-28', '2026-02-03'),
(4,  'Beneficio', 'Troca de operadora do plano de saúde',     'Avaliando migração de operadora no aniversário.',          'Media', 'Aberto',  '2026-03-08', NULL),
(6,  'Acesso',    'Liberação de pasta compartilhada',         'Preciso de acesso à pasta do time financeiro.',            'Media', 'Fechado', '2026-02-09', '2026-02-11'),
(8,  'Outro',     'Atualização de estado civil',              'Casamento recente, atualizar cadastro e dependentes.',     'Baixa', 'Aberto',  '2026-03-11', NULL);
GO

-- Popula 40 benefícios com 2 a 3 registros por colaborador
INSERT INTO dbo.Beneficios (ColaboradorId, TipoBeneficio, Valor, Ativo, AtivadoEm) VALUES
(1,  'VR',           880.00,  1, '2015-03-10'),
(1,  'Plano Saude',  650.00,  1, '2015-03-10'),
(1,  'Gympass',      120.00,  1, '2022-01-05'),
(2,  'VA',           750.00,  1, '2018-07-22'),
(2,  'Plano Saude',  980.00,  1, '2018-07-22'),
(3,  'VR',           880.00,  1, '2020-01-15'),
(3,  'Plano Odonto', 90.00,   1, '2020-01-15'),
(3,  'Gympass',      120.00,  1, '2021-03-01'),
(4,  'VA',           700.00,  1, '2019-11-05'),
(4,  'Plano Saude',  720.00,  1, '2019-11-05'),
(5,  'VR',           880.00,  1, '2017-05-30'),
(5,  'Plano Saude',  650.00,  1, '2017-05-30'),
(5,  'Plano Odonto', 90.00,   1, '2018-02-01'),
(6,  'VA',           760.00,  1, '2016-09-12'),
(6,  'Gympass',      120.00,  1, '2019-06-15'),
(7,  'VR',           900.00,  1, '2014-02-18'),
(7,  'Plano Saude',  1100.00, 1, '2014-02-18'),
(7,  'Plano Odonto', 95.00,   1, '2015-01-10'),
(8,  'VR',           880.00,  1, '2021-06-01'),
(8,  'Plano Saude',  650.00,  1, '2021-06-01'),
(9,  'VA',           780.00,  1, '2022-03-14'),
(9,  'Gympass',      120.00,  1, '2022-03-14'),
(10, 'VR',           880.00,  1, '2018-10-09'),
(10, 'Plano Saude',  650.00,  1, '2018-10-09'),
(10, 'Plano Odonto', 90.00,   1, '2019-05-01'),
(11, 'VA',           820.00,  1, '2013-04-25'),
(11, 'Plano Saude',  1200.00, 1, '2013-04-25'),
(11, 'Gympass',      120.00,  1, '2020-01-01'),
(12, 'VR',           880.00,  1, '2020-08-19'),
(12, 'Plano Odonto', 90.00,   1, '2020-08-19'),
(13, 'VA',           770.00,  1, '2015-12-03'),
(13, 'Plano Saude',  950.00,  1, '2015-12-03'),
(13, 'Gympass',      120.00,  1, '2021-09-10'),
(14, 'VR',           880.00,  0, '2023-01-30'),
(14, 'Plano Saude',  650.00,  0, '2023-01-30'),
(15, 'VA',           800.00,  1, '2017-07-07'),
(15, 'Plano Saude',  980.00,  1, '2017-07-07'),
(15, 'Plano Odonto', 95.00,   1, '2018-01-15'),
(2,  'Gympass',      120.00,  1, '2019-01-01'),
(4,  'Plano Odonto', 85.00,   1, '2020-01-01');
GO

-- ============ AVALIAÇÃO ESPERADA (DMA / Azure SQL Migration) ============
-- Category            | Severity | Object                         | Recommendation
-- Deprecated datatype | Warning  | Colaboradores.Observacoes NTEXT| -> NVARCHAR(MAX)
-- Deprecated datatype | Warning  | Tickets.Descricao TEXT         | -> NVARCHAR(MAX)
-- Compatibility       | Info     | Salario/Valor MONEY            | -> DECIMAL(19,4)
-- Compatibility level | Info     | Level 120                      | Azure SQL usa 150
-- Unicode             | Warning  | Colunas VARCHAR (Nome, Email)  | -> NVARCHAR (i18n)
-- Security            | Warning  | sp_BuscarColaborador           | SQL dinâmico -> sp_executesql
-- Integrity           | Info     | Sem FK / CHECK / índices       | adicionar no destino
-- ========================================================================
