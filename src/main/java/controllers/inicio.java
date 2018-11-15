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
import ar.com.sge.usuarios.Hogar;
import ar.com.sge.usuarios.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class inicio<T> {
private Map<String,Object> model=new HashMap<>();
	
	public ModelAndView inicio(Request req, Response res)throws IOException{
		
		return new ModelAndView(model, "inicio.hbs");
	}
	
	/*public ModelAndView inicioUsuario(Request req, Response res)throws IOException{
		
		try {
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			String usuario = req.queryParams("usuario");
			String contrasenia = req.queryParams("clave");
			Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where contrasenia = "+contrasenia+" and nombre_usuario = '"+usuario+"'", Cliente.class).getResultList().get(0);
			
			Session session = new Session(clientebase.getId_Usuario(),clientebase.getNombre_usuario(),clientebase.getTipoUsuario());
			
			model.put("session", (T) usuario);
			String vista;
			if(clientebase.getTipoUsuario().equalsIgnoreCase("administrador")) {
				
				List<Cliente> lista = entityManager.createNativeQuery(
						"select * from usuario where tipo_usuario='cliente'", Cliente.class).getResultList();
				
				model.put("lista", (T) lista);
				
				vista = "usuario.hbs";
				
			}
			else {
				List<Hogar> lista = entityManager.createNativeQuery("select h.* from hogares h join usuario_hogares uh on uh.hogares_id = h.id where uh.Usuario_id_Usuario = "+session.getId(),Hogar.class).getResultList();
								
				model.put("lista", (T) lista);
				
				vista = "listaHogares.hbs";
			}
			model.put("tipo", (T) clientebase.getTipoUsuario());
			return new ModelAndView(model,vista);
			
		}
		catch(Exception e){
			String mensaje = "El usuario ingresado no existe";	
			model.put("mensaje", (T) mensaje);
			return new ModelAndView(model,"inicio.hbs");
		}
		
	}*/
	
public ModelAndView inicioUsuario(Request req, Response res)throws IOException{
		
		try {
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			String usuario = req.queryParams("usuario");
			String contrasenia = req.queryParams("clave");
			System.out.println(usuario);
			System.out.println(contrasenia);
			Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where contrasenia = "+contrasenia+" and nombre_usuario = '"+usuario+"'", Cliente.class).getResultList().get(0);
			System.out.println("usuario cliente"+clientebase.getNombre());
			model.clear();
			String vista = null;
			
			if(clientebase.getTipoUsuario().equalsIgnoreCase("administrador")) {
				
				List<Cliente> lista = entityManager.createNativeQuery("select * from usuario where tipo_usuario='cliente'", Cliente.class).getResultList();
				
				model.put("lista", (T) lista);
				vista = "listaHogares.hbs";
				
			}
			else {
				
				model.put("listainteligentes", clientebase.getLstDispositivosInteligentes());
				model.put("listaEstandar", clientebase.getLstDispositivosEstandares());
				vista = "usuario.hbs";
			
			}
			
			model.put("usuario", clientebase);
			
			return new ModelAndView(model,vista);
			
		}	
		
		catch(Exception e){
			String mensaje = "El usuario ingresado no existe";	
			model.put("mensaje", (T) mensaje);
			return new ModelAndView(model,"inicio.hbs");
		}
		
	}

}
