package uc3m.es.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.dbhandler.ItemHandler;
import es.uc3m.model.Item;

public class FindItemSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String findInput = request.getParameter("search");
		System.out.println("----------------------"+findInput);
		List<Item> items = new ItemHandler().findItems(findInput);
		request.setAttribute("filter", items);
		
		return "home.jsp";
	}

}
