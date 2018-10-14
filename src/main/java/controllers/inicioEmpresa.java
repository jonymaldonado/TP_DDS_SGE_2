package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dao.daoIndicador;
import dao.daoMetodologia;
import dao.daojson;
import dao.repoEmpresa;
import dao.repoMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class inicioEmpresa {
private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicio(Request req, Response res)throws IOException{
		daojson dao=new daojson();
		daoIndicador daoi=new daoIndicador();
		daoMetodologia daom=new daoMetodologia();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		daoi.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\indicadores-usuario.json");
		daom.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\metodologias.json");
		repoEmpresa repo = repoEmpresa.getInstance(dao,daoi);
		repoMetodologias repometodologia=repoMetodologias.getInstance(daom);
		
		model.put("empresas", repo.getAllEmpresas());
		model.put("indicadores", repo.getAllIndicadores());
		//model.put("metodologiasOrdenamiento", repometodologia.getAllMetodologiaOrdenamieto());
		//model.put("metodologiastaxativas", repometodologia.getAllMetodologiaTaxativa());
		return new ModelAndView(model, "empresa.hbs");
	}

}
