package testjava;

import static org.junit.Assert.*;

import java.awt.List;

import org.apache.commons.math3.optim.PointValuePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.dispositivos.IDispositivo;
import ar.com.sge.dispositivos.repositorioDispositivo;
import ar.com.sge.geografia.Coordenada;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;
import java.util.ArrayList;
import java.util.*;


public class TestSimplex {
	
	private Cliente cliente1,clientenuevo;
	private repositorioDispositivo repositorio;
	private DispositivoInteligente Aire2200,tv32; 
	private DispositivoEstandar ventilador_pie,plancha_vapor;
	private DispositivoInteligente tv , lavarropa,ventilador;
	private Categoria categoria1;
	private PointValuePair solucion;
	private Transformador trans1, trans2;
	private Coordenada cor1, cor2;
	private Administrador admin;

	//private List<Cliente> lista;
	
	
	
	@Before
	public void init() {
		categoria1=new Categoria("residencial",12,1);
		cliente1=new Cliente("tomas","perez","dni",123212,482122,categoria1,0,41,42);
		tv =new DispositivoInteligente("LCD 40", 0.18);
		tv.setMinimoconsumo(90);
		tv.setMaximoconsumo(370);
		lavarropa =new DispositivoInteligente("Lavarropa 5kg", 0.875);
		lavarropa.setMinimoconsumo(6);
		lavarropa.setMaximoconsumo(30);
		ventilador =new DispositivoInteligente("ventilador techo", 0.06);
		ventilador.setMinimoconsumo(120);
		ventilador.setMaximoconsumo(360);
		cliente1.agregarDispositivosInteligentes(tv);
		cliente1.agregarDispositivosInteligentes(lavarropa);
		cliente1.agregarDispositivosInteligentes(ventilador);
		
		//prototype
		clientenuevo=new Cliente("lucas", "lopez", "dni",4232342,4321432, categoria1, 0, 42, 21);
		repositorio=new repositorioDispositivo();
		Aire2200=new DispositivoInteligente("aire2200",1.013); 
		tv32=new DispositivoInteligente("tv32", 0.055);
		ventilador_pie=new DispositivoEstandar("ventilador_pie", 0.09);
		plancha_vapor=new DispositivoEstandar("plancha_vapor", 0.75);
		repositorio.agregar(Aire2200);
		repositorio.agregar(tv32);
		repositorio.agregarStandar(plancha_vapor);
		repositorio.agregarStandar(ventilador_pie);
		
		//Transformadores
		trans1 = new Transformador();
		trans2 = new Transformador();
		cor1 = new Coordenada(3.5,5);
		cor2 = new Coordenada(6,7.5);
		admin = new Administrador("Pepito","Alvarez",1);
		trans1.setPosTransformador(cor1);
		trans2.setPosTransformador(cor2);
		admin.agregarCliente(clientenuevo);
		admin.agregarCliente(cliente1);
		//admin.setListaDeTransformadoresActivos();

		
	}
	
	@Test
	public void usarsimplex() {
		solucion=cliente1.consultarASimplex();
		Assert.assertEquals(760, solucion.getValue(), 0.01);
		Assert.assertEquals(360, solucion.getPoint()[0], 0.01); // <--- X2
		Assert.assertEquals(30, solucion.getPoint()[1], 0.01); // <--- X1
		Assert.assertEquals(370, solucion.getPoint()[2], 0.01); // <--- X0
	}
	@Test
	public void clonar_objeto() throws CloneNotSupportedException{
		repositorio.seleccionarInteligente(clientenuevo, "tv32");
		repositorio.seleccionarStandar(clientenuevo, "ventilador_pie");
		Assert.assertTrue(clientenuevo.cantidadDedispositivosInteligentes()==1);
		Assert.assertTrue(clientenuevo.cantidadDeDispositivosEstandares()==1);
		Assert.assertTrue(clientenuevo.cantidadDeDispositivos()==2);
	}
	


	

}
