package server;


import controllers.EmpresaController;
import controllers.indicadorController;
import controllers.inicio;
import controllers.inicioEmpresa;
import controllers.metodologiaController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class router {
	public static void configure(){
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		
		inicio inicio=new inicio();
		inicioEmpresa inicioController = new inicioEmpresa();
		EmpresaController empresaController = new EmpresaController();
		indicadorController indicadorController=new indicadorController();
		metodologiaController metodologiacontroller=new metodologiaController();
		
		Spark.get("/", inicio::inicio, engine);
		Spark.post("/usuario", inicio::inicioUsuario, engine);
		/*Spark.get("/usuario/empresas_indicadores", inicioController::inicio, engine);
		Spark.post("/usuario/periodos", empresaController::listarPeriodos, engine);
		Spark.post("/usuario/indicadores", indicadorController::mostrarformula, engine);
		Spark.post("/usuario/resultado", indicadorController::mostrarResultado, engine);
		//Spark.post("/taxativaconsulta", metodologiacontroller::mostrarConsulta, engine);
		Spark.post("/usuario/taxativaconsulta/:usuario", metodologiacontroller::mostrarConsulta, engine);
		Spark.post("/usuario/ordenamientoconsulta/:usuario", metodologiacontroller::mostrarlista, engine);
		Spark.get("/usuario/cargarMetodologia/:usuario", metodologiacontroller::cargarMetodologia, engine);
		Spark.post("/usuario/cargarMetodologia/:usuario/metodologias",metodologiacontroller::vermetodologias,engine);
		Spark.get("/usuario/periodoDetalle/:empresa/:mesinicio/:mesfin/:anio", empresaController::verdetalleVenta2,engine);
		//Spark.post("/periodoDetalle", empresaController::verdetalleVenta, engine);*/
	}

}