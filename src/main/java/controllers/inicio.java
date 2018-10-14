package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class inicio {
private Map<String, Object> model=new HashMap<>();
	
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
	
	public ModelAndView inicioUsuario(Request req, Response res)throws IOException{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();

		String usuarioBuscado = req.queryParams("usuario");
		String contraseñaBuscado = req.queryParams("clave");
		//Cliente clientebase=(Cliente) entityManager.createNativeQuery("from usuario where usuario.contrasenia="+contraseñaBuscado, Cliente.class).getResultList().get(0);
		//Cliente clientebase=(Cliente) entityManager.createQuery("from usuario where usuario.contrasenia="+contraseñaBuscado).getResultList().get(0); 
		//entityManager.createQuery("from usuario").executeUpdate();
		
		
		//repoUsuario repo=repoUsuario.getInstance(dao);
		//Cliente usuario=;
		
		model.put("usuario", clientebase);
		//model.put("metodologiastaxativas", usuario.getMetodologiaTaxativa());
		//model.put("lista", usuario.getMetodologiaOrdenamiento());
		//model.put("empresas", repoempresa.getAllEmpresas());
		//model.put("metodologiasOrdenamiento", repometodologia.getAllMetodologiaOrdenamieto());
		//model.put("metodologiastaxativas", repometodologia.getAllMetodologiaTaxativa());
		return new ModelAndView(model, "usuario.hbs");
	}

}
