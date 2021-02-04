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
<body>

    
<%
Item item = (Item) request.getAttribute("item");
String url = "data:image/png;base64,"+Base64.getEncoder().encodeToString(item.getImagen());
%>
	<!--Remove Item Alert-->
    <div class="changebg"></div>
    <div class="removeItemAlert">
        <form class="deleteItemForm" action="deleteItem.html" method="POST">
            <div class="close_tag">
                <img src="images/close.svg" alt="close_tag">
            </div>
            <p>Esta seguro que desea eliminar este item?</p>
            <input type="hidden" name="idItem" value="<%=item.getId().getIditems()%>">
            <input id="confirm_deleteItem" type="submit" value="Borar Item">
        </form>
    </div>
    
    <!--Remove Item Alert ends-->
    <!--Edit item form -->
    <form action="editItem.html" method="POST" class="edititem_form">
        <div class="close_tag">
            <img src="images/close.svg" alt="close_tag">
        </div>
        <div class="inputs">
            <label for="nombre">Nombre: </label>
            <input type="text" name="nombre" id="nombre" value="<%=item.getNombre()%>">
        </div>
        <div class="inputs">
            <label for="category">Categoría:</label>
            <select name="category" id="category">
                <option value="sport" selected>deportiva</option>
                <option value="formal">Formal</option>
                <option value="pijama">Pijama</option>
            </select>
        </div>
        <div class="inputs">
            <label for="description">Descripción:</label>
            <textarea name="description" id="description" cols="30" rows="10"><%=item.getDescripcion()%></textarea>
        </div>
        <div class="inputs">
            <label for="price">Precio:</label>
            <input type="number" name="price" id="price" value="<%=item.getPrecio()%>">
        </div>
       	<input type="hidden" name="idItem" value="<%=item.getId().getIditems()%>">
        <div class="inputs">
            <input type="submit" value="Edit Item">
        </div>
    </form>
    <!--Edit item form end -->
    
    <header>
        <img class="header_img" src="images/bolsa-reciclada.svg" alt="Logo">
        <div class="searchbar">
            <img src="images/buscar.svg" alt="searchLogo">
            <input type="text" name="search" id="search" placeholder="Terminos de busqueda">
        </div>
        <div class="chat">
            <img src="images/mensajero.svg" alt="chat icon">
            <span>Chat</span>
        </div>
        <div class="user" id="a">
            <img src="images/usuario.svg" alt="user icon">
        </div>
    </header>

    <section class="main_info">
        <h2><%=item.getNombre()%></h2>
        <div class="item_info">
            <div class="img_container">
                <img src="<%=url%>" alt="imagen item">
            </div>
            <div class="item_text">
                <p class="description"><%=item.getDescripcion()%></p>
                <div class="owner_info">
                    <h4 class="owner">Nombre propietario: <%=item.getUsuario().getNombre() %></h4>
                    <span>Email: <%=item.getUsuario().getEmail() %></span>
                </div>
                <h4 class="item_price">Precio: <%=item.getPrecio() %>&euro;</h4>
            </div>
            <%
            String user = (String) request.getSession().getAttribute("user");
            if(!item.getUsuario().getEmail().equals(user)){%>
	            <div class="buy_button">
	                <form action="addToCart.html" method="POST">
	                    <input type="hidden" name="itemID" value="<%=item.getId().getIditems()%>">
	                    <input type="submit" value="comprar">
	                </form>
	            </div>
	            <div class="send_message">
	                <form action="chat.html" method="POST">
	                    <input type="hidden" name="reciver" value="<%=item.getId().getEmailDueño()%>">
	                    <input type="submit" value="Enviar mensaje">
	                </form>
	            </div>
            <%}else{%>
	            <div class="editItem">
	                <div class="editItem_button">
	                    <p>Editar Articulo</p>
	                </div>
	                <div class="deleteItem_button">
	                    <p>Eliminar Artículo</p>
	                </div>
	            </div>
            <%}
            %>
        </div>
    </section>

    <script src="scripts/script.js"></script>
</body>
</html>