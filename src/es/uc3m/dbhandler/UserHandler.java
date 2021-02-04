package es.uc3m.dbhandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import es.uc3m.model.Usuario;


public class UserHandler {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("practica1");
	EntityManager em = emf.createEntityManager();
	
	public void create_user(Usuario new_user) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(new_user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
	public Usuario log_user(String email, String password) {
		Usuario user = em.find(Usuario.class, email);
		if (user!=null && user.getPassword().equals(password)) {
			System.out.println("Logeado");
			return user;
		}else {
			System.out.println("Usuario o contraseña incorrectos");
			return null;
		}
	}
	
	public Usuario find_user(String email) {
		Usuario user = em.find(Usuario.class, email);
		em.close();
		if (user!=null) {
			return user;
		}else {
			return null;
		}
	}
	
	public void editUser(String email, String nombre, String apellidos, String ciudad) {
		System.out.println(nombre+"---"+apellidos+"----"+ciudad);
		Usuario user = em.find(Usuario.class, email);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			user.setNombre(nombre);
			user.setApellidos(apellidos);
			user.setCiudad(ciudad);
			em.merge(user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
	public void removeUser(String email) {
		EntityTransaction tx = em.getTransaction();
		Usuario user = em.find(Usuario.class, email);
		try {
			tx.begin();
			em.remove(user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
}
