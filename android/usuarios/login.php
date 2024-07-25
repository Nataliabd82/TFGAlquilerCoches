<?php
include '../conexion.php';

$email = $_GET['email'];
$password = $_GET['password'];
$sql = "SELECT u.nombre, u.apellidos, u.email, u.clave, u.dni, u.rol 
FROM Usuarios u 
WHERE u.email = ?;";
//Preparo la consulta
$consulta = $db->prepare($sql);
//Pasar a traves de un array los valores escritos en el formulario
//Los valores se recogen por parametros en la función
$consulta->execute(array($email));
$datos = array();

//si la consulta devuelve algo, es que todo va bien
if ($consulta->rowCount() > 0){

        $row = $consulta->fetch();
        $nombre = $row['nombre'];
        $apellidos = $row['apellidos'];
        $email = $row['email'];
        $clave = $row['clave'];
        $dni = $row['dni'];
        $rol = $row['rol'];
       if (password_verify($password, $clave)) {

        $datos[] = array(
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'email' => $email,
            'clave' => $clave,
            'dni' => $dni,
            'rol' => $rol,
            
        );
    }
    }


$recordFiltered = count ($datos);
$recordsTotal = $recordFiltered;

header ('Content-Type: application/json');

echo json_encode($datos);

?>

