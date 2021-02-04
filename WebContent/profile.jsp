<!DOCTYPE html>
<%@page import="es.uc3m.dbhandler.UserHandler"%>
<%@page import="es.uc3m.model.Usuario"%>
<%@page import="java.util.Base64"%>
<%@page import="es.uc3m.dbhandler.ItemHandler"%>
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

    <div class="changebg"></div>
    <!--Formulario para editar el perfil de usuario-->
    <form class="update_userInfo" action="editProfile.html" method="POST">
        <div class="close_tag">
            <img src="images/close.svg" alt="close_tag">
        </div>
        <div class="inputs">
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" id="nombre">
        </div>
        <div class="inputs">
            <label for="apellidos">Apellidos: </label>
            <input type="text" name="apellidos" id="apellidos">
        </div>
        <div class="inputs">
            <label for="ciudad">Ciudad: </label>
            <input type="text" name="ciudad" id="ciudad">
        </div>
        <div class="inputs">
            <input type="submit" value="Actualizar Usuario">
        </div>
    </form>
    <!--Fin formulario para editar el perfil de usuario-->
    
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
    <%
    String user = (String) request.getSession().getAttribute("user");
    System.out.println(user);
    ItemHandler ih = new ItemHandler();
    List<Item> myitems = ih.getAllItemsFromUser(user);
    UserHandler uh = new UserHandler();
    Usuario user_data = uh.find_user(user);
    %>
    <section class="main_info">
        <h2><%=user %></h2>
        <div class="user_info">
            <img id="user_img" src="images/usuario.svg" alt="Imagen usuario">
            <div class="user_data">
                <p>Nombre: <%=user_data.getNombre() %></p>
                <p>Apellidos: <%=user_data.getApellidos() %></p>
                <p>Correo: <%=user_data.getEmail() %></p>
                <p>Ciudad: <%=user_data.getCiudad() %></p>
            </div>
        </div>
        <div class="user_options">
            <p id="edit_profile">Editar Perfil</p>
            <p id="delete_profile">Borrar Perfil</p>
        </div>
        <h3>Mis articulos <%=myitems.size()%></h3>
        <div class="my_items">
        <%
        if(myitems != null){
    		for(Item item : myitems){
    			String url = "data:image/png;base64,"+Base64.getEncoder().encodeToString(item.getImagen());
    			%>
    			<div class="child" id=<%=item.getId().getIditems()%>>
	                <div class="img_container">
	                    <img src="<%=url%>" alt="imagen articulo">
	                </div>
	                <span><%= item.getNombre() %></span>
	                <br>
	                <span><%= item.getPrecio()%>&euro;</span>
            	</div>
    		<%}
    	}
        %>
        </div>
        <%
        List<Item> items_bought = ih.getAllItemsFromBuyer(user); 
        %>
        <h3>Articulos comprados: <%=items_bought.size()%></h3>
        <div class="my_items">
	        <%
	        if(items_bought != null){
	        	for(Item item : items_bought){
	        		String url = "data:image/png;base64,"+Base64.getEncoder().encodeToString(item.getImagen());
	    			%>
	    			<div class="child" id=<%=item.getId().getIditems()%>>
		                <div class="img_container">
		                    <img src="<%=url%>" alt="imagen articulo">
		                </div>
		                <span><%= item.getNombre() %></span>
		                <br>
		                <span><%= item.getPrecio()%>&euro;</span>
	            	</div>
	        	<%}
	        }
	        %>            
        </div>

    </section>
    
    <script src="scripts/script.js"></script>
</body>
</html>