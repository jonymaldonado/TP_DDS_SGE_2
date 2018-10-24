package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.IDispositivo;
import ar.com.sge.usuarios.Hogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HogarController<T> {

	private Map<String,T> model=new HashMap<String,T>();
	
	public ModelAndView index(Request req, Response res)throws IOException{
		
		return new ModelAndView(null, "formHogar.hbs");
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
	
	public ModelAndView listarDispositivos(Request req, Response res)throws IOException{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		int id_hogar = Integer.parseInt(req.params(":id"));
		
		List<IDispositivo> lista = entityManager.createNativeQuery(
		"select d.* from dispositivo d join hogar_dispositivo hd on hd.Id_Dispositivo = d.Id_Dispositivo  where hd.id = "+id_hogar, IDispositivo.class).getResultList();

		model.put("lista", (T) lista);
		return new ModelAndView(model, "dispositivos.hbs");
	}
}
