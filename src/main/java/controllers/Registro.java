package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Registro {

	private Map<String,Object> model=new HashMap<>();
	
	public ModelAndView index(Request req, Response res)throws IOException{
		
		return new ModelAndView(model, "registrar.hbs");
	}
	
	public ModelAndView create(Request req, Response res)throws IOException{
		
		return new ModelAndView(model, "inicio.hbs");
	}
	

}
