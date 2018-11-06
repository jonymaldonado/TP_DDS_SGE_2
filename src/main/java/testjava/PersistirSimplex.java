package testjava;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.math3.optim.PointValuePair;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.dispositivos.repositorioDispositivo;
import ar.com.sge.geografia.Coordenada;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;

public class PersistirSimplex {
	
	private EntityManager entityManager ;
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
		
		
		
		@Test
		public void testPersistirSimplex() {
			
			entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			categoria1=new Categoria("residencial",12,1);
			cliente1=new Cliente("tomas","perez","dni",123212,482122,categoria1,41,42);
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
			entityManager.persist(cliente1);
			
			//prototype
			clientenuevo=new Cliente("lucas", "lopez", "dni",4232342,4321432, categoria1, 42, 21);
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
			entityManager.persist(trans1);
			entityManager.persist(trans2);
			transaction.commit();
			
		}
	}


