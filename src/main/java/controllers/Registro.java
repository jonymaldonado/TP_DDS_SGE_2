package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Registro {

	private Map<String,Object> model=new HashMap<>();
	
	public ModelAndView index(Request req, Response res)throws IOException{
		
		return new ModelAndView(model, "registrar.hbs");
	}
	
	public ModelAndView create(Request req, Response res)throws IOException{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String usuario = req.queryParams("nombre_usuario");
		String contrasenia = req.queryParams("contrasenia");
		String nombre = req.queryParams("nombre");
		String apellido = req.queryParams("apellido");
		String tipoDoc = req.queryParams("tipo_documento");
		int numeroDoc = Integer.parseInt(req.queryParams("numero_documento"));
		int telefono = Integer.parseInt(req.queryParams("telefono"));
		
		Cliente cliente = new Cliente(usuario,contrasenia,nombre,apellido,tipoDoc,numeroDoc,telefono);
		entityManager.persist(cliente);
		transaction.commit();
		return new ModelAndView(null, "inicio.hbs");
	}
	

}
