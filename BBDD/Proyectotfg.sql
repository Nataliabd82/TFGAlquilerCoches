DROP DATABASE IF EXISTS Proyectotfg;
CREATE DATABASE Proyectotfg;
USE Proyectotfg;


-- tabla vehiculo
CREATE TABLE Vehiculos (
    ID INT AUTO_INCREMENT,
    Marca VARCHAR(50) NOT NULL,
    Modelo VARCHAR(50) NOT NULL,
    Ano INT,
    Kilometros INT,
    Precio DECIMAL(10,2),
    Ciudad VARCHAR(50) NOT NULL,
    Plazas INT,
    Transmision VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

-- tabla usuario
CREATE TABLE Usuarios (
    ID INT AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Apellidos VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    DNI VARCHAR(9) NOT NULL,
    PRIMARY KEY (ID)
);

-- tabla reserva
CREATE TABLE Reservas (
    ID INT AUTO_INCREMENT,
    UsuarioID INT,
    VehiculoID INT,
    FechaInicio DATE,
    HoraInicio TIME,
    FechaFin DATE,
    HoraFin TIME,
    PRIMARY KEY (ID),
    FOREIGN KEY (UsuarioID) REFERENCES Usuarios(ID),
    FOREIGN KEY (VehiculoID) REFERENCES Vehiculos(ID)
);

-- inserción de datos en tabla vehículo
INSERT INTO Vehiculos (Marca, Modelo, Ano, Kilometros, Precio, Ciudad, Plazas, Transmision) VALUES 
('Ford', 'Mondeo', 2020, 10000, 10000.00, 'Toledo', 5, 'Manual'),
('Ford', 'Focus', 2021, 20000, 20000.00, 'Madrid', 4, 'Automático'),
('Ford', 'Fiesta', 2022, 30000, 30000.00, 'Sevilla', 6, 'Manual'),
('Seat', 'León', 2021, 500000, 50000.00, 'Toledo', 5, 'Automático'),
('Seat', 'Ibiza', 2021, 500000, 50000.00, 'Madrid', 5, 'Automático'),
('Seat', 'Toledo', 2021, 500000, 50000.00, 'Barcelona', 5, 'Automático'),
('BMW', 'Serie 1', 2021, 500000, 50000.00, 'La Rioja', 5, 'Automático'),
('BMW', 'Serie 2', 2021, 500000, 50000.00, 'Almería', 5, 'Automático'),
('BMW', 'x6', 2021, 500000, 50000.00, 'Granada', 5, 'Automático'),
('BMW', 'x5', 2021, 500000, 50000.00, 'León', 5, 'Automático'),
('BMW', 'i30', 2021, 500000, 50000.00, 'Cádiz', 5, 'Automático'),
('Mercedes', 'AMG GT', 2021, 500000, 50000.00, 'Lugo', 5, 'Automático'),
('Mercedes', 'AMG ONE', 2021, 500000, 50000.00, 'Oviedo', 5, 'Automático'),
('Mercedes', 'CLA', 2021, 500000, 50000.00, 'Santander', 5, 'Automático'),
('Mercedes', 'Clase A', 2021, 500000, 50000.00, 'Madrid', 5, 'Automático'),
('Mercedes', 'Citan', 2021, 500000, 50000.00, 'Toledo', 5, 'Automático'),
('Hyundai', 'TUCSON', 2021, 500000, 50000.00, 'Talavera de la Reina', 5, 'Automático'),
('Hyundai', 'i20', 2021, 500000, 50000.00, 'San Sebastián', 5, 'Automático'),
('Hyundai', 'Bayon', 2021, 500000, 50000.00, 'Ciudad Rodrigo', 5, 'Automático'),
('Hyundai', 'Kona', 2021, 500000, 50000.00, 'Benidorm', 5, 'Automático'),
('Citroen', 'C3', 2021, 500000, 50000.00, 'Valencia', 5, 'Automático'),
('Citroen', 'C5', 2021, 500000, 50000.00, 'Alicante', 5, 'Automático'),
('Citroen', 'C4', 2021, 500000, 50000.00, 'Oviedo', 5, 'Automático'),
('Citroen', 'Berlingo', 2021, 500000, 50000.00, 'Lugo', 5, 'Automático'),
('Citroen', 'Jumpy Furgón', 2021, 500000, 50000.00, 'Llanes', 5, 'Automático');

-- inserción de datos en tabla usuario
INSERT INTO Usuarios (Nombre, Apellidos, Email, Password, DNI) VALUES 
('Natalia', 'Blanco', 'natalia@gmail.com', '1234', '12345678A'),
('Virginia', 'López', 'virginia@gmail.com', '1234', '23456789B'),
('Gonzalo', 'Díaz', 'gonzalo@gmail.com', '1234', '34567890C'),
('Carlos', 'Gómez', 'Carlos@gmail.com', '1234', '56789012E');

