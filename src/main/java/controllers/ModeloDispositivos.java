package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.modeloInteligente;
import ar.com.sge.dispositivos.modeloStandar;
import ar.com.sge.dispositivos.repositorioDispositivo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ModeloDispositivos {

	private Map<String, Object> model = new HashMap<>();

	public ModelAndView listarInteligente(Request req, Response res) throws IOException {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		List<modeloInteligente> listaInteligente = new ArrayList<>();
		listaInteligente = (List<modeloInteligente>) entityManager
				.createNativeQuery("Select * from ModeloInteligente", modeloInteligente.class).getResultList();

		model.clear();
		model.put("listaInteligente", listaInteligente);

		return new ModelAndView(model, "modeloInteligente.hbs");
	}

	public ModelAndView listarEstandar(Request req, Response res) throws IOException {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		List<modeloStandar> listaEstandar = new ArrayList<>();
		listaEstandar = (List<modeloStandar>) entityManager.createNativeQuery("select * from ModeloStandar;", modeloStandar.class).getResultList();
		System.out.print("ejecutado por vista");
		model.clear();
		model.put("listaEstandar", listaEstandar);
		
		return new ModelAndView(model, "modeloEstandar.hbs");
	}

	public int deleteInteligente(Request req, Response res) throws IOException {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		
		int id_inteligente = Integer.parseInt(req.queryParams("id_inteligente"));		
		
		modeloInteligente dispo = entityManager.find(modeloInteligente.class,id_inteligente);
		
		entityManager.remove(dispo);
		transaction.commit();
		  
		return 1;
	}
	
	public int deleteEstandar(Request req, Response res) throws IOException {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		
		int id_estandar = Integer.parseInt(req.queryParams("id_estandar"));	
		modeloStandar dis = entityManager.find(modeloStandar.class,id_estandar);
		
		entityManager.remove(dis);
		transaction.commit();
		  
		return 1;
	}
	
	public int addModeloInteligente(Request req, Response res) throws IOException {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		
		modeloInteligente dispo;
		
		if(req.queryParams("id_inteligente").isEmpty()) {
			
			dispo = new modeloInteligente();
			
		}else {
			
			int id_inteligente = Integer.parseInt(req.queryParams("id_inteligente"));
			dispo = entityManager.find(modeloInteligente.class,id_inteligente);
		}
		
		
		dispo.setNombre(req.queryParams("nombre"));
		dispo.setKwPorHora(Double.parseDouble(req.queryParams("kw")));
		dispo.setMaximoconsumo(Double.parseDouble(req.queryParams("max")));
		dispo.setMinimoconsumo(Double.parseDouble(req.queryParams("min")));
		entityManager.merge(dispo);
		transaction.commit();
		  
		return 1;
	}
	
	public int addModeloEstandar(Request req, Response res) throws IOException {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		modeloStandar dispo;
		
		if(req.queryParams("id_estandar").isEmpty()) {
			
			dispo = new modeloStandar();
			
		}else {
			
			int id_estandar = Integer.parseInt(req.queryParams("id_estandar"));			
			dispo = entityManager.find(modeloStandar.class,id_estandar);
		}		
		
		dispo.setNombre(req.queryParams("nombre"));
		dispo.setKwPorHora(Double.parseDouble(req.queryParams("kw")));
		entityManager.merge(dispo);
		transaction.commit();
		  
		return 1;
	}
}
