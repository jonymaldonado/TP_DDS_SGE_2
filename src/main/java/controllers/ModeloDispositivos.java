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

import ar.com.sge.dispositivos.IDispositivo;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Hogar;
import ar.com.sge.usuarios.Session;
import ar.com.sge.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ModeloDispositivos {
	
private Map<String,Object> model=new HashMap<>();
	
	public ModelAndView listarInteligente(Request req, Response res)throws IOException{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		return new ModelAndView(model, "modeloInteligente.hbs");
	}
	
	public ModelAndView listarEstandar(Request req, Response res)throws IOException{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		return new ModelAndView(model, "modeloEstandar.hbs");
	}	

}
