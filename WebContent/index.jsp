<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
    <title>SecondTry</title>
    <link rel="stylesheet" href="style/style.css">
    <link rel="icon" type="image/png" href="images/favicon_bolsa.png" sizes="16x16">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>    
</head>
<body>
    <header>
        <img class="header_img" src="images/bolsa-reciclada.svg" alt="Logo">
        <div class="searchbar">
            <img src="images/buscar.svg" alt="searchLogo">
            <input type="text" name="search" id="search" placeholder="Terminos de busqueda">
        </div>
        <div class="sign sign_up">
            <a href="#">Registrarse</a>
        </div>
        <div class="sign sign_in">
            <a href="#">Iniciar Sesion</a>
        </div>
    </header>

    <!--Non visibles Log In and Sign Up Forms-->
    <div class="changebg"></div>
    <form action="register.html" class="sign_forms" method="POST" id="regist">        
        <div class="close_tag">
            <img src="images/close.svg" alt="close_tag">
        </div>
        <h3>¡Welcome to our great community!</h3>
        <div class="inputs">
            <input type="text" name="name" id="name" placeholder="user name">
        </div>
        <div class="inputs">
            <input type="text" name="surnames" id="surnames" placeholder="user surnames">
        </div>
        <div class="inputs">
            <input type="email" name="email" id="email" placeholder="example@email.com">
        </div>
        <div class="inputs">
            <input type="text" name="city" id="city" placeholder="city">
        </div>
        <div class="inputs">
            <input type="password" name="password" id="password" placeholder="password">
        </div>
        <div class="inputs">
            <input type="password" name="confirm_password" id="confirm_password" placeholder="confirm password">
        </div>
        <div class="inputs">
            <input type="submit" value="Sign Up" name="execute">
        </div>
    </form>
    <form action="log.html" class="sign_forms log_in" method="POST" id="login">
        <div class="close_tag">
            <img src="images/close.svg" alt="close_tag">
        </div>
        <h3>�So nice to hear from you!</h3>
        <div class="inputs">
            <input type="email" name="email" id="log_email" placeholder="example@email.com">
        </div>
        <div class="inputs">
            <input type="password" name="password" id="log_password" placeholder="password">
        </div>
        <div class="inputs">
            <input type="submit" value="Log In" name="execute">
        </div>
    </form>
    <!--End forms-->

    <section>
        <h1>SecondTry</h1>
        <div class="page_info">
            <div class="left_content">
                <p>
                    En SecondTry le damos una segunda oportunidad a las cosas que ya no usas! Convierte esos objetos del final del fondo del amrario en dinero rápido.
                </p>
                <div class="info_sign">
                    <div class="sign sign_up">
                        <a href="#">Registrarse</a>
                    </div>
                    <div class="sign sign_in">
                        <a href="#">Iniciar Sesion</a>
                    </div>
                </div>
            </div>
            <img src="images/fotoremovedbg2.png" alt="Logo compra">
        </div>
    </section>
    <script src="scripts/script.js"></script>
</body>
</html>