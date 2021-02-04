<!DOCTYPE html>
<%@page import="es.uc3m.controladorJMS.InteraccionJMS"%>
<%@page import="java.util.ArrayList"%>
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
<%
String user = (String) request.getSession().getAttribute("user");
ArrayList<String> users = new InteraccionJMS().getUserChat(user);
%>
    <header>
        <img class="header_img" src="images/bolsa-reciclada.svg" alt="Logo">
        <form class="searchbar" action="">
            <img src="images/buscar.svg" alt="searchLogo">
            <input type="text" name="search" id="search" placeholder="Terminos de busqueda">
            <input type="submit" style="position: absolute; left: -9999px; width: 1px; height: 1px;" tabindex="-1"/>
        </form>
        <div class="chat">
            <img src="images/mensajero.svg" alt="chat icon">
            <span>Chat</span>
        </div>
        <div class="shopping_cart">
            <img src="images/shopping-cart.svg" alt="shopping cart">
            <div class="dropdown_shopping">
                <div class="itemToBuy">
                    <img src="images/ejemplo_pantalon.jpg" alt="icono compra">
                    <div class="info_shopping">
                        <span>AA</span>
                        <br>
                        <span>Precio</span>
                    </div>
                </div>
                <div class="itemToBuy">
                    <img src="images/ejemplo_pantalon.jpg" alt="icono compra">
                    <div class="info_shopping">
                        <span>asdjnaksdjnad</span>
                        <br>
                        <span>Precio</span>
                    </div>
                </div>
                <div class="buy_items">
                    <a href="">Comprar Articulos</a>
                </div>
            </div>
        </div>
        <div class="user" id="a">
            <img src="images/usuario.svg" alt="user icon">
        </div>
    </header>

    <section class="main_info">
        <h2>Chats:</h2>
        <%
        for(String user_chat : users){%>

            <div class="chat_person" id="<%=user_chat%>">
                <img src="images/usuario.svg" alt="">
                <p><%=user_chat %></p>
            </div>
        <%}
        %>
    </section>
    
    <script src="scripts/script.js"></script>
</body>
</html>