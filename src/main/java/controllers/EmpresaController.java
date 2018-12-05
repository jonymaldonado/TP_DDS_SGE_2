package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.dispositivos.modeloStandar;
import ar.com.sge.dispositivos.repositorioDispositivo;
import ar.com.sge.reglas.Regla;
import ar.com.sge.usuarios.Cliente;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {
	private Map<String, Object> model=new HashMap<>();
	EntityManager entityManager;
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
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String nombreinteligente = req.queryParams("nombreinteligente");
		String regla = req.queryParams("regla");
		String valor = req.queryParams("valor");
		String accion = req.queryParams("accion");
		
		
		repositorioDispositivo repobase=entityManager.find(repositorioDispositivo.class,1);
		Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where nombre_usuario = '"+usuarioBuscado+"'", Cliente.class).getResultList().get(0);
		List<Integer> inteligentes=new ArrayList<>();
		
		if(clientebase.getLstDispositivosInteligentes().size()!=0) {
		for(DispositivoInteligente inteligente:clientebase.getLstDispositivosInteligentes() ) {
			inteligentes.add(inteligente.getId_Dispositivo());
		}
		}
		if(nombreinteligente!=null) {
				
		System.out.println("nombre "+nombreinteligente+repobase.getListaActualInteligentes().get(0).getNombre());
		repobase.seleccionarInteligente(clientebase, nombreinteligente,regla,Double.parseDouble(valor),accion);
		
		//transaction.commit();
		
		}
		
		transaction.commit();
		
		model.clear();
		model.put("usuario", clientebase);
		model.put("inteligente", inteligentes);
		model.put("listainteligentes", clientebase.getLstDispositivosInteligentes());
		model.put("inteligentesbase", repobase.getListaActualInteligentes());
		
		return new ModelAndView(model, "detallePeriodo.hbs");
	}
	
	public ModelAndView verdetalleStandar(Request req, Response res)throws  Exception{
		
		String usuarioBuscado = req.params(":usuario");
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where nombre_usuario = '"+usuarioBuscado+"'", Cliente.class).getResultList().get(0);
		
		repositorioDispositivo repobase = entityManager.find(repositorioDispositivo.class,1);
		
		//List<modeloStandar> listaEstandar = entityManager.createNativeQuery("select * from modelostandar", modeloStandar.class).getResultList();
		
		model.clear();
		model.put("usuario", clientebase);
		model.put("listaEstandar", clientebase.getLstDispositivosEstandares());
		model.put("listaEstandarRepo",repobase.getListaActualEstandar());
		return new ModelAndView(model, "dispositivoEstandar.hbs");
	}

	public ModelAndView verResultado(Request req, Response res)throws  Exception{
	

		
		String usuarioBuscado = req.params(":usuario");
		/*int inicio = Integer.parseInt(req.params(":mesinicio"));
		int fin = Integer.parseInt(req.params(":mesfin"));
		int anio = Integer.parseInt(req.params(":anio"));*/
		
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		
		
		//String contrase�aBuscado = req.queryParams("clave");

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
	
	public double verConsumo(Request req, Response res)throws  Exception{
	

		
		String usuarioBuscado = req.params(":usuario");
		String inicio = req.queryParams("fecha_inicio");
		String fin = req.queryParams("fecha_fin");
		
		System.out.print("fecha inicio"+inicio+"**** fecha fin "+fin);
		
		
		String ini = inicio+" 00:00";
		String fi = fin+" 00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechainicio = LocalDateTime.parse(ini, formatter);
        LocalDateTime fechafin = LocalDateTime.parse(fi, formatter);
        
        /*
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime fechainicio = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fechafin = LocalDateTime.parse(fin, formatter);*/
		
		System.out.print("+++++++++++++++++++++++++++++++++++++++++");
		
		
		System.out.print("fecha inicio"+fechainicio+"**** fecha fin "+fechafin);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		List<Cliente> listaclientesbase=(List<Cliente>) entityManager.createQuery("from Usuario where nombre_usuario='"+usuarioBuscado+"'").getResultList(); 
		Cliente clientebase=listaclientesbase.get(0);
		
		Double kw = (double) clientebase.consumoDeEnergiaDeDispositivos(fechainicio, fechafin);
		
		return kw;
}

	public ModelAndView verRegla(Request req, Response res)throws  Exception{
		
		String inteligenteBuscado = req.params(":Id_Dispositivo");
		/*int inicio = Integer.parseInt(req.params(":mesinicio"));
		int fin = Integer.parseInt(req.params(":mesfin"));
		int anio = Integer.parseInt(req.params(":anio"));*/
		
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		String usuarioBuscado = req.params(":usuario");	
		transaction.begin();

		//String nombreinteligente = req.queryParams("nombreinteligente");
		String reglabuscada = req.queryParams("id_regla");
		String regla = req.queryParams("regla");
		String valor = req.queryParams("valor");
		String accion = req.queryParams("accion");
		System.out.println("***********valor regla*********" + reglabuscada);
		
		//repositorioDispositivo repobase=entityManager.find(repositorioDispositivo.class,2);
		Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where nombre_usuario = '"+usuarioBuscado+"'", Cliente.class).getResultList().get(0);
		//List<Integer> inteligentes=new ArrayList<>();
		
		//String contrase�aBuscado = req.queryParams("clave");

		List<DispositivoInteligente> listaclientesbase= entityManager.createNativeQuery("select * from dispositivo where Id_Dispositivo="+inteligenteBuscado,DispositivoInteligente.class).getResultList(); 
		DispositivoInteligente inteligentebase=listaclientesbase.get(0);
		if(reglabuscada!=null) {
			inteligentebase.eliminarregla(Integer.parseInt(reglabuscada));
			Regla regla_1 = entityManager.find(Regla.class, Integer.parseInt(reglabuscada));
			entityManager.remove(regla_1);
		}
		List<Regla> reglas=inteligentebase.getSensor().getObservadores();
		if(regla!=null) {
			Regla reglanueva=new Regla(regla,Double.parseDouble(valor),accion);
			inteligentebase.getSensor().agregarObservador(reglanueva);
			
			//transaction.commit();
			}
		transaction.commit();
		
		model.clear();
		model.put("usuario", clientebase);
		model.put("inteligente", inteligentebase);
		model.put("listareglas", reglas);
		
		//model.put("periodo", periodo);
		//model.put("cuentas", periodo.getCuentas());
		return new ModelAndView(model, "dispositivos.hbs");
	}
	
	public int agregarEstandar(Request req, Response res)throws  Exception{
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		String usuarioBuscado = req.params(":usuario");
		
		String nombre_dispositivo = req.queryParams("nombre_dispositivo");
		System.out.println(nombre_dispositivo);
		
		repositorioDispositivo repobase=entityManager.find(repositorioDispositivo.class,1);
		
		Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where nombre_usuario = '"+usuarioBuscado+"'", Cliente.class).getResultList().get(0);
		
		repobase.seleccionarStandar(clientebase,nombre_dispositivo);
		
		transaction.commit();
		
		return 1;
	}

}
