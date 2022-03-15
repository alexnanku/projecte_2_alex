<?php
    session_start(); /* Continua la sessio ja iniciada  */ 

    if(isset($_SESSION['DNI'])&& isset($_SESSION['USUARI'])){   /*  valida si els camps de sessio DNI i usuari son cirrectes, si son incorrectes ens torna a pagina index.php*/
?>

<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta charset="UTF-8" />
        <title>Portada</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="portada_estil.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> <!-- Aixo es un link dels logos de xarxes socials, per el footer -->
    </head>
    <body>
        <header>
            <img src="Imatges/logo_large.png" alt="logo_gym"/>
        </header>
        <nav id="myHeader">
            <div class="nav_logo">
                <img src="Imatges/logo_small_icon_only.png" alt="logo gimnas" />
            </div>
            <a href="javascript:void(0);" class="icon" onclick="myFunction1()"> <!-- .icon es un element ivisible fins que l'amplada de pagina no sera 600px, sera una icona que al fer clic activa el js per mostrar un menu d'opcions -->
                <i class="fa fa-bars"></i>
            </a>
            <div class="opcio" id="myTopnav">   <!-- Aquest opcions s'ensenyaran normal, pero al amplada 600px s'amaguen fins que no es fagi click a la icona -->
                <a href="reserves.php">Reserves</a>
                <a href="Competicions.html">Competicions</a>
            </div>
            <div class="Usuari" >
                <h2 onclick="myFunction2()">Hola, <?php echo $_SESSION['NOM']; ?> <?php echo $_SESSION['COGNOM']; ?><!-- auqi estara el PHP amb el nom usuari, sera un desplegable amb opcioms de meves reserves, modificar dades personals i visualitzar dades personals, tambe posarem opció per sortir --></h2>
                <div class="opcio_usuari" id="MyUserList">  <!-- Opcions que se despleguen quan es fagi clic, funcionara tal amb pantalla normal com amb 600px d'amplada -->
                    <div class="llista_opcio">
                        <a href="meves_reserves.php">Meves reserves</a>
                        <a href="dades_personals.php">Dades personals</a>
                        <a href="logout.php">Sortir del compte</a>
                    </div>
                </div>
            </div>
        </nav>
        <main>  <!-- el main ens mostrara uns quants imatges amb introduccio de nostre gimnas -->
            <section class="reverse">
                <article>
                    <div class="desc_gym">
                        El nostre gymnas us ofereix activitats de tot tipus, fitness, piscines, body-pump... tenim activitat col·lectives i individuals, les col·lectives es dirigiran per un entrenador 
                    </div>
                </article>
                <article>
                    <img src="Imatges/gimnas.jpg" alt="Imatge gimnas" />
                </article>
            </section>
            <section>
                <article>
                    <img src="Imatges/piscina.png" alt="Piscina" />
                </article>
                <article>
                    <div class="desc_pis">
                        Podeu reserva qualsevol activitat disponible a la hora que us conven millor
                    </div>
                </article>
            </section>
            <section class="reverse">
                <article>
                    <div class="desc_fitness">
                        Tenim diferents sales amb equipament de tot tipus
                    </div>
                </article>
                <article>
                    <img src="Imatges/salafitnes.png" alt="Imatge de sala" />
                </article>
            </section>
            <section>
                <article>
                    <img src="Imatges/vestidor.jpg" alt="Imatge vestidor" />
                </article>
                <article>
                    <div class="desc_gym">
                        En el nostre gimnas s'apliquen mesures higièniques i mesures contra covid 
                    </div>
                </article>
            </section>
        </main>
        <footer>    <!-- el footer amb enllaços a xarxes socials i informació que podria interesa al usuari -->
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

    }else{      /*  Si no ens valider be el usuari i DNI, ens mou a index.php, i surt de la sessio*/
        header("Location: index.php");
        exit();
    }

?>


