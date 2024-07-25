<?php
include '../conexion.php';

// Obtener los valores de los atributos del vehículo a través de la URL
$marca = $_GET['marca'];
$modelo = $_GET['modelo'];
$ano = $_GET['ano'];
$kilometros = $_GET['kilometros'];
$precio = $_GET['precio'];
$ciudad = $_GET['ciudad'];
$plazas = $_GET['plazas'];
$transmision = $_GET['transmision'];

// Consulta SQL con marcadores de posición (?)
$sql = "INSERT INTO Vehiculos (Marca, Modelo, Ano, Kilometros, Precio, Ciudad, Plazas, Transmision) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

// Preparar la consulta
$consulta = $db->prepare($sql);

// Ejecutar la consulta con los valores proporcionados
$consulta->execute(array($marca, $modelo, $ano, $kilometros, $precio, $ciudad, $plazas, $transmision));

header('Content-Type: application/json');
echo json_encode(["message" => "Vehículo añadido correctamente"]);
?>
