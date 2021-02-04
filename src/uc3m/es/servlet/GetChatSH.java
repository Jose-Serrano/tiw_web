package uc3m.es.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetChatSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String user = request.getParameter("reciver");
		System.out.println(user);
		String sender = (String) request.getSession().getAttribute("user");
		List<String> chat = new ArrayList<String>();
		chat.add(sender);
		chat.add(user);
		request.setAttribute("chat", chat);
		return "chat.jsp";
	}

}
