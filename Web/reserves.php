<?php
    session_start();    /*  Continuem la sessio iniciada    */

    include "connection_data.php";  /*  importem la connexio amb bd de l'altre arxiu*/

    if(isset($_SESSION['DNI'])&& isset($_SESSION['USUARI'])){   /*  Validem el usuari   */
?>

<!DOCTYPE html> <!-- En aquesta pagina es faran reserves d'activitats -->
<html lang="ca">
    <head>
        <meta charset="UTF-8" />
        <title>Activitats</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="reserves_estil.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">   <!-- link d'icones per footer -->
    </head>
    <body>
        <nav id="myHeader">
            <div class="nav_logo">
                <a href="portada.php"><img src="Imatges/logo_small_icon_only.png" alt="logo gimnas" /></a>
            </div>
            <a href="javascript:void(0);" class="icon" onclick="myFunction1()">
                <i class="fa fa-bars"></i>
            </a>
            <div class="opcio" id="myTopnav">
                <a id="id_reserves">Reserves</a>
                <a href="Competicions.html">Competicions</a>
            </div>
            <div class="Usuari" >
                <h2 onclick="myFunction2()">Hola, <?php echo $_SESSION['NOM']; ?> <?php echo $_SESSION['COGNOM']; ?></h2>
                <div class="opcio_usuari" id="MyUserList">
                    <div class="llista_opcio">
                        <a href="meves_reserves.php">Meves reserves</a>
                        <a href="dades_personals.php">Dades personals</a>
                        <a href="logout.php">Sortir del compte</a>
                    </div>
                </div>
            </div>
        </nav>
        <main>
            <?php
                $color; /*  Creem variable color    */
                /*  Aixo es la consulta que agafara dades necessaries per mostrar informacio de activitat/sala/monitor, les activitat agafara sol entre 24h i 1h  abans del hora inici   */
                $sql = "SELECT * FROM ACTIVITATS A, MONITORS M, SALES S WHERE S.DNI_MONITOR=M.DNI_MONITOR AND S.NUM_SALA=A.NUM_SALA AND (NOW() + INTERVAL 1 HOUR)<HORA_INICI AND (NOW() + INTERVAL 24 HOUR)>HORA_INICI  GROUP BY NOM_ACT ORDER BY HORA_INICI, DURADA";

                $result = mysqli_query($db, $sql);  /*  Tirem la consulta a bd  */
                
                
                if (mysqli_num_rows($result) !=0) { /*  Revisem si hi ha activitats, si no hi ha ens tirara un missatge */
                    
                    $row = mysqli_fetch_assoc($result); /*  Guardem el resultat en linia com a row  */
                    ?>
                    <div class="all_main">
                        <?php if (isset($_GET['reserva'])) {?>  <!-- misstge d'error, sortira en cas de que ja tenim la reserva feta, o si al mysql no arriba la consulta -->
                            <p class="reserva_incorrecte"><?php echo $_GET['reserva']; ?></p>
                        <?php } ?>
                        <form action="reserves.php" method="POST">  <!--    Hem posat un formulari per les checkbox d'activitats    -->
                    <?php
                            foreach($result as $row){   /* Ara el resultat en linies, ensenyara com a $row, anira repetinse i ens posara els mateixos sections en html, que activitats en bd    */
                                if($row['NOM_ACT']=='Fitness'){     /*  Aqui fem comprovacions del nom d'activitat, depenent del nom ens canvia el color de fons    */
                                    $color=null;
                                    $color='gris';      /*  si es fitness la variable del $color, es dira gris, sino un altre color*/
                                }elseif($row['NOM_ACT']=='Yoga'){
                                    $color=null;
                                    $color='verd';
                                }elseif($row['NOM_ACT']=='Body_pump'){
                                    $color=null;
                                    $color='groc';
                                }elseif($row['NOM_ACT']=='Pilates'){
                                    $color=null;
                                    $color='taronja';
                                }elseif($row['NOM_ACT']=='Natació_lliure'){
                                    $color=null;
                                    $color='blau';
                                }elseif($row['NOM_ACT']=='Cycling'){
                                    $color=null;
                                    $color='vermell';
                                }
                        $activitats [] = $row['NOM_ACT'];   /*  els noms activitat ens apuntar en una Array */
                        $activitat = $activitats[count($activitats)-1]; /*  com a variable activitat s'apuntara sempre l'ultim lalor de Array*/
                    ?>
                        <section>
                            <div class="<?php echo $color ?>" id="color">   <!--    En lloc de class posem variable color, ja que al css per cada com de color canviem el fons  -->
                                <article>
                                    <?php echo "<img src='Imatges/".$row['IMG']."'>" ?>     <!--    Aqui posem nostra imatge, ja que en bd posem el nom d'imatge, i aqui la ruta    -->
                                </article>
                                <article>
                                    <h3><?= $row['NOM_ACT']; ?></h3>
                                    <p id="desc"><?= $row['DESCRIPCIO']; ?></p>
                                    <hr />
                                    <p>Duració: <?= $row['DURADA']; ?>Min, en la sala <?= $row['NUM_SALA'];?></p>
                                    <hr />
                                    <p>Monitor: <?= $row['NOM_MONITOR']; ?> <?= $row['COGNOM_MONITOR']; ?></p>
                                    <hr />
                                    <p>De <?= $row['HORA_INICI'] ?> fins <?= $row['HORA_FI']?></p>
                                    <div class="class_check">
                                        <label for="check_reserve">Seleccionar activitat</label>
                                        <input type="checkbox" name="<?= $activitat; ?>" id="check_reserve"/>   <!--    El check fara que nom de la activitat es guardi com a reserva   -->
                                    </div>
                                </article>
                            </div>
                        </section>
                    <?php  
                                if(isset($_POST[$activitat])){  /*  Aqui revisem els check que hem fet*/
                                    /*  Tirem un insert amb DNI de sessio, num activitat, hora inici i fi    */
                                    $sql = "INSERT INTO inscriuen (DNI, NUM_ACTIVITAT, HORA_INICI, HORA_FI, DATA_ACT) VALUES ('$_SESSION[DNI]','$row[NUM_ACTIVITAT]','$row[HORA_INICI]','$row[HORA_FI]','$row[DIA]')"; 
                                    $sql_insert = mysqli_query($db, $sql);
                                    
                                    error_reporting(0); /*  Perque no ens surti cap warning    */

                                    if($sql_insert){    /*  Si es tirara la consulta a bd fara lo següent   */
                                        header("Location: meves_reserves.php?reserva=Activitat reservada correctament");    /*  ens dira que ha guardat correctament i ens enllaç a meves_reserves.php  */
                                    }else{  /*  Sino surt un error  */
                                        header("Location: reserves.php?reserva=S'ha produït un error al hora de fer la reserva");
                                    }
                                }

                            }
                    ?>
                            <input type="submit" name="reservar" id="boto_reserva" value="Reservar"/>   <!-- boto per tira el formulari -->
                        </form>
                    </div>

            <?php
                }else{  /*  en cas de que no hi ha cap activitat a les hores indicats:   */
                    $noact = 'No hi han activitats per reservar';
                    echo $noact;
                }
            ?>

            
                
            
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

    }else{  /*  Si es un usuari incorrecte ens enllaça a index.php, i tanca la sessio*/
        header("Location: index.php");
        exit();
    }

?>
