<?php

    $mysql = new mysqli("localhost", "root", "", "proyectobdejemplo");

    if($mysql -> connect_error){
        die("Failed to connect". $mysql->connect_error);
        
    } else {
        echo "Conectado correctamente";
    }    
?>