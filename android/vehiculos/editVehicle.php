<?php
include '../conexion.php';

$id = $_POST['id'];
$marca = $_POST['marca'];
$modelo = $_POST['modelo'];
$ano = $_POST['ano'];
$kilometros = $_POST['kilometros'];
$precio = $_POST['precio'];
$ciudad = $_POST['ciudad'];
$plazas = $_POST['plazas'];
$transmision = $_POST['transmision'];

// Consulta SQL con marcadores de posición (?)
$sql = "UPDATE Vehiculos SET Marca = ?, Modelo = ?, Ano = ?, Kilometros = ?, Precio = ?, Ciudad = ?, Plazas = ?, Transmision = ? WHERE ID = ?";
$stmt = $db->prepare($sql);

// Ejecutar la consulta con los valores proporcionados
$stmt->execute([$marca, $modelo, $ano, $kilometros, $precio, $ciudad, $plazas, $transmision, $id]);

header('Content-Type: application/json');
echo json_encode(["message" => "Vehículo actualizado correctamente"]);
?>
