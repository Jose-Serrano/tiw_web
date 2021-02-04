package es.uc3m.dbhandler;

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

import es.uc3m.model.Item;

public class ShoppingCartHandler {
	private Context _context;
	private Connection _connection;
	private Statement _statement;
	
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
	
	public List<Item> shoppingCart_items(String user){
		List<Item> items = null;
		
		try {
			start_connection();
			items = new ArrayList<Item>();
			ResultSet _resultSet = _statement.executeQuery("select * from carrito where email_usuario='"+user+"'");
			while(_resultSet.next()) {
				int idItem = _resultSet.getInt("idItem");
				Item new_item = new ItemHandler().findByID(idItem);
				items.add(new_item);
				System.out.println(new_item.getId().getIditems()+"-"+new_item.getUsuario().getNombre()+"-"+new_item.getNombre());
			}
			_resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			end_connection();
		}
		
		return items;
	}
	
	public void inserItemIn_shoppingCart(Item item) {
		String query = "INSERT INTO carrito (idItem, email_usuario, unidades)"
				+"VALUES(?,?,?)";
		
		try {
			start_connection();
			PreparedStatement preparedStmt = _connection.prepareStatement(query);
			preparedStmt.setInt(1, item.getId().getIditems());
			preparedStmt.setString(2, item.getComprador());
			preparedStmt.setInt(3, 1);
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
	
	public void removeItem(Item item) {
		String query = "DELETE FROM carrito WHERE idItem=?";
		
		try {
			start_connection();
			PreparedStatement preparedStm = _connection.prepareStatement(query);
			preparedStm.setInt(1, item.getId().getIditems());
			preparedStm.executeUpdate();
			preparedStm.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println();
			System.out.println("Error borrando item del carrito");
			e.printStackTrace();
		}finally {
			end_connection();
		}
	}
}
