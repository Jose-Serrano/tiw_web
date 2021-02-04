package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;
import es.uc3m.model.Item;

public class EditItemSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("nombre");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		int prize = Integer.parseInt(request.getParameter("price"));
		int idItem = Integer.parseInt(request.getParameter("idItem"));
		System.out.println();
		//ejecutamos la actualización en la base de datos
		new ItemHandler().updateItemInfo(idItem, name, description, category, prize);
		Item item = new ItemHandler().findByID(idItem);
		request.setAttribute("item", item);
		return "item.jsp";
	}

}
