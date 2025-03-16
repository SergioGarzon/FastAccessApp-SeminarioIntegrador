<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name_user = $_POST['name_user'];

    require_once 'db.php';

    $sql = "SELECT * FROM users WHERE name_user = '$name_user'";

    $response = mysqli_query($conn, $sql);
    $result = array();
    $result['login'] = array();

    if (mysqli_num_rows($response) === 1) {
        $row = mysqli_fetch_assoc($response);

        $index['id_usuario'] = $row['id_usuario'];
        $index['name_user'] = $row['name_user'];
        $index['password_user'] = $row['password_user'];
        $index['value_access'] = $row['value_access'];
        $index['id_person'] = $row['id_person'];

        array_push($result['login'], $index);

        $result['success'] = "1";
        $result['message'] = "success";
    } else {
        $result['success'] = "0";
        $result['message'] = "Error";
    }

    echo json_encode($result);
    mysqli_close($conn);
}
?>