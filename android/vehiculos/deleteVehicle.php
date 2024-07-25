<?php
include '../conexion.php';

// Obtener los valores de los atributos del vehículo a través de la URL
$id = $_GET['id'];

// Consulta SQL con marcadores de posición (?)
$sql = "DELETE FROM Vehiculos WHERE ID = ?";
$stmt = $db->prepare($sql);

// Ejecutar la consulta con los valores proporcionados
$stmt->execute(array($id));

header('Content-Type: application/json');
echo json_encode(["message" => "Vehículo eliminado correctamente"]);
?>
