package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.UserHandler;

public class EditProfileSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//Obtenemos los datos
		String name = request.getParameter("nombre");
		String surnames = request.getParameter("apellidos");
		String city = request.getParameter("ciudad");
		String user = (String) request.getSession().getAttribute("user");
		
		//Modificamos la informacion de usuario
		new UserHandler().editUser(user, name, surnames, city);
		
		return "profile.jsp";
	}

}
