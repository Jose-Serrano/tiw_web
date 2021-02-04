package uc3m.es.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;
import es.uc3m.model.Item;

public class FilterSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int minprice = (request.getParameter("priceMin") == "") ? 0 : Integer.parseInt(request.getParameter("priceMin"));
		int maxprice = (request.getParameter("priceMax") == "") ? Integer.MAX_VALUE : Integer.parseInt(request.getParameter("priceMax"));
		String category = request.getParameter("category_filter");
		List<Item> itemList = new ArrayList<Item>();
		if (category == null) {
			itemList = new ItemHandler().filterItem(category, minprice, maxprice);
		}else {
			itemList = new ItemHandler().filterItem(minprice, maxprice);
		}
		request.setAttribute("filter", itemList);
		return "home.jsp";
	}

}
