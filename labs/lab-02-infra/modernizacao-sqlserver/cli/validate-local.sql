SET NOCOUNT ON;

IF DB_NAME() <> N'ContosoRH_Legacy'
    THROW 51000, 'Banco local incorreto.', 1;

IF (SELECT compatibility_level FROM sys.databases WHERE name = DB_NAME()) <> 120
    THROW 51000, 'Compatibility level local deve ser 120.', 1;

IF (SELECT COUNT(*) FROM dbo.Colaboradores) <> 15
    THROW 51000, 'Contagem de Colaboradores invalida.', 1;
IF (SELECT COUNT(*) FROM dbo.Ferias) <> 25
    THROW 51000, 'Contagem de Ferias invalida.', 1;
IF (SELECT COUNT(*) FROM dbo.Tickets) <> 30
    THROW 51000, 'Contagem de Tickets invalida.', 1;
IF (SELECT COUNT(*) FROM dbo.Beneficios) <> 40
    THROW 51000, 'Contagem de Beneficios invalida.', 1;

IF TYPE_NAME(COLUMNPROPERTY(OBJECT_ID(N'dbo.Colaboradores'), N'Nome', 'SystemTypeId')) <> N'varchar'
    THROW 51000, 'Baseline perdeu a deficiencia VARCHAR esperada.', 1;

SELECT N'LOCAL_BASELINE_OK' AS Resultado,
       (SELECT COUNT(*) FROM dbo.Colaboradores) AS Colaboradores,
       (SELECT COUNT(*) FROM dbo.Ferias) AS Ferias,
       (SELECT COUNT(*) FROM dbo.Tickets) AS Tickets,
       (SELECT COUNT(*) FROM dbo.Beneficios) AS Beneficios;
GO
