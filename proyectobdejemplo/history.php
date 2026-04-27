<?php
    require_once('db.php');
    mysqli_select_db($connected, $bdd); 

    $id_usuario = $_REQUEST['id_usuario']; 
    $array_string = [];

    $consulta = mysqli_query($connected, "SELECT date_time FROM historyuser WHERE id_usuario = " . $id_usuario . " ORDER BY date_time ASC") or
    die(mysqli_error($connected));

    if(mysqli_num_rows($consulta) == 0) 
        array_push($array_string, "Sin registros");
    else {
        $allTableDataString = ""; 

        while ($row = mysqli_fetch_assoc($consulta)) {
            foreach ($row as $columnName => $columnValue) {
                array_push($array_string, $row['date_time']);
            }            
        }
        
        //echo $array_string;
        header('Content-Type: application/json');
        echo json_encode($array_string);
    }
    
    mysqli_close($connected); 
?>