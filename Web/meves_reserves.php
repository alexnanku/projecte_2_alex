<?php
    session_start();    /*  Continuem la sessio ja iniciada */

    include "connection_data.php";  /*  importem nostre connexio amb bd*/

    if(isset($_SESSION['DNI'])&& isset($_SESSION['USUARI'])){   /*  Validem que l'usuari sigui correcte */
?>

<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta charset="UTF-8" />
        <title>Meves Reserves</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="reserves_estil.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <nav id="myHeader">
            <div class="nav_logo">
                <a href="portada.php"><img src="Imatges/logo_small_icon_only.png" alt="logo gimnas" /></a>
            </div>
            <a href="javascript:void(0);" class="icon" onclick="myFunction1()"> <!-- .icon es un element ivisible fins que l'amplada de pagina no sera 600px, sera una icona que al fer clic activa el js per mostrar un menu d'opcions -->
                <i class="fa fa-bars"></i>
            </a>
            <div class="opcio" id="myTopnav">   <!-- Aquest opcions s'ensenyaran normal, pero al amplada 600px s'amaguen fins que no es fagi click a la icona -->
                <a href="reserves.php">Reserves</a>
                <a href="Competicions.html">Competicions</a>
            </div>
            <div class="Usuari" >
                <h2 onclick="myFunction2()">Hola, <?php echo $_SESSION['NOM']; ?> <?php echo $_SESSION['COGNOM']; ?><!-- auqi estara el PHP amb el nom usuari, sera un desplegable amb opcioms de meves reserves i visualitzar dades personals, tambe posarem opció per sortir --></h2>
                <div class="opcio_usuari" id="MyUserList">  <!-- Opcions que se despleguen quan es fagi clic, funcionara tal amb pantalla normal com amb 600px d'amplada -->
                    <div class="llista_opcio">
                        <a href="meves_reserves.php">Meves reserves</a>
                        <a href="dades_personals.php">Dades personals</a>
                        <a href="logout.php">Sortir del compte</a>
                    </div>
                </div>
            </div>
        </nav>
        <main>
            <h1>Activitats reservades: </h1>
            <?php if (isset($_GET['reserva'])) {?>  <!-- misstge de confirmació -->
                <p class="reserva_correcte"><?php echo $_GET['reserva']; ?></p>
            <?php } ?>
            <?php if (isset($_GET['anular'])) {?>  <!-- misstge de confirmació -->
                <p class="anular_correcte"><?php echo $_GET['anular']; ?></p>
            <?php } ?>
            <?php if (isset($_GET['anularin'])) {?>  <!-- misstge d'error -->
                <p class="anular_incorrecte"><?php echo $_GET['anular']; ?></p>
            <?php } ?>
            <div class="main">
                <div class="llista">
                    <ul>
                        <li><b>Nom activitat: </b></li>
                        <li>Data: <br />
                            Hora inici: 
                        </li>
                        <li>Hora fi: </li>
                        <li></li>
                    </ul>
                    <?php 
                        $sql="SELECT * FROM INSCRIUEN WHERE DNI= '$_SESSION[DNI]'"; /*  consulta per mira les reserves fetes    */
                        $result = mysqli_query($db, $sql);  /*  Enviem la consulta a bd */

                        if (mysqli_num_rows($result) !=0) { /*  Revisar que cada columna no sigui null*/
                            $row = mysqli_fetch_assoc($result); /* Apunta resultat per columnes com a $row*/
                        }
                        foreach($result as $row){
                            $activitat = $row['NUM_ACTIVITAT'];
                    ?>
                        <ul>
                            <li><b><?php if($row['NUM_ACTIVITAT']==1){echo'Body Pump';}     /*  Aqui hem posat noms d'activitats comprovant per el seu num id, ja que en la consulta notenim el atribut del nom act */
                                    elseif($row['NUM_ACTIVITAT']==2){echo'Pilates';}
                                    elseif($row['NUM_ACTIVITAT']==3){echo'Yoga';}
                                    elseif($row['NUM_ACTIVITAT']==4){echo'Natació lliure';}
                                    elseif($row['NUM_ACTIVITAT']==5){echo'Fitness';}
                                    elseif($row['NUM_ACTIVITAT']==6){echo'Cycling';} ?></b></li>
                            <li><?= $row['HORA_INICI']; ?></li>
                            <li><?= $row['HORA_FI'] ?></li>
                            <li><form action="meves_reserves.php" method="POST"><button type="submit" name="<?= $activitat ?>">Anul·lar la reserva</button></form></li>   <!-- Boto per anular l'activitat -->
                        </ul>
                        <?php
                            if(isset($_POST[$activitat])){
                                $sql1="DELETE FROM `inscriuen` WHERE NUM_ACTIVITAT=$activitat"; /*  Consulta per eliminar la reserva    */
                                $anular= mysqli_query($db, $sql1);

                                if($anular){
                                    header("Location: meves_reserves.php?anular=Reserva anul·lada correctament");   /*  Missatge confirmació  */
                                }else{
                                    header("Location: meves_reserves.php?anularin=s'Ha produït un error");  /*  Missatge error  */
                                }
                            }
                        ?>
                    <?php } ?>
                </div>
            </div>
        </main>
        <footer>
            <div class="xarxes">
                <a href="#" class="fa fa-facebook"></a>
                <a href="#" class="fa fa-instagram"></a>
                <a href="#" class="fa fa-twitter"></a>
                <a href="#" class="fa fa-google"></a>
            </div>
            <div class="links">
                <a href="#">Centre de Soport</a>
                <br />
                <a href="#">Contacte</a>
                <br />
                <a href="#">Privacitat</a>
                <br />
                <a href="#">Publicitat</a>
                <br />
                <a href="#">Politica de cookies</a>
            </div>
            <div class="under_footer">
                <a>Copyright © 2022 BestGym, Inc.</a>
            </div>
        </footer>
        <script src="header_button.js"></script>
    </body>
</html>

<?php 

    }else{  /*  si la validació d'usuari es incorrecte, ens torna a index.php, i surt de sessio*/
        header("Location: index.php");
        exit();
    }

?>