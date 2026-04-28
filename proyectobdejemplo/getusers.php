<?php
    require_once('db.php');
    mysqli_select_db($connected, $bdd); 

    $sql = "SELECT id_usuario, name_user FROM users";
    $resultado = $connected->query($sql);
    $usuarios = array();


    while($fila = $resultado->fetch_assoc()) {
        $usuarios[] = array(
            "id_usuario" => (int)$fila['id_usuario'],
            "name_user" => $fila['name_user']
        );
    }
    
    header('Content-Type: application/json');
    echo json_encode($usuarios);
?>