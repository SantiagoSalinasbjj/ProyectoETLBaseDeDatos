
USE [BD_PROYECTOBD]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[barrio](
    [codigo] VARCHAR(50) NOT NULL,
    [barrio] VARCHAR(100) NOT NULL,
    [comuna] SMALLINT NOT NULL,
    PRIMARY KEY ([codigo])
);
GO

CREATE TABLE [dbo].[tipo_incidente](
    [id] INT IDENTITY(1,1) NOT NULL,
    [nombre] VARCHAR(100) NOT NULL,
    PRIMARY KEY ([id])
);
GO

CREATE TABLE [dbo].[subtipo_incidente](
    [id] INT IDENTITY(1,1) NOT NULL,
    [nombre] VARCHAR(100) NOT NULL,
    [tipo_id] INT,
    PRIMARY KEY ([id]),
    FOREIGN KEY ([tipo_id]) REFERENCES [dbo].[tipo_incidente] ([id])
);
GO

CREATE TABLE [dbo].[zona_afectada](
    [id_mapa] INT IDENTITY(1,1) NOT NULL,
    [latitud] FLOAT NOT NULL,
    [longitud] FLOAT NOT NULL,
    [codigo] VARCHAR(50),
    PRIMARY KEY ([id_mapa]),
    FOREIGN KEY ([codigo]) REFERENCES [dbo].[barrio] ([codigo])
);
GO

CREATE TABLE [dbo].[incidente](
    [id_incidente] INT IDENTITY(1,1) NOT NULL,
    [subtipo_id] INT,
    [uso_arma] BIT NOT NULL,
    [uso_moto] BIT NOT NULL,
    [cantidad] SMALLINT NOT NULL,
    [fecha] DATE NOT NULL,
    [franja] SMALLINT NOT NULL,
    [zona_afectada_id] INT,
    [nombre_dia] VARCHAR(50),
    [anio] SMALLINT NOT NULL,
    [nombre_mes] VARCHAR(50),
    PRIMARY KEY ([id_incidente]),
    FOREIGN KEY ([subtipo_id]) REFERENCES [dbo].[subtipo_incidente] ([id]),
    FOREIGN KEY ([zona_afectada_id]) REFERENCES [dbo].[zona_afectada] ([id_mapa])
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
    @codigo_barrio VARCHAR(50),
    @nombre_barrio VARCHAR(100),
    @comuna SMALLINT,
    @latitud FLOAT,
    @longitud FLOAT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @tipo_id INT;
    DECLARE @subtipo_id INT;
    DECLARE @zona_afectada_id INT;

    BEGIN TRANSACTION;
    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM Tipo_Incidente WHERE nombre = @nombre_barrio)
        BEGIN
            INSERT INTO Tipo_Incidente (nombre)
            VALUES (@nombre_barrio);
            SET @tipo_id = SCOPE_IDENTITY();
        END
        ELSE
        BEGIN
            SET @tipo_id = (SELECT id FROM Tipo_Incidente WHERE nombre = @nombre_barrio);
        END

        IF NOT EXISTS (SELECT 1 FROM Subtipo_Incidente WHERE nombre = @nombre_barrio AND tipo_id = @tipo_id)
        BEGIN
            INSERT INTO Subtipo_Incidente (nombre, tipo_id)
            VALUES (@nombre_barrio, @tipo_id);
            SET @subtipo_id = SCOPE_IDENTITY();
        END
        ELSE
        BEGIN
            SET @subtipo_id = (SELECT id FROM Subtipo_Incidente WHERE nombre = @nombre_barrio AND tipo_id = @tipo_id);
        END

        IF NOT EXISTS (SELECT 1 FROM Barrio WHERE codigo = @codigo_barrio)
        BEGIN
            INSERT INTO Barrio (codigo, barrio, comuna)
            VALUES (@codigo_barrio, @nombre_barrio, @comuna);
        END

        IF NOT EXISTS (SELECT 1 FROM Zona_Afectada WHERE latitud = @latitud AND longitud = @longitud)
        BEGIN
            INSERT INTO Zona_Afectada (latitud, longitud, codigo)
            VALUES (@latitud, @longitud, @codigo_barrio);
            SET @zona_afectada_id = SCOPE_IDENTITY();
        END
        ELSE
        BEGIN
            SET @zona_afectada_id = (SELECT id_mapa FROM Zona_Afectada WHERE latitud = @latitud AND longitud = @longitud);
        END

        INSERT INTO Incidente (subtipo_id, uso_arma, uso_moto, cantidad, fecha, franja, zona_afectada_id, nombre_dia, anio, nombre_mes)
        VALUES (@subtipo_id, @uso_arma, @uso_moto, @cantidad, @fecha, @franja, @zona_afectada_id, @nombre_dia, @anio, @nombre_mes);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        PRINT 'Ocurrió un error al intentar insertar los registros';
    END CATCH;
END;
GO



select count(*) from incidente;

select * from incidente;