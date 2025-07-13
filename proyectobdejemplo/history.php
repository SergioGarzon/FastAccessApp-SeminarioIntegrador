<?php
    require_once('db.php'); // Asegúrate de que este archivo conecta correctamente a tu base de datos
    mysqli_select_db($connected, $bdd); // Selecciona tu base de datos

    // MODIFICACIÓN CLAVE AQUÍ: Seleccionamos solo 'date_time' y 'id_usuario'
    $consulta = mysqli_query($connected, "SELECT date_time, id_usuario FROM historyuser") or
    die(mysqli_error($connected)); // Mejor pasar $connected para mensajes de error más detallados

    if(mysqli_num_rows($consulta) == 0) {
        echo "ERROR: No se encontraron registros en la tabla 'historyuser'.";
    } else {
        $allTableDataString = ""; // Aquí almacenaremos toda la data como una sola cadena

        // Recorrer cada fila de resultados
        while ($row = mysqli_fetch_assoc($consulta)) {
            $rowDataString = ""; // Para almacenar los datos de la fila actual como una cadena

            // Recorrer cada columna (clave => valor) en la fila actual
            foreach ($row as $columnName => $columnValue) {
                // Formatear cada par columna: valor. Puedes ajustar el formato aquí.
                $rowDataString .= $columnValue . " | ";
            }
            
            // Eliminar el " | " extra al final de cada fila
            $rowDataString = rtrim($rowDataString, " | ");

            // Añadir la fila formateada a la cadena general, seguida de un salto de línea para legibilidad
            $allTableDataString .= $rowDataString . "\n"; 
        }
        
        // Mostrar toda la data de la tabla como una sola cadena
        echo "" . $allTableDataString . ""; // Usamos <pre> para mantener los saltos de línea y el formato
    }
    
    mysqli_close($connected); // Cierra la conexión a la base de datos
?>