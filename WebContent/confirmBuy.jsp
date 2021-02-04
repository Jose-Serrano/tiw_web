<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="es.uc3m.dbhandler.ShoppingCartHandler"%>
<%@page import="java.util.Base64"%>
<%@page import="es.uc3m.model.Item"%>
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
<% 
    String user = (String) request.getSession().getAttribute("user");
    System.out.println(user);
	ShoppingCartHandler shoppingCarhH = new ShoppingCartHandler();
	List<Item> shopping_cartItems = shoppingCarhH.shoppingCart_items(user);
	System.out.println("Items del carrito del usuario: "+user+" son: "+shopping_cartItems.size());
%>
<body>
    <header>
        <img class="header_img" src="images/bolsa-reciclada.svg" alt="Logo">
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
        <h2>Articulos a comprar</h2>
        <form action="confirmBuy.html" method="post">
        <%
        if(shopping_cartItems != null && shopping_cartItems.size() > 0){
        	int finalPrize = 0;
            for(Item item : shopping_cartItems){
            	String url = "data:image/png;base64,"+Base64.getEncoder().encodeToString(item.getImagen());
	            %>
	            <div class="confirmItem">
	                <img src="<%=url%>" alt="imagen del item">
	                <div class="conf_itemInfo">
	                    <p>Nombre: <%=item.getNombre() %></p>
	                    <p>Precio: <%=item.getPrecio() %></p>
	                </div>
	            </div>
            <%}%>  
            <p>Precio Final: <%=finalPrize %></p>
            <input class="confirmButton" type="submit" value="Confirmar Compra">      	
        <%}
        %>
        </form>


    </section>

</body>