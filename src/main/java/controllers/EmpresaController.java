package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dao.daoIndicador;
import dao.daojson;
import dao.repoEmpresa;
import entidades.Empresa;
import entidades.Periodo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView listarPeriodos(Request req, Response res)throws IOException{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		String empresaBuscado = req.queryParams("nombreEmpresa");
		Empresa empresa = repo.getEmpresa(empresaBuscado);
		model.put("empresa", empresa);
		model.put("periodos", empresa.getPeriodos());
		return new ModelAndView(model, "periodos.hbs");
	}
	
	public ModelAndView verdetalleVenta(Request req, Response res)throws  Exception{
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
	}
	
	public ModelAndView verdetalleVenta2(Request req, Response res)throws  Exception{
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
	}

}
