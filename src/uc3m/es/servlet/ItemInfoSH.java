package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;
import es.uc3m.model.Item;

public class ItemInfoSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Autogenerated method stub
		Item item = new ItemHandler().findByID(Integer.parseInt(request.getParameter("idItem")));
		request.setAttribute("item", item);
		return "item.jsp";
	}

}
