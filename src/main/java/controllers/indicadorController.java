package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.daoIndicador;
import dao.daojson;
import dao.repoEmpresa;
import entidades.Empresa;
import entidades.IndicadorCompuesto;
import entidades.Periodo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class indicadorController {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView mostrarformula(Request req, Response res)throws IOException{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		daoi.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\indicadores-usuario.json");
		
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		String indicadorBuscado = req.queryParams("nombreIndicador");
		IndicadorCompuesto indicador = repo.getIndicador(indicadorBuscado);
		model.put("indicador", indicador);
		//model.put("periodos", empresa.getPeriodos());
		return new ModelAndView(model, "indicadores.hbs");
	}
	
	public ModelAndView mostrarResultado(Request req, Response res)throws Exception{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		daoi.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\indicadores-usuario.json");
		
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		String indicadorBuscado = req.queryParams("nombreIndicador");
		String empresaBuscado = req.queryParams("nombreEmpresa");
		String inicio = req.queryParams("mesinicio");
		String fin = req.queryParams("mesfin");
		String anio = req.queryParams("anio");
		IndicadorCompuesto indicador = repo.getIndicador(indicadorBuscado);
		Empresa empresa = repo.getEmpresa(empresaBuscado);
		//Double resultado=indicador.aplicarAcuenta(empresa,new Integer(inicio), new Integer(fin), new Integer(anio));
		
		Double resultado=indicador.aplicarAcuenta(empresa,new Integer(inicio), new Integer(fin), new Integer(anio));
		
		//Periodo periodo=empresa.getPeriodoByName(new Integer(inicio), new Integer(fin),new Integer( anio));
		model.put("indicador", indicador);
		model.put("empresa", empresa);
		model.put("resultado", resultado);
		//model.put("cuentas", periodo.getCuentas());
		return new ModelAndView(model, "resultadoindicador.hbs");
	}
	

}
