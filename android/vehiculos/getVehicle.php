<?php
include '../conexion.php';

try {
    // Preparo la consulta 
    $sql = "SELECT * FROM Vehiculos";
    $stmt = $db->prepare($sql);

    // Ejecuto la consulta 
    $stmt->execute();

    // Buscar todos los vehículos
    $vehiculos = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // encabezado en json
    header('Content-Type: application/json');

    // Preparar el response
    echo json_encode($vehiculos);

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
?>
