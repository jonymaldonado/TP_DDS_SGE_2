package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.repositorioDispositivo;
import ar.com.sge.usuarios.Cliente;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {
	private Map<String, Object> model=new HashMap<>();
	
	/*public ModelAndView listarPeriodos(Request req, Response res)throws IOException{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		String empresaBuscado = req.queryParams("nombreEmpresa");
		Empresa empresa = repo.getEmpresa(empresaBuscado);
		model.put("empresa", empresa);
		model.put("periodos", empresa.getPeriodos());
		return new ModelAndView(model, "periodos.hbs");
	}*/
	
	/*public ModelAndView verdetalleVenta(Request req, Response res)throws  Exception{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		String empresaBuscado = req.queryParams("nombreEmpresa");
		String mesinicio = req.queryParams("inicio");
		String mesfin = req.queryParams("fin");
		String anio = req.queryParams("anio");
		Empresa empresa = repo.getEmpresa(empresaBuscado);
		
		Periodo periodo=empresa.getPeriodoByName(new Integer(mesinicio), new Integer(mesfin),new Integer(anio));
		//String ventaId = req.queryParams("idVenta");
		//Venta venta = modelSuper.getVenta(supermercadoBuscado, new Integer(ventaId));
		
		model.clear();
		model.put("periodo", periodo);
		model.put("cuenta", periodo.getCuentas());
		return new ModelAndView(model, "detallePeriodo.hbs");
	}*/
	
	/*public ModelAndView verdetalleVenta2(Request req, Response res)throws  Exception{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		String empresaBuscado = req.params(":empresa");
		int inicio = Integer.parseInt(req.params(":mesinicio"));
		int fin = Integer.parseInt(req.params(":mesfin"));
		int anio = Integer.parseInt(req.params(":anio"));
		
		Empresa empresa = repo.getEmpresa(empresaBuscado);
		
		Periodo periodo=empresa.getPeriodoByName(inicio, fin,anio);
		
		model.clear();
		model.put("periodo", periodo);
		model.put("cuentas", periodo.getCuentas());
		return new ModelAndView(model, "detallePeriodo.hbs");
	}*/
	public ModelAndView verdetalleInteligente(Request req, Response res)throws  Exception{
		
		String usuarioBuscado = req.params(":usuario");
		/*int inicio = Integer.parseInt(req.params(":mesinicio"));
		int fin = Integer.parseInt(req.params(":mesfin"));
		int anio = Integer.parseInt(req.params(":anio"));*/
		
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		//String usuarioBuscado = req.queryParams("usuario");
		//String contraseñaBuscado = req.queryParams("clave");
		//List<repositorioDispositivo> listarepo=(List<repositorioDispositivo>) entityManager.createQuery("select r from repositorio r").getResultList(); 
		//repositorioDispositivo repobase=listarepo.get(0);
		
		String nombreinteligente = req.queryParams("nombreinteligente");
		String regla = req.queryParams("regla");
		String valor = req.queryParams("valor");
		repositorioDispositivo repobase=entityManager.find(repositorioDispositivo.class,3);
		
		List<Cliente> listaclientesbase=(List<Cliente>) entityManager.createQuery("from Usuario where nombre_usuario='"+usuarioBuscado+"'").getResultList(); 
		Cliente clientebase=listaclientesbase.get(0);
		if(nombreinteligente!=null) {
			
			System.out.println("nombre "+nombreinteligente+repobase.getListaActualInteligentes().get(0).getNombre());
		repobase.seleccionarInteligente(clientebase, nombreinteligente);
		//transaction.commit();
		}
		//transaction.commit();
		
		model.clear();
		model.put("usuario", clientebase);
		model.put("listainteligentes", clientebase.getLstDispositivosInteligentes());
		model.put("inteligentesbase", repobase.getListaActualInteligentes());
		//model.put("listaestandar", clientebase.getLstDispositivosEstandares());
	//	Empresa empresa = repo.getEmpresa(empresaBuscado);
		
		//Periodo periodo=empresa.getPeriodoByName(inicio, fin,anio);
		
		transaction.commit();
		return new ModelAndView(model, "detallePeriodo.hbs");
	}
	
public ModelAndView verdetalleStandar(Request req, Response res)throws  Exception{
		
		String usuarioBuscado = req.params(":usuario");
		/*int inicio = Integer.parseInt(req.params(":mesinicio"));
		int fin = Integer.parseInt(req.params(":mesfin"));
		int anio = Integer.parseInt(req.params(":anio"));*/
		
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();

		
		//String contraseñaBuscado = req.queryParams("clave");

		List<Cliente> listaclientesbase=(List<Cliente>) entityManager.createQuery("from Usuario where nombre_usuario='"+usuarioBuscado+"'").getResultList(); 
		Cliente clientebase=listaclientesbase.get(0);
		
		model.clear();
		model.put("usuario", clientebase);
		model.put("listaestandar", clientebase.getLstDispositivosEstandares());
	//	Empresa empresa = repo.getEmpresa(empresaBuscado);
		
		//Periodo periodo=empresa.getPeriodoByName(inicio, fin,anio);
		
		
		//model.put("periodo", periodo);
		//model.put("cuentas", periodo.getCuentas());
		return new ModelAndView(model, "detallePeriodo.hbs");
	}

public ModelAndView verResultado(Request req, Response res)throws  Exception{
	

		
		String usuarioBuscado = req.params(":usuario");
		/*int inicio = Integer.parseInt(req.params(":mesinicio"));
		int fin = Integer.parseInt(req.params(":mesfin"));
		int anio = Integer.parseInt(req.params(":anio"));*/
		
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();

		
		//String contraseñaBuscado = req.queryParams("clave");

		List<Cliente> listaclientesbase=(List<Cliente>) entityManager.createQuery("from Usuario where nombre_usuario='"+usuarioBuscado+"'").getResultList(); 
		Cliente clientebase=listaclientesbase.get(0);
		//clientebase.consultarASimplex();
		System.out.println("valor simplex" + clientebase.ResultadoSimplex().get(0));
		System.out.println("valor simplex" + clientebase.ResultadoSimplex().get(1));
		List<Double> listaDeConsumoActual = new ArrayList<>();
		model.clear();
		model.put("consumo", clientebase.ConsumoActualDispositivos());
		model.put("usuario", clientebase);
		model.put("resultado", clientebase.ResultadoSimplex());
		model.put("listainteligente", clientebase.getLstDispositivosInteligentes());
	//	Empresa empresa = repo.getEmpresa(empresaBuscado);
		
		//Periodo periodo=empresa.getPeriodoByName(inicio, fin,anio);
		
		
		//model.put("periodo", periodo);
		//model.put("cuentas", periodo.getCuentas());
		return new ModelAndView(model, "resultadoSimplex.hbs");
}


}
