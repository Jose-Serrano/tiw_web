<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="es.uc3m.dbhandler.ItemHandler"%>
<%@page import="es.uc3m.dbhandler.ShoppingCartHandler"%>
<%@page import="java.util.Base64"%>
<%@page import="es.uc3m.model.Item"%>
<%@page import="java.util.List"%>
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
    System.out.println(user);
	ShoppingCartHandler shoppingCarhH = new ShoppingCartHandler();
	List<Item> shopping_cartItems = shoppingCarhH.shoppingCart_items(user);
	System.out.println("Items del carrito del usuario: "+user+" son: "+shopping_cartItems.size());
	%>
    <header>
        <img class="header_img" src="images/bolsa-reciclada.svg" alt="Logo">
        <form class="searchbar" action="search.html" method="GET">
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
            <%
            if(shopping_cartItems != null){
            	for(Item item : shopping_cartItems){
            		String url = "data:image/png;base64,"+Base64.getEncoder().encodeToString(item.getImagen());
            		%>
            		<div class="itemToBuy">
	                    <img src="<%=url%>" alt="icono compra">
	                    <div class="info_shopping">
	                        <span><%=item.getNombre()%></span>
	                        <br>
	                        <span><%= item.getPrecio() %>&euro;</span>
	                    </div>
                	</div>
            	<%}
            }
            %>
                <div class="buy_items">
                    <a href="confirmBuy.jsp">Comprar</a>
                </div>
            </div>
        </div>
        <div class="user" id="a">
            <img src="images/usuario.svg" alt="user icon">
        </div>
    </header>

    <section class="main_info">
        <h2>Articulos</h2>
        <form class="filter" action="filtrar.html" method="GET">
            <div class="filter_inputs">
                <label for="category_filter">Categoria: </label>
                <select name="category_filter" id="category_filter">
                    <option value="Deportiva" selected>Deportes</option>
                    <option value="Formal">Formal</option>
                    <option value="Pijama">Pijama</option>
                    <option value="Bañadores">Bañadores</option>
                </select>
            </div>
            <div class="filter_inputs">
                <label for="prizeMax">Precio</label>
                <input type="number" min="0" name="priceMax" id="priceMax" placeholder="Precio Maximo">
                <input type="number" min="0" name="priceMin" id="priceMin" placeholder="Precio Mínimo">
            </div>
            <div class="filter_inputs">
                <img src="images/simbolo-de-herramienta-llena-de-filtro.svg" alt="filtrar icon">
                <input type="submit" value="Filtrar">
            </div>
        </form>
        <div class="container_articulos">
        	
        	<%
    		ItemHandler items = new ItemHandler();
        	List<Item> allitems = new ArrayList<Item>();
            allitems = (request.getAttribute("filter") == null) ? items.getAllItems() : (List<Item>)request.getAttribute("filter");
        	
        	if(allitems != null){
        		for(Item item : allitems){
        			String url = "data:image/png;base64,"+Base64.getEncoder().encodeToString(item.getImagen());
        			%>
        			<div class="child" id=<%=item.getId().getIditems()%>>
        			<%
        			if(item.getComprador() != null && item.getComprador() != ""){%>
        				<div class="vendido">
	                    	<span>VENDIDO</span>
	                	</div>
        			<%}%>
		                
		                <div class="img_container">
		                    <img src="<%=url%>" alt="imagen articulo">
		                </div>
		                <span><%= item.getNombre() %></span>
		                <br>
		                <span><%= item.getPrecio() %>&euro;</span>
		               <div class="buyIt">
		                   <span>Comprar</span>
		                   <img src="images/shopping-cart.svg" alt="Icono comprar">
		               </div>
	            	</div>
        		<%}
        	}
        	%>        	
        </div>
    </section>
    <div class="add_object">
        <img src="images/mas.svg" alt="add">
    </div>
    <!--Formulario para subir elemento-->
    <div class="changebg"></div>
    <form action="uploadItem.html" method="POST" class="item_form" enctype="multipart/form-data">
        <div class="close_tag">
            <img src="images/close.svg" alt="close_tag">
        </div>
        <div class="inputs">
            <label for="image">Imagen:</label>
            <input required type="file" name="image" id="image" placeholder="image.jpg">
        </div>
        <div class="inputs">
            <label for="nombre">Nombre: </label>
            <input required type="text" name="nombre" id="nombre">
        </div>
        <div class="inputs">
            <label for="category">Categoria:</label>
            <select required name="category" id="category">
                <option value="sport" selected>deportiva</option>
                <option value="formal">Formal</option>
                <option value="pijama">Pijama</option>
            </select>
        </div>
        <div class="inputs">
            <label for="description">Descripcion:</label>
            <textarea required name="description" id="description" cols="30" rows="10"></textarea>
        </div>
        <div class="inputs">
            <label for="price">Precio:</label>
            <input required type="number" name="price" id="price">
        </div>
        <div class="inputs">
            <input type="submit" value="Subir Item">
        </div>
    </form>
    <!--Fin formulario de subida de elemento-->
    <script src="scripts/script.js"></script>
</body>
</html>