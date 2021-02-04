package es.uc3m.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uc3m.es.servlet.BuyItemSH;
import uc3m.es.servlet.ConfirmBuyItemSH;
import uc3m.es.servlet.EditItemSH;
import uc3m.es.servlet.EditProfileSH;
import uc3m.es.servlet.FilterSH;
import uc3m.es.servlet.FindItemSH;
import uc3m.es.servlet.GetChatSH;
import uc3m.es.servlet.ItemInfoSH;
import uc3m.es.servlet.LogUserSH;
import uc3m.es.servlet.RegistUserSH;
import uc3m.es.servlet.RemoveItemSH;
import uc3m.es.servlet.RemoveUserSH;
import uc3m.es.servlet.RequestHandler;
import uc3m.es.servlet.UploadItemSH;

/**
 * Servlet implementation class MainController
 */
@WebServlet({ "/MainController", "*.html" })
@MultipartConfig
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    Map<String, RequestHandler> dictionary;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		dictionary = new HashMap<String, RequestHandler>();
		dictionary.put("/log.html", new LogUserSH());
		dictionary.put("/register.html", new RegistUserSH());
		dictionary.put("/addToCart.html", new BuyItemSH());
		dictionary.put("/confirmBuy.html", new ConfirmBuyItemSH());
		dictionary.put("/editItem.html", new EditItemSH());
		dictionary.put("/editProfile.html", new EditProfileSH());
		dictionary.put("/filtrar.html", new FilterSH());
		dictionary.put("/uploadItem.html", new UploadItemSH());
		dictionary.put("/itemInfo.html", new ItemInfoSH());
		dictionary.put("/removeUser.html", new RemoveUserSH());
		dictionary.put("/deleteItem.html", new RemoveItemSH());	
		dictionary.put("/search.html", new FindItemSH());
		dictionary.put("/chat.html", new GetChatSH());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		System.out.println("PATH: "+path);
		
		RequestHandler rh = dictionary.get(path);
		
		if(rh == null) {
			response.sendError(500);
		}else {
			String view = rh.process(request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
