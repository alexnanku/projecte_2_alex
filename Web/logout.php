<?php

session_start();    /*Com sempre cridem start per iniciar sessio, o continuar la sessio ja iniciada*/ 
                    /*Com que es un php per sortir del compte lo que farem es borar i anular nostra sessio, 
                    ja que cada altre pagina menos el index validar si tenim les varibles de sessio ficades correctament*/

session_unset();    /*unset, anular tots els variables de $_SESSION[]*/
session_destroy();  /*destroy, destrueix tota la informació de sessio actual*/

header("Location: index.php");  /*al final sempre ens portara al index*/

?>