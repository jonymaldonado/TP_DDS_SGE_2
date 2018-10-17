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

import ar.com.sge.usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class inicio<T> {
private Map<String,T> model=new HashMap<String,T>();
	
	public ModelAndView inicio(Request req, Response res)throws IOException{
		//daoUsuario dao=new daoUsuario();
	//	daoIndicador daoi=new daoIndicador();
		//daoMetodologia daom=new daoMetodologia();
		//dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\usuario.json");
		//daoi.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\indicadores-usuario.json");
		//daom.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\metodologias.json");
		//repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		//repoMetodologias repometodologia=repoMetodologias.getInstance(daom);
		
		//model.put("empresas", repo.getAllEmpresas());
		//model.put("indicadores", repo.getAllIndicadores());
		//model.put("metodologiasOrdenamiento", repometodologia.getAllMetodologiaOrdenamieto());
		//model.put("metodologiastaxativas", repometodologia.getAllMetodologiaTaxativa());
		return new ModelAndView(model, "inicio.hbs");
	}
	
	/*public ModelAndView inicioUsuario(Request req, Response res)throws IOException{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		String usuario = req.queryParams("usuario");
		String contrasenia = req.queryParams("clave");
		
		Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where contrasenia = "+contrasenia+" and nombre_usuario = '"+usuario+"'", Cliente.class).getResultList().get(0);
		
		model.put("usuario", clientebase);
		return new ModelAndView(model, "usuario.hbs");
	}*/
	
	public ModelAndView inicioUsuario(Request req, Response res)throws IOException{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String usuario = req.queryParams("usuario");
		String contrasenia = req.queryParams("clave");
		
		int clientebase= entityManager.createNativeQuery("select * from usuario where contrasenia = "+contrasenia+" and nombre_usuario = '"+usuario+"'", Cliente.class).getFirstResult();
		
		System.out.println(clientebase);
		List<Cliente> lista = entityManager.createNativeQuery(
				"select u.*,c.nombre as categoria from usuario u left join categorias c on c.idCategoria = u.idCategoria where u.tipo_usuario='cliente'", Cliente.class).getResultList();
		
		model.put("usuarios", (T) lista);
		model.put("cliente", (T) usuario);
		
		
		return new ModelAndView(model,"usuario.hbs");
	}

}
