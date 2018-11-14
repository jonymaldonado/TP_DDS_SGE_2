package controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.dispositivos.repositorioDispositivo;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Cliente;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;

public class TransformadoController {
	
	private Map<String, Object> model=new HashMap<>();
	EntityManager entityManager;
	
	public float mostrarConsumo(Request req, Response res)throws  Exception{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();
	    
		String transformador_id = req.queryParams("transformador_id");
		Transformador transformador = (Transformador) entityManager.createNativeQuery("select * from transformador where idtransformador = "+transformador_id,Transformador.class).getResultList().get(0);
		
		float consumo = transformador.totalDeConsumoDelTransformadores();
		
		return consumo;
	}

}
