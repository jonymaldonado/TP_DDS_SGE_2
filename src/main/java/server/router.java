package server;


import controllers.EmpresaController;
import controllers.HogarController;
import controllers.MapaController;
import controllers.ModeloDispositivos;
import controllers.Registro;
import controllers.TransformadoController;
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
		EmpresaController inteligenteController = new EmpresaController();
		indicadorController indicadorController=new indicadorController();
		metodologiaController metodologiacontroller=new metodologiaController();
		Registro registro = new Registro();
		HogarController hogar = new HogarController();
		MapaController mapa = new MapaController();
		TransformadoController trans = new TransformadoController();
		ModeloDispositivos modelo = new ModeloDispositivos();
		
		
		Spark.get("/Mapa", mapa::verMapa, engine);
		Spark.get("/", inicio::inicio, engine);
		Spark.post("/usuario", inicio::inicioUsuario, engine);
		Spark.get("/registro",registro::index,engine);
		Spark.post("/usuario/create",registro::create,engine);
		Spark.get("/registroHogar",hogar::index,engine);
		Spark.post("/hogar/create",hogar::create,engine);
		Spark.get("/hogar/listarDispositivos/:id",hogar::listarDispositivos,engine);
		//Spark.get("/dispositivo/:usuario/inteligentes",inteligenteController::agregarDispositivo,engine);
		/*Spark.get("/usuario/empresas_indicadores", inicioController::inicio, engine);
		Spark.post("/usuario/periodos", empresaController::listarPeriodos, engine);
		Spark.post("/usuario/indicadores", indicadorController::mostrarformula, engine);
		Spark.post("/usuario/resultado", indicadorController::mostrarResultado, engine);
		//Spark.post("/taxativaconsulta", metodologiacontroller::mostrarConsulta, engine);
		Spark.post("/usuario/taxativaconsulta/:usuario", metodologiacontroller::mostrarConsulta, engine);
		Spark.post("/usuario/ordenamientoconsulta/:usuario", metodologiacontroller::mostrarlista, engine);
		Spark.get("/usuario/cargarMetodologia/:usuario", metodologiacontroller::cargarMetodologia, engine);*/
		Spark.get("/usuario/:usuario/inteligentes",inteligenteController::verdetalleInteligente,engine);
		Spark.get("/usuario/:usuario/inteligentes/:Id_Dispositivo/regla",inteligenteController::verRegla,engine);
		Spark.get("/usuario/:usuario/standar",inteligenteController::verdetalleStandar,engine);
		Spark.get("/usuario/:usuario/resultado",inteligenteController::verResultado,engine);
		//Spark.get("/usuario/periodoDetalle/:empresa/:mesinicio/:mesfin/:anio", empresaController::verdetalleVenta2,engine);
		//Spark.post("/periodoDetalle", empresaController::verdetalleVenta, engine);
		Spark.get("/transformador",trans::mostrarConsumo);
		Spark.get("/agregarEstandar/:usuario",inteligenteController::agregarEstandar);
		Spark.get("/consumo",inicio::calcularConsumo);
		Spark.get("/modeloInteligente",modelo::listarInteligente,engine);
		Spark.get("/modeloEstandar",modelo::listarEstandar,engine);
		Spark.get("/deleteInteligente",modelo::deleteInteligente);
		Spark.get("/deleteEstandar",modelo::deleteEstandar);
		Spark.get("/addModeloInteligente",modelo::addModeloInteligente);
		Spark.get("/addModeloEstandar",modelo::addModeloEstandar);
		
	}

}
