package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.modeloInteligente;
import ar.com.sge.geografia.Coordenada;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Hogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Registro {

	private Map<String,Object> model=new HashMap<>();
	
	public ModelAndView index(Request req, Response res)throws IOException{

		model.clear();
	
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
		//domicilio
		System.out.println(req.queryParams("latitude")+","+req.queryParams("longitude"));
		String calle = req.queryParams("calle");
		int numero = Integer.parseInt(req.queryParams("numero_calle"));
		String piso = (!(req.queryParams("piso").isEmpty()) && req.queryParams("piso")!="") ? req.queryParams("piso") : null;
		String departamento = (!(req.queryParams("departamento").isEmpty()) && req.queryParams("departamento")!="")?req.queryParams("departamento"):null;
		double latitud = Double.parseDouble(req.queryParams("latitude"));
		double longitud = Double.parseDouble(req.queryParams("longitude"));
		System.out.println(calle+","+numero);
		Hogar hogar = new Hogar();
		hogar.setCalle(calle);
		hogar.setNumero(numero);
		if(piso!=null) {
			hogar.setPiso(Integer.parseInt(piso));
		}
		if(departamento!=null) {
			hogar.setDepartamento(departamento);
		}
		
		Coordenada coordenada = new Coordenada(latitud,longitud);
		Cliente cliente = new Cliente(usuario,contrasenia,nombre,apellido,tipoDoc,numeroDoc,telefono);
		entityManager.persist(hogar);
		cliente.setHogar(hogar);
		cliente.setCoordenada(coordenada);
		
		List<Transformador> listaTrans = entityManager.createNativeQuery("select * from transformador",Transformador.class).getResultList();
		double minimo = listaTrans.get(0).getCoordenada().distanciaAlPunto(cliente.getCoordenada());
		Transformador transformador = null;
		for(Transformador t: listaTrans) {
			if(t.getCoordenada().distanciaAlPunto(cliente.getCoordenada()) <= minimo) {
				minimo = t.getCoordenada().distanciaAlPunto(cliente.getCoordenada());
				transformador = t;
			}
		}
		
		transformador.agregarCliente(cliente);
		entityManager.persist(cliente);
		transaction.commit();
		
		model.clear();
		return new ModelAndView(model, "inicio.hbs");
	}
	

}
