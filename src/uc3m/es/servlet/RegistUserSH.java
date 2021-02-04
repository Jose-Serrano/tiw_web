package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.UserHandler;
import es.uc3m.model.Usuario;

public class RegistUserSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String surnames = request.getParameter("surnames");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String password = request.getParameter("password");
		Usuario new_user = new Usuario();
		new_user.setNombre(name);
		new_user.setApellidos(surnames);
		new_user.setEmail(email);
		new_user.setCiudad(city);
		new_user.setPassword(password);
		new UserHandler().create_user(new_user);
		request.getSession().setAttribute("user", email);		
		
		return "home.jsp";
	}

}
