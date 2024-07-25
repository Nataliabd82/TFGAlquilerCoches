<?php
include '../conexion.php';

$email = $_GET['email'];
$password = $_GET['clave'];
$clave = password_hash($password, PASSWORD_DEFAULT);
$nombre = $_GET['nombre'];
$apellidos = $_GET['apellidos'];
$dni = $_GET['dni'];

//Compruebo que no haya un correo o dni ya registrado
$sql = "SELECT * FROM Usuarios WHERE email = ? OR dni = ?";
$consulta = $db->prepare($sql);
$consulta->execute([$email, $dni]);

if ($consulta->rowCount() == 0){
    $sql= "INSERT INTO Usuarios(dni,nombre, apellidos, email, clave)
    Values (?,?,?,?,?);";
    //Preparo la consulta
    $consulta = $db->prepare($sql);
    //Pasar a traves de un array los valores escritos en el formulario
    //Los valores se recogen por parametros en la función
    $consulta->execute(array($dni, $nombre, $apellidos, $email, $clave));
    //Compruebo que se haya registrado correctamente
    $sql = "SELECT * FROM Usuarios WHERE email = ?";
    $consulta = $db->prepare($sql);
    $consulta->execute([$email]);

    if ($consulta->rowCount() > 0){
        $respuesta = 1;
    } else{
        $respuesta = 2;
    }
    
} else {
    $respuesta = 0;
}

header ('Content-Type: application/json');

echo $respuesta;

?>
