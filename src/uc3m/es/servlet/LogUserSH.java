package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.UserHandler;
import es.uc3m.model.Usuario;

public class LogUserSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Usuario user;
		if((user = new UserHandler().log_user(email, password)) != null) {
			System.out.println("Usuario logeado: "+user.getEmail()+"-"+user.getPassword());
			request.getSession().setAttribute("user", email);
			System.out.println("USUARIO----------"+email);
			return "home.jsp";
		}else {
			return "index.jsp";
		}
		
	}

}
