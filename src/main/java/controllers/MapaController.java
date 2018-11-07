package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.geografia.Coordenada;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MapaController {
	private Map<String, Object> model=new HashMap<>();
	
public ModelAndView verMapa(Request req, Response res)throws  Exception{
	    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();
		List<Transformador> listatransformadorbase= entityManager.createNativeQuery("select * from transformador", Transformador.class).getResultList(); 
		List<Coordenada> listaCoordenada = new ArrayList<>();
		for(Transformador t : listatransformadorbase) {
			listaCoordenada.add(t.getCoordenada());
		}
		model.clear();
		model.put("listacoordenada", listaCoordenada);
	//	Empresa empresa = repo.getEmpresa(empresaBuscado);
		
		//Periodo periodo=empresa.getPeriodoByName(inicio, fin,anio);
		
		
		//model.put("periodo", periodo);
		//model.put("cuentas", periodo.getCuentas());
		return new ModelAndView(model, "Mapa.hbs");
	}
}
