package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.usuarios.Hogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HogarController {

	private Map<String,Object> model=new HashMap<>();
	
	public ModelAndView index(Request req, Response res)throws IOException{
		
		return new ModelAndView(model, "formHogar.hbs");
	}
	
	
	public ModelAndView create(Request req, Response res)throws IOException{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String calle = req.queryParams("calle");
		int numero = Integer.parseInt(req.queryParams("numero"));
		int piso = Integer.parseInt(req.queryParams("piso"));
		String departamento = req.queryParams("departamento");
		
		Hogar hogar = new Hogar(calle,numero,piso,departamento);
		entityManager.persist(hogar);
		transaction.commit();
		
		return new ModelAndView(null, "base.hbs");
	}
}
