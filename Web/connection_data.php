<?php

$user="root";
$password="";
$database="gim";
$server="localhost";

$db = mysqli_connect($server, $user, $password, $database); //  Cridem la conexio amb la base de dades

if (!$db) {     //  en cas de que ens falla ens sortira el missatge de baix
    echo "Connection failed!";
}

?>