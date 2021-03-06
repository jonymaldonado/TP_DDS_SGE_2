package testjava;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.comandos.Comando;
import ar.com.sge.comandos.ComandoApagar;
import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.geografia.Coordenada;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.geografia.Zona;
import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Regla;
import ar.com.sge.reglas.Sensor;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Usuario;

public class PersistirCliente {
	
	private EntityManager entityManager ;
	private DispositivoInteligente tv , lavarropa;
	private DispositivoEstandar ventilador;
	private Categoria categoria1;
	private Cliente cliente1,clientenuevo;
	private Sensor sensor1,sensor2;
	private Regla regla1,regla2;
	private Actuador actuador1,actuador2;
	
	
	@Before
	public void init() {
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		tv =new DispositivoInteligente("LCD 40", 0.18);
		tv.setMinimoconsumo(90);
		tv.setMaximoconsumo(370);
		ventilador=new  DispositivoEstandar("ventilador", 2);
		lavarropa =new DispositivoInteligente("Lavarropa 5kg", 0.875);
		lavarropa.setMinimoconsumo(6);
		lavarropa.setMaximoconsumo(30);
		sensor1 =new Sensor();
		sensor2=new Sensor();
		actuador1=new Actuador();
		actuador2=new Actuador();
		regla1=new Regla("mayor", 2, "encender");
		regla2=new Regla("igual", 21, "apagar");
		sensor1.agregarObservador(regla1);
		sensor2.agregarObservador(regla2);
		tv.setSensor(sensor1);
		lavarropa.setSensor(sensor2);
		categoria1=new Categoria("residencial",12,1);
		cliente1=new Cliente("tomas","perez","dni",123212,482122,categoria1,41,42);
		cliente1.agregarDispositivosInteligentes(tv);
		cliente1.agregarDispositivosInteligentes(lavarropa);
		cliente1.setNombre_usuario("tomi");
		cliente1.setContrasenia("1234");
		
		
		//cliente1.agregarDispositivosEstandares(ventilador);
		
		//EntityTransaction transaction = entityManager.getTransaction();
		
	}


	@Test
	public void TestPersistirCliente() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Zona unazona =new Zona();
		unazona.setNombreDeLaZona("boedo");
		unazona.setRadioEnMetros(24);
		Coordenada coordenada1=new Coordenada(45.2, 48.2);
		unazona.setPosZonaCentral(coordenada1);
		Transformador unTransformador=new Transformador(32.1,43.4,unazona);
		unazona.agregarTransformador(unTransformador);
		entityManager.persist(unazona);
		entityManager.persist(categoria1);
		Cliente cliente2=new Cliente("luca", "lope", "dni", 2493, 1521, 42.2, 42.1);
		tv.encender();
		cliente2.agregarDispositivosInteligentes(tv);
		cliente2.agregarDispositivosInteligentes(lavarropa);
		cliente2.agregarDispositivosEstandares(ventilador);
		cliente2.setNombre_usuario("lucalope");
		cliente2.setContrasenia("1112");
		cliente2.setCategoria(categoria1);
		unTransformador.agregarCliente(cliente2);
		
		 
		//entityManager.persist(cliente1);//guardar registro en base de datos
		entityManager.persist(cliente2);
		
		transaction.commit();
		//termina la transaccion
	}
}