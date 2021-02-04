package uc3m.es.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;
import es.uc3m.dbhandler.ShoppingCartHandler;
import es.uc3m.model.Item;

public class BuyItemSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int idItem = Integer.parseInt(request.getParameter("itemID"));
		Item item = new ItemHandler().findByID(idItem);
		String comprador = (String) request.getSession().getAttribute("user");
		item.setComprador(comprador);
		new ShoppingCartHandler().inserItemIn_shoppingCart(item);
		return "home.jsp";
	}

}
