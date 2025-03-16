<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $name_user = $_POST['name_user'];
    $password = $_POST['password_user'];
    $value_access = $_POST['value_access'];
    $id_person = $_POST['id_person'];

    $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'db.php';

    $sql = "INSERT INTO users (name_user, password_user, value_access, id_person) VALUES ('$name_user', '$password', $value_access, $id_person)";

    if (mysqli_query($conn, $sql)) {
        $result["success"] = "1";
        $result["message"] = "success";

    } else {
        $result["success"] = "0";
        $result["message"] = "error";
    }

    echo json_encode($result);
    mysqli_close($conn);
}
?>