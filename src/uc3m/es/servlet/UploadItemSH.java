package uc3m.es.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import es.uc3m.dbhandler.ItemHandler;

public class UploadItemSH implements RequestHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String item_name = request.getParameter("nombre");
		String item_category = request.getParameter("category");
		String item_description = request.getParameter("description");
		System.out.println(item_name+"---------"+item_category+"-----------"+item_description);
		int item_prize = Integer.parseInt(request.getParameter("price"));
		Part filePart = request.getPart("image");
		HttpSession session = request.getSession();
		String owner = (String) session.getAttribute("user");
		System.out.println(owner);
		InputStream is = null;
		
		if (filePart != null) {
			is = filePart.getInputStream();
			new ItemHandler().upload_item(item_name, owner, item_description, item_category, is, item_prize);
		}
		
		
		return "home.jsp";
	}

}
