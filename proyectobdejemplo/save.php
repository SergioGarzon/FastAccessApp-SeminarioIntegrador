<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        require_once("db.php");

        $nombreUsuario = $_POST["nombreUsuario"];
        $passwordUser = $_POST["passwordUser"];
        $accesoValor = $_POST["accesoValor"];
        $IdPersona = $_POST["IdPersona"];

        $query = "INSERT INTO user (nombreUsuario, passwordUser, accesoValor, IdPersona) VALUES ('$nombreUsuario', '$passwordUser', '$accesoValor', '$IdPersona')";

        $result = $mysql->query($query);

        if($result === TRUE){
           echo "Usuario creado";
        } else {
            echo "ERROR";
        }

        $mysql->close();
    }

    

?>