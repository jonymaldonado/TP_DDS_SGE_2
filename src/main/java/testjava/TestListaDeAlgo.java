package testjava;

import static org.junit.Assert.*;


import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Administrador;

public class TestListaDeAlgo {
	//private String archivoJson;
	private ArrayList<Transformador> transformadoresActivos;

	private Administrador admin;
	@Before
	
	public void inicializar() {

	//	archivoJson= String."document.json");
//		archivoJson="{\r\n" + 
//				"  \"transformadoresActivos\": [\r\n" + 
//				"    1,\r\n" + 
//				"    2,\r\n" + 
//				"    3\r\n" + 
//				"  ]\r\n" + 
//				"}";

		transformadoresActivos= new ArrayList<>();
	
		admin=new Administrador("Carlos", "Losares", 2334);
		
	};
	@Test
	public void testLista() throws FileNotFoundException {

		Transformador  t1= new Transformador(1, -34.61f, -58.41f, 5);
		Transformador  t2= new Transformador(2, -77.61f, -88.54f, 5);
		Transformador  t3= new Transformador(3, -88.22f,-8.23f,4);
		transformadoresActivos.add(t1);
		transformadoresActivos.add(t2);
		transformadoresActivos.add(t3);
		admin.cargarJsonTransformadores("document.json");
		Assert.assertEquals(admin.getListaDeTransformadoresActivos(), transformadoresActivos); 
	
	}

}
