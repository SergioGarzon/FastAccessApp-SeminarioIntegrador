<?php

    require_once('db.php');
    mysqli_select_db($connected, $bdd);

    
    $id_usuario = $_REQUEST['id_usuario'];
    $date_time = $_REQUEST['date_time'];

    $sql = "INSERT INTO historyuser (date_time, id_usuario)  VALUES ('$date_time', $id_usuario)";

    
    if (mysqli_query($connected, $sql)) {
         echo "AGREGADO";

    } else {
        echo "ERROR";
    }
    
    mysqli_close($connected); 
?>