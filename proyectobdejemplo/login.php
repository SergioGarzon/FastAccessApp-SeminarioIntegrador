<?php

    require_once('db.php');
    mysqli_select_db($connected, $bdd);

    $name_user = $_REQUEST['name_user'];
    $password_user = $_REQUEST['password_user'];

    $result = array();
    $result['login'] = array();
   
    if($name_user == "" || $password_user == "") {
        echo "ERROR1";
    } else {
        $consulta = mysqli_query($connected, "SELECT * FROM users WHERE name_user = '" . $name_user . "' AND '" . $password_user . "'") or
        die(mysqli_error());

        if(mysqli_num_rows($consulta) == 0) {
            echo "ERROR2";
        } else {
            $row = mysqli_fetch_assoc($consulta);

            echo "".$row['id_usuario']."/".$row['name_user']."+".$row['value_access'];          
        }
    }

        mysqli_close($connected); 
?>