USE BD_MULTIDIMENSIONAL;

CREATE TABLE Lugar (
    Lugar_id VARCHAR(50) PRIMARY KEY,  
    Nombre_Barrio VARCHAR(255),
    Comuna SMALLINT
);

CREATE TABLE Tiempo (
    Tiempo_id INT PRIMARY KEY,
    fecha DATETIME,
    Hora SMALLINT,
    Nombre_Dia VARCHAR(50),
    Mes SMALLINT,
    Año SMALLINT
);

CREATE TABLE Tipo_Incidente (
    Tipo_incidente_id INT PRIMARY KEY,
    Descripcion VARCHAR(255)
);

CREATE TABLE Tiempo_Lugar (
    Tiempo_Lugar_id INT PRIMARY KEY,
    Tiempo_id INT,
    Lugar_id VARCHAR(50),  
    FOREIGN KEY (Tiempo_id) REFERENCES Tiempo(Tiempo_id),
    FOREIGN KEY (Lugar_id) REFERENCES Lugar(Lugar_id)
);

CREATE TABLE Incidente (
    id_incidente INT PRIMARY KEY,
    Lugar_id VARCHAR(50), 
    Tiempo_id INT,
    Tiempo_Lugar_id INT,
    Tipo_incidente_id INT,
    Cant_Incidentes INT,      
    Cant_Uso_arma INT,       
    Cant_Uso_moto INT,       
    FOREIGN KEY (Lugar_id) REFERENCES Lugar(Lugar_id),
    FOREIGN KEY (Tiempo_id) REFERENCES Tiempo(Tiempo_id),
    FOREIGN KEY (Tiempo_Lugar_id) REFERENCES Tiempo_Lugar(Tiempo_Lugar_id),
    FOREIGN KEY (Tipo_incidente_id) REFERENCES Tipo_Incidente(Tipo_incidente_id)
);
