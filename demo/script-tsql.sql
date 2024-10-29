


USE BD_MULTIDIMENSIONAL;

INSERT INTO Lugar (Lugar_id, Nombre_Barrio, Comuna)
SELECT 
    barrio_id AS Lugar_id,
    barrio AS Nombre_Barrio,
    comuna AS Comuna
FROM 
    BD_PROYECTOBD.dbo.barrio;


	INSERT INTO Tiempo (Tiempo_id, fecha, Hora, Nombre_Dia, Mes, Año)
SELECT 
    ROW_NUMBER() OVER (ORDER BY fecha, anio) AS Tiempo_id,  
    CAST(fecha AS DATETIME) AS fecha,
    NULL AS Hora,  
    nombre_dia AS Nombre_Dia,
    CASE nombre_mes
        WHEN 'Enero' THEN 1
        WHEN 'Febrero' THEN 2
        WHEN 'Marzo' THEN 3
        WHEN 'Abril' THEN 4
        WHEN 'Mayo' THEN 5
        WHEN 'Junio' THEN 6
        WHEN 'Julio' THEN 7
        WHEN 'Agosto' THEN 8
        WHEN 'Septiembre' THEN 9
        WHEN 'Octubre' THEN 10
        WHEN 'Noviembre' THEN 11
        WHEN 'Diciembre' THEN 12
        ELSE NULL
    END AS Mes,
    anio AS Año
FROM 
    BD_PROYECTOBD.dbo.incidente
GROUP BY 
    fecha, nombre_dia, anio, nombre_mes;
use BD_MULTIDIMENSIONAL;


	INSERT INTO Tipo_Incidente (Tipo_incidente_id, Descripcion)
SELECT 
    tipo_incidente_id AS Tipo_incidente_id,
    nombre AS Descripcion
FROM 
    BD_PROYECTOBD.dbo.tipo_incidente;


	INSERT INTO Incidente (id_incidente, Lugar_id, Tiempo_id, Tiempo_Lugar_id, Tipo_incidente_id)
SELECT 
    inc.id_incidente AS id_incidente,
    bar.barrio_id AS Lugar_id, 
    tiem.Tiempo_id AS Tiempo_id,
    NULL AS Tiempo_Lugar_id,  
    tipo.Tipo_incidente_id AS Tipo_incidente_id
FROM 
    BD_PROYECTOBD.dbo.incidente inc
    JOIN BD_PROYECTOBD.dbo.subtipo_incidente sub ON inc.subtipo_incidente_id = sub.subtipo_incidente_id
    JOIN BD_PROYECTOBD.dbo.tipo_incidente tipo ON sub.tipo_id = tipo.tipo_incidente_id
    JOIN BD_PROYECTOBD.dbo.barrio bar ON inc.barrio_id = bar.barrio_id
    JOIN Lugar lug ON lug.Lugar_id = bar.barrio_id
    JOIN Tiempo tiem ON tiem.fecha = inc.fecha AND tiem.Año = inc.anio AND tiem.Nombre_Dia = inc.nombre_dia;




	INSERT INTO Tiempo_Lugar (Tiempo_Lugar_id, Tiempo_id, Lugar_id)
SELECT 
    ROW_NUMBER() OVER (ORDER BY tiem.Tiempo_id, lug.Lugar_id) AS Tiempo_Lugar_id, 
    tiem.Tiempo_id,
    lug.Lugar_id
FROM 
    Tiempo tiem
    CROSS JOIN Lugar lug;  
