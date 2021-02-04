package uc3m.es.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;
import es.uc3m.dbhandler.ShoppingCartHandler;
import es.uc3m.model.Item;

public class ConfirmBuyItemSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String user = (String) request.getSession().getAttribute("user");
		ItemHandler ih = new ItemHandler();
		ShoppingCartHandler ish = new ShoppingCartHandler();
		List<Item> shoppingCart_items = new ShoppingCartHandler().shoppingCart_items(user);
		
		for (Item item : shoppingCart_items) {

			//Cambiar estado a comprado de los items (ponemos comprador)
			ih.setItemBuyer(user, item.getId().getIditems());
			
			//Eliminar el item de TODOS los carritos
			ish.removeItem(item);			
			
		}
		return "home.jsp";
	}

}
