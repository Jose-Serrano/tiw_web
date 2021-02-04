package es.uc3m.dbhandler;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import es.uc3m.helpers.ItemHelpers;
import es.uc3m.model.Item;
import es.uc3m.model.ItemPK;
import es.uc3m.model.Usuario;

public class ItemHandler {
	
	private Context _context;
	private Connection _connection;
	private Statement _statement;
	private ItemHelpers itemHelpers = new ItemHelpers();
	
	private void start_connection() {
		
		System.out.println("Comenzamos la conexion");
		
		try {
			
			_context = new InitialContext();
			
			DataSource _datasource = (DataSource) _context.lookup("jdbc/practica");
			
			_connection = _datasource.getConnection();
			
			_statement = _connection.createStatement();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void end_connection() {
		try {
			_statement.close();
			_connection.close();
			_context.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	public List<Item> getAllItems() {
		
		List<Item> items = null;
		
		//Comenzamos la conexion
		try {
			items = new ArrayList<Item>();
			start_connection();
			ResultSet _resultset = _statement.executeQuery("select * from items");
			while(_resultset.next()) {
				
				System.out.println("Articulo: "+_resultset.getString("nombre"));
				Item new_item = generateItem(_resultset);
				items.add(new_item);
			}
			_resultset.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			end_connection();
		}
		System.out.println(items.size());
		return items;
		
	}
	
	public List<Item> getAllItemsFromUser(String user) {
		
		List<Item> items = null;
		
		//Comenzamos la conexion
		try {
			items = new ArrayList<Item>();
			start_connection();
			ResultSet _resultset = _statement.executeQuery("select * from items where email_dueño='"+user+"'");
			while(_resultset.next()) {
				
				System.out.println("Articulo: "+_resultset.getString("nombre"));
				Item new_item = generateItem(_resultset);
				items.add(new_item);
				
			}
			_resultset.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error!!!!!!");
			e.printStackTrace();
		}finally {
			end_connection();
		}
		for (Item item : items) {
			System.out.println(item.getNombre());
		}
		System.out.println(items.size());
		return items;
		
	}
	
	public List<Item> getAllItemsFromBuyer(String user) {
		
		List<Item> items = null;
		
		//Comenzamos la conexion
		try {
			items = new ArrayList<Item>();
			start_connection();
			ResultSet _resultset = _statement.executeQuery("select * from items where comprador='"+user+"'");
			while(_resultset.next()) {
				System.out.println("Articulo: "+_resultset.getString("nombre"));
				Item new_item = generateItem(_resultset);
				items.add(new_item);
			}
			_resultset.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error!!!!!!");
			e.printStackTrace();
		}finally {
			end_connection();
		}
		for (Item item : items) {
			System.out.println(item.getNombre());
		}
		System.out.println(items.size());
		return items;
		
	}
	
	public Item findAnItem(ItemPK key) {
		Item toReturn = null;
		String query = "SELECT * FROM items WHERE email_dueño=? AND iditems=?";
		try {
			start_connection();
			PreparedStatement preparedStm = _connection.prepareStatement(query);
			preparedStm.setString(1, key.getEmailDueño());
			preparedStm.setInt(2, key.getIditems());
			ResultSet _resultset = preparedStm.executeQuery();
			while(_resultset.next()) {
				toReturn = generateItem(_resultset);
			}
			_resultset.close();
			preparedStm.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
		}finally {
			end_connection();
		}
		
		return toReturn;
	}
	
	public Item findByID(int idItem) {
		Item toReturn = null;
		String query = "SELECT * FROM items WHERE iditems=?";
		try {
			start_connection();
			PreparedStatement preparedStm = _connection.prepareStatement(query);
			preparedStm.setInt(1, idItem);
			ResultSet _resultset = preparedStm.executeQuery();
			while(_resultset.next()) {
				toReturn = generateItem(_resultset);
			}
			preparedStm.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			end_connection();
		}
		return toReturn;
	}
	
	private Item generateItem(ResultSet _resultset) {
		Item toReturn = new Item();
		
		try {
			ItemPK key = itemHelpers.generate_PK(_resultset.getInt("iditems"), _resultset.getString("email_dueño"));
			String category = _resultset.getString("categoria");
			String buyer = _resultset.getString("comprador");
			String description = _resultset.getString("descripcion");
			byte[] image = _resultset.getBytes("imagen");
			String name = _resultset.getString("nombre");
			int prize = _resultset.getInt("precio");
			Usuario owner = new UserHandler().find_user(_resultset.getString("email_dueño"));
			toReturn = itemHelpers.create_item(key, category, buyer, description, image, name, owner, prize);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return toReturn;
	}
	
	
	public void upload_item(String name, String owner_email, String description, String category, InputStream is, int prize) {
		String query = "INSERT INTO items (nombre, email_dueño, descripcion, categoria, imagen, precio)"
				+"VALUES(?,?,?,?,?,?)";
		
		try {
			start_connection();
			PreparedStatement preparedStmt = _connection.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, owner_email);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, category);
			preparedStmt.setBlob(5, is);
			preparedStmt.setInt(6, prize);
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ALGUN ERROR");
			e.printStackTrace();
		}finally {
			end_connection();
		}
	}
	
	
	public void setItemBuyer(String buyer, int idItem) {
		String query = "UPDATE items SET comprador=? WHERE iditems=?";
		
		try {
			start_connection();
			PreparedStatement preparedStmt = _connection.prepareStatement(query);
			preparedStmt.setString(1, buyer);
			preparedStmt.setInt(2, idItem);
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error estableciendo comprador!");
			e.printStackTrace();
		}finally {
			end_connection();
		}
	}
	
	public void updateItemInfo(int idItem, String name, String description, String category, int prize) {
		String query = "UPDATE items SET nombre=?, descripcion=?, categoria=?, precio=? WHERE iditems=?";
		try {
			start_connection();
			PreparedStatement preparedStmt = _connection.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, category);
			preparedStmt.setInt(4, prize);
			preparedStmt.setInt(5, idItem);
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
		}finally {
			end_connection();
		}
	}
	
	public void removeItemInfo(int idItems) {
		String query = "DELETE FROM items WHERE iditems=?";
		try {
			start_connection();
			PreparedStatement stmt = _connection.prepareStatement(query);
			stmt.setInt(1, idItems);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			end_connection();
		}
	}
	
	public List<Item> filterItem(String category, int priceMin, int priceMax) {
		String query = "SELECT * FROM items WHERE categoria=? AND precio>? AND precio<?";
		List<Item> toReturn = null;
		try {
			start_connection();
			PreparedStatement stmt = _connection.prepareStatement(query);
			toReturn = new ArrayList<Item>();
			stmt.setString(1, category);
			stmt.setInt(2, priceMin);
			stmt.setInt(3, priceMax);
			ResultSet _resultset = stmt.executeQuery();
			while(_resultset.next()) {
				Item addItem = generateItem(_resultset);
				toReturn.add(addItem);
			}
			_resultset.close();
			stmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
		}finally {
			end_connection();
		}
		
		return toReturn;
	}
	
	public List<Item> filterItem(int priceMin, int priceMax) {
		String query = "SELECT * FROM items WHERE precio>=? AND precio<?";
		List<Item> toReturn = null;
		try {
			start_connection();
			PreparedStatement stmt = _connection.prepareStatement(query);
			toReturn = new ArrayList<Item>();
			stmt.setInt(1, priceMin);
			stmt.setInt(2, priceMax);
			ResultSet _resultset = stmt.executeQuery();
			while(_resultset.next()) {
				Item addItem = generateItem(_resultset);
				toReturn.add(addItem);
			}
			_resultset.close();
			stmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
		}finally {
			end_connection();
		}
		
		return toReturn;
	}
	
	public List<Item> findItems(String input){
		String query = "SELECT * FROM items WHERE nombre LIKE '%"+input+"%' OR descripcion LIKE '%"+input+"%'";
		List<Item> toReturn = null;
		try {
			start_connection();
			
			toReturn = new ArrayList<Item>();
			ResultSet _resultset = _statement.executeQuery(query);
			while(_resultset.next()) {
				Item addItem = generateItem(_resultset);
				toReturn.add(addItem);
			}
			_resultset.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
		}finally {
			end_connection();
		}
		
		
		return toReturn;
	}

}
