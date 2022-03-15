<!DOCTYPE html>  <!-- l'arxiu inicial de la web, aqui es fara el formulari per logejar-se, i accedir a la portada de la web -->
<html lang="ca">
    <head>
        <meta charset="UTF-8" />
        <title>Login</title>
        <link rel="stylesheet" href="index_estil.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400&display=swap" rel="stylesheet">
    </head>
    <body>
        <main>  <!-- tots els pagines estan ordenats per header, main, footer, etc..., per millor orden del codi, tambe tots els pagines son reponsive -->
            <form action="login.php" method="post"> <!-- Al enviar el formulari ens envia al document login.php -->
                <h1>Iniciar Sessió</h1>

                <?php if (isset($_GET['error'])) {?>  <!-- misstge d'error, en cas que ens surt un error ens trucara aquest missatge -->
                    <p class="error"><?php echo $_GET['error']; ?></p>
                <?php } ?>

                <label for="user">Usuari</label>
                <input type="text" id="user" name="user" placeholder="Posa el teu nom usuari"/>
                <label for="password">Contrasenya</label>
                <input type="password" id="password" name="password" placeholder="Posa la teva contrasenya" />
                <input type="submit" id="button" value="Entrar"/>
            </form>
            
        </main>
        <footer>
            <a>Copyright © 2022 BestGym, Inc.</a>
        </footer>
    </body>
</html>