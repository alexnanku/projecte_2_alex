<?php 

/*echo "hello";*/


    
    session_start(); // Aqui iniciem una sessió, el posarem al inici de cada pagina per poder pasa alguns variables per la sessio com a $_SESSION[]
        
    include "connection_data.php" ; //  Aqui importem la connexio a bd

    if (isset($_POST['user']) && isset($_POST['password'])) { //  Revisem que els camps de usuari i contrasenya no estiguin buides

        function validate($data){ //  funcio per validar la informacio

            $data = trim($data);    //  trim, revisa que no tingui espai, o altres caracters especials, en inici i final de variable

            $data = stripslashes($data);    //  stripslashes, neteja les barres simples(/) que trobar, i si trobar doble barra (//) la converteix en sol una 

            $data = htmlspecialchars($data);   //  converteix els caracters especials de HTML

            return $data;   //  retorn data final amb tots modificacions fetes

        }
    }

    $user = validate($_POST['user']);   //  Aqui utilitzem la funcio de validar al camp del formulari llamat 'user', li asignem variable user

    $pass = md5(validate($_POST['password']));  //  Aqui lo mateix que user pero les dades ja validades els encripte amb MD5

    if (empty($user)) {     //  si el camp de user esta buid ens surt el missatge de baix(sera un error) 

        header("Location: index.php?error=Us falta l'usuari");

        exit();     //  cridem exit per sortir del login.php

    }else if(empty($pass)){

        header("Location: index.php?error=Us falta la contrasenya");

        exit();

    }

    $sql = "SELECT * FROM CLIENTS WHERE USUARI='$user' AND PASS='$pass'";   //   La consulta per bd, les columnes de usuari, i contrasenya, les buscare per les variables que hem ficat als camps del login

    $result = mysqli_query($db, $sql);  //  iniciem la consulta, la resposta apuntem com a result

    if (mysqli_num_rows($result) === 1) {   // ens agafara linia a linia la resposta i revisa que no estigui 0

        $row = mysqli_fetch_assoc($result); // aignem $row al resultat de cada linia

        if ($row['USUARI'] === $user && $row['PASS'] === $pass) {   // revisem que el usuari de bd estigui igual que el que hem ficat al formulari i fa lo mateix per la contrasenya

            $_SESSION['USUARI'] = $row['USUARI'];   //  Aqui assignem les variables de cada linia com a $_SESSION[nom]
            
            $_SESSION['NOM'] = $row['NOM'];

            $_SESSION['COGNOM'] = $row['COGNOM'];

            $_SESSION['SEXE'] = $row['SEXE'];

            $_SESSION['DATA_NAIX'] = $row['DATA_NAIX'];

            $_SESSION['TEL'] = $row['TEL'];

            $_SESSION['EMAIL'] = $row['EMAIL'];

            $_SESSION['USUARI'] = $row['USUARI'];

            $_SESSION['PUBLICITAT'] = $row['PUBLICITAT'];

            $_SESSION['IBAN'] = $row['IBAN'];

            $_SESSION['IMPEDIMENT_FISIC'] = $row['IMPEDIMENT_FISIC'];

            $_SESSION['DNI'] = $row['DNI'];

            header("Location: portada.php");    // al verificar tot correcte ens porte a la portada

            exit(); //  i tanque el login php

        }else{

            header("Location: index.php?error=Usuari o contrasenya incorrecte"); // si el usuari i contrasenya de bd no es igual al de camp de login ens surt error

            exit();

        }

    }else{

        header("Location: index.php?error=Usuari o contrasenya incorrecte");

        exit();

    }

?>