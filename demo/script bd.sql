CREATE TABLE [dbo].[barrio](
    [barrio_id] VARCHAR(50) NOT NULL,
    [barrio] VARCHAR(100) NOT NULL,
    [comuna] SMALLINT NOT NULL,
    PRIMARY KEY ([barrio_id])
);
GO

CREATE TABLE [dbo].[tipo_incidente](
    [tipo_incidente_id] INT IDENTITY(1,1) NOT NULL,
    [nombre] VARCHAR(100) NOT NULL,
    PRIMARY KEY ([tipo_incidente_id])
);
GO

CREATE TABLE [dbo].[subtipo_incidente](
    [subtipo_incidente_id] INT IDENTITY(1,1) NOT NULL,
    [nombre] VARCHAR(100) NOT NULL,
    [tipo_id] INT,
    PRIMARY KEY ([subtipo_incidente_id]),
    FOREIGN KEY ([tipo_id]) REFERENCES [dbo].[tipo_incidente] ([tipo_incidente_id])
);
GO

CREATE TABLE [dbo].[incidente](
    [id_incidente] INT IDENTITY(1,1) NOT NULL,
    [subtipo_incidente_id] INT,
    [uso_arma] BIT NOT NULL,
    [uso_moto] BIT NOT NULL,
    [cantidad] SMALLINT NOT NULL,
    [fecha] DATE NOT NULL,
    [franja] SMALLINT NOT NULL,
    [barrio_id] VARCHAR(50),
    [nombre_dia] VARCHAR(50),
    [anio] SMALLINT NOT NULL,
    [nombre_mes] VARCHAR(50),
    PRIMARY KEY ([id_incidente]),
    FOREIGN KEY ([subtipo_incidente_id]) REFERENCES [dbo].[subtipo_incidente] ([subtipo_incidente_id]),
    FOREIGN KEY ([barrio_id]) REFERENCES [dbo].[barrio] ([barrio_id])
);
GO






CREATE PROCEDURE [dbo].[sp_InsertarDatosIncidente]
    @uso_arma BIT,
    @uso_moto BIT,
    @cantidad SMALLINT,
    @fecha DATE,
    @franja SMALLINT,
    @nombre_dia VARCHAR(50),
    @anio SMALLINT,
    @nombre_mes VARCHAR(50),
    @barrio_id VARCHAR(50),
    @nombre_barrio VARCHAR(100),
    @comuna SMALLINT,
    @tipo VARCHAR(100),
    @subtipo VARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @tipo_id INT;
    DECLARE @subtipo_id INT;

    BEGIN TRANSACTION;
    BEGIN TRY

        IF NOT EXISTS (SELECT 1 FROM tipo_incidente WHERE nombre = @tipo)
        BEGIN
            INSERT INTO tipo_incidente (nombre)
            VALUES (@tipo);
            SET @tipo_id = SCOPE_IDENTITY();
        END
        ELSE
        BEGIN
            SET @tipo_id = (SELECT tipo_incidente_id FROM tipo_incidente WHERE nombre = @tipo);
        END


        IF NOT EXISTS (SELECT 1 FROM subtipo_incidente WHERE nombre = @subtipo AND tipo_id = @tipo_id)
        BEGIN
            INSERT INTO subtipo_incidente (nombre, tipo_id)
            VALUES (@subtipo, @tipo_id);
            SET @subtipo_id = SCOPE_IDENTITY();
        END
        ELSE
        BEGIN
            SET @subtipo_id = (SELECT subtipo_incidente_id FROM subtipo_incidente WHERE nombre = @subtipo AND tipo_id = @tipo_id);
        END


        IF NOT EXISTS (SELECT 1 FROM barrio WHERE barrio_id = @barrio_id)
        BEGIN
            INSERT INTO barrio (barrio_id, barrio, comuna)
            VALUES (@barrio_id, @nombre_barrio, @comuna);
        END


        INSERT INTO incidente (subtipo_incidente_id, uso_arma, uso_moto, cantidad, fecha, franja, barrio_id, nombre_dia, anio, nombre_mes)
        VALUES (@subtipo_id, @uso_arma, @uso_moto, @cantidad, @fecha, @franja, @barrio_id, @nombre_dia, @anio, @nombre_mes);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH;
END;
GO



select count(*) from incidente;
