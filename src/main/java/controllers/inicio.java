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
import ar.com.sge.usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class inicio<T> {
private Map<String,T> model=new HashMap<String,T>();
	
	public ModelAndView inicio(Request req, Response res)throws IOException{
		
		return new ModelAndView(model, "inicio.hbs");
	}
	
	public ModelAndView inicioUsuario(Request req, Response res)throws IOException{
		
		try {
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			String usuario = req.queryParams("usuario");
			String contrasenia = req.queryParams("clave");
			Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where contrasenia = "+contrasenia+" and nombre_usuario = '"+usuario+"'", Cliente.class).getResultList().get(0);
			
			model.put("cliente", (T) usuario);
			String vista;
			if(clientebase.getTipoUsuario().equalsIgnoreCase("administrador")) {
				
				List<Cliente> lista = entityManager.createNativeQuery(
						"select * from usuario where tipo_usuario='cliente'", Cliente.class).getResultList();
				
				model.put("lista", (T) lista);
				
				vista = "usuario.hbs";
				
			}
			else {
				List<IDispositivo> lista = entityManager.createNativeQuery(
						"select * from dispositivo where id_usuario = "+clientebase.getId_Usuario(), IDispositivo.class).getResultList();
				
				model.put("lista", (T) lista);
				
				vista = "base.hbs";
			}
			model.put("tipo", (T) clientebase.getTipoUsuario());
			return new ModelAndView(model,vista);
			
		}
		catch(Exception e){
			String mensaje = "El usuario ingresado no existe";	
			model.put("mensaje", (T) mensaje);
			return new ModelAndView(model,"inicio.hbs");
		}
		
		
	}

}
