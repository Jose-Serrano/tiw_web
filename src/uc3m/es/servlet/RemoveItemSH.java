package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;

public class RemoveItemSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int idItems = Integer.parseInt(request.getParameter("idItem"));
		new ItemHandler().removeItemInfo(idItems);
		return "home.jsp";
	}

}
