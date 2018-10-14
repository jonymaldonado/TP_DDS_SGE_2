package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.daoIndicador;
import dao.daoM;
import dao.daoMetodologia;
import dao.daoUsuario;
import dao.daojson;
import dao.repoEmpresa;
import dao.repoMetodologias;
import dao.repoUsuario;
import entidades.Empresa;
import entidades.IndicadorCompuesto;
import entidades.Periodo;
import entidades.Usuario;
import metodologia.IMetodologia;
import metodologia.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class metodologiaController {
	private Map<String, Object> model=new HashMap<>();

	
	/*public ModelAndView mostrarConsulta(Request req, Response res)throws Exception{
		daoMetodologia dao=new daoMetodologia();
		daojson daoe=new daojson();
		daoIndicador daoi=new daoIndicador();
		daoe.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		daoi.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\indicadores-usuario.json");
		
		repoEmpresa repoempresa=repoEmpresa.getInstance(daoe, daoi);
		repoMetodologias repo = repoMetodologias.getInstance(dao);
		String metodologiaBuscado = req.queryParams("nombreMetodologiaTaxativa");
		String empresaBuscado = req.queryParams("nombreEmpresa");
		String inicio = req.queryParams("mesinicio");
		String fin = req.queryParams("mesfin");
		String anio = req.queryParams("anio");
		Metodologia metodologiataxativa = repo.getMetodologiaTaxativa(metodologiaBuscado);
		Empresa empresa = repoempresa.getEmpresa(empresaBuscado);
		
		model.put("respuesta",metodologiataxativa.convieneInvertiren(empresa,new Integer(inicio), new Integer(fin),new Integer( anio)));
		//model.put("indicador", indicador);
		model.put("empresa", empresa);
		model.put("metodologiataxativa", metodologiataxativa);
	//	model.put("respuesta", periodo.getCuentas());
		return new ModelAndView(model, "metodologiaconsulta.hbs");
	}*/
	public ModelAndView mostrarConsulta(Request req, Response res)throws Exception{
		
		daoUsuario dao=new daoUsuario();
		daoIndicador daoi=new daoIndicador();
		daojson daoe=new daojson();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\usuario.json");
		daoe.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		
		repoUsuario repousuario=repoUsuario.getInstance(dao);
		repoEmpresa repoempresa=repoEmpresa.getInstance(daoe, daoi);
		String metodologiaBuscado = req.queryParams("nombreMetodologiaTaxativa");
		String empresaBuscado = req.queryParams("nombreEmpresa");
		String inicio = req.queryParams("mesinicio");
		String fin = req.queryParams("mesfin");
		String anio = req.queryParams("anio");
		String usuarioBuscado = req.params(":usuario");
		
		
		Usuario usuario = repousuario.getUsuarioNombre(usuarioBuscado);
		Metodologia metodologiataxativa=usuario.getTaxativa(metodologiaBuscado);
		Empresa empresa = repoempresa.getEmpresa(empresaBuscado);
		
		model.put("respuesta",metodologiataxativa.convieneInvertir(empresa,new Integer(inicio), new Integer(fin),new Integer( anio)));
		//model.put("indicador", indicador);
		model.put("empresa", empresa);
		model.put("metodologiataxativa", metodologiataxativa);
	//	model.put("respuesta", periodo.getCuentas());
		return new ModelAndView(model, "metodologiaconsulta.hbs");
	}
	
	public ModelAndView mostrarlista(Request req, Response res)throws Exception{
		
		daoUsuario dao=new daoUsuario();
		daoIndicador daoi=new daoIndicador();
		daojson daoe=new daojson();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\usuario.json");
		daoe.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		
		repoUsuario repousuario=repoUsuario.getInstance(dao);
		repoEmpresa repoempresa=repoEmpresa.getInstance(daoe, daoi);
		String metodologiaBuscado = req.queryParams("nombreMetodologiaOrdenamiento");
		String empresasBuscados = req.queryParams("nombreEmpresa");
		String inicio = req.queryParams("mesinicio");
		String fin = req.queryParams("mesfin");
		String anio = req.queryParams("anio");
		String usuarioBuscado = req.params(":usuario");
		/*Gson gson=new GsonBuilder().setPrettyPrinting().create();
		ArrayList<String> usuarios = gson.fromJson(empresasBuscados, new TypeToken<ArrayList<String>>(){}.getType());
		*/
		String str[] = empresasBuscados.split(",");
	        
	    List<String> empresas = new ArrayList<String>();
	    empresas = Arrays.asList(str);
		
		
		
		Usuario usuario = repousuario.getUsuarioNombre(usuarioBuscado);
		Metodologia metodologiaOrdenamiento=usuario.getOrdenamiento(metodologiaBuscado);
		List<Empresa> empresa = repoempresa.getEmpresas(empresas);
		
		model.put("listaOrdenada",metodologiaOrdenamiento.evaluarMetodologia(empresa,new Integer(inicio), new Integer(fin),new Integer( anio)));
		//model.put("indicador", indicador);
		model.put("empresas", empresa);
		model.put("metodologia", metodologiaOrdenamiento);
	//	model.put("respuesta", periodo.getCuentas());
		return new ModelAndView(model, "metodologiaordenada.hbs");
	}
	
public ModelAndView cargarMetodologia(Request req, Response res)throws Exception{
		
		daoUsuario dao=new daoUsuario();
		daoIndicador daoi=new daoIndicador();
		daojson daoe=new daojson();
		dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\usuario.json");
		daoe.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
		
		repoUsuario repousuario=repoUsuario.getInstance(dao);
		repoEmpresa repoempresa=repoEmpresa.getInstance(daoe, daoi);
	
		String usuarioBuscado = req.params(":usuario");
	
		
		
		
		Usuario usuario = repousuario.getUsuarioNombre(usuarioBuscado);
		
		model.put("usuario", usuario);
		return new ModelAndView(model, "cargarMetodologia.hbs");
	}


public ModelAndView vermetodologias(Request req, Response res)throws Exception{
	
	daoUsuario dao=new daoUsuario();
	/*daoIndicador daoi=new daoIndicador();
	daojson daoe=new daojson();
	dao.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\usuario.json");
	daoe.setFilePath("C:\\Users\\omar cristian coca\\Desktop\\donde-invierto-master\\back\\src\\test\\resources\\empresas.json");
	*/
	repoUsuario repousuario=repoUsuario.getInstance(dao);
	

	String usuarioBuscado = req.params(":usuario");
	String nombreMetodologia = req.queryParams("nombre");
	String tipo = req.queryParams("tipo");
	String operador = req.queryParams("operador");
	String valor = req.queryParams("valor");
	String indicador = req.queryParams("indicador");
	String formula = req.queryParams("formula");
	IndicadorCompuesto indicador1=new IndicadorCompuesto(formula);
	indicador1.setNombre(indicador);
	Metodologia metodologia=new Metodologia(nombreMetodologia,tipo);
	metodologia.setcondicione(indicador1, operador, new Double(valor));
	Usuario usuario = repousuario.getUsuarioNombre(usuarioBuscado);
	usuario.setMetodologia(metodologia);
	repousuario.modificarUsuario(usuario);
	//Metodologia metodologiaOrdenamiento=usuario.getOrdenamiento(metodologiaBuscado);
	//List<Empresa> empresa = repoempresa.getEmpresas(empresas);
	
	//model.put("listaOrdenada",metodologiaOrdenamiento.evaluarMetodologia(empresa,new Integer(inicio), new Integer(fin),new Integer( anio)));
	//model.put("indicador", indicador);
	model.put("metodologias",usuario.getMetodologias());
	//model.put("metodologia", metodologiaOrdenamiento);
	model.put("usuario", usuario);
	return new ModelAndView(model, "vermetodologia.hbs");
}



}
