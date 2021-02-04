package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.UserHandler;

public class RemoveUserSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String user = (String) request.getSession().getAttribute("user");
		new UserHandler().removeUser(user);
		return "index.jsp";
	}

}
