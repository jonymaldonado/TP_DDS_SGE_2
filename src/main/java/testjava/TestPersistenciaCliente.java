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
import ar.com.sge.dispositivos.repositorioDispositivo;
import ar.com.sge.geografia.Coordenada;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.geografia.Zona;
import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Regla;
import ar.com.sge.reglas.Sensor;
import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Usuario;

public class TestPersistenciaCliente {
	
	private EntityManager entityManager ;
	private DispositivoInteligente tv , lavarropa,aire2;
	private DispositivoEstandar ventilador;
	private Categoria categoria1;
	private Cliente cliente1,clientenuevo;
	private Sensor sensor1,sensor2;
	private Regla regla1,regla2;
	private Actuador actuador1,actuador2;
	private repositorioDispositivo repo;
	
	
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
		aire2 =new DispositivoInteligente("lg", 0.18);
		aire2.setMinimoconsumo(90);
		aire2.setMaximoconsumo(370);
		repo=new repositorioDispositivo();
		//repo.agregar(lavarropa);
		//repo.agregar(tv);
		//repo.agregar(aire2);
		
		//cliente1.agregarDispositivosEstandares(ventilador);
		
		//EntityTransaction transaction = entityManager.getTransaction();
		
	}

/*
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
	}*/
	/*
	@Test
	public void TestRecuperarCliente() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		//Cliente cliente= (Cliente)entityManager.find(Usuario.class,2);
		Cliente cliente= entityManager.find(Cliente.class,1);
		//Coordenada coordenada1=cliente.getCoordenada();
		//coordenada1.setLatitud(10);
		//coordenada1.setLongitud(20);
		cliente.getCoordenada().setLatitud(10);
		cliente.getCoordenada().setLongitud(20);
		//entityManager.persist(cliente);
		//Cliente cliente1= entityManager.find(Cliente.class,2);
		//Assert.assertTrue(cliente1.getCoordenada().getLatitud()==10);
		//Assert.assertTrue(cliente1.getCoordenada().getLongitud()==20);
		
		
		transaction.commit();
	}
	
	@Test
	public void TestRecuperar() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		//Cliente cliente= (Cliente)entityManager.find(Usuario.class,2);
		Cliente cliente= entityManager.find(Cliente.class,1);
		//Coordenada coordenada1=cliente.getCoordenada();
		//coordenada1.setLatitud(10);
		//coordenada1.setLongitud(20);
		//System.out.println(cliente.getLstDispositivosEstandares());
		int a=cliente.getLstDispositivosInteligentes().size();
		Assert.assertTrue(cliente.getLstDispositivosInteligentes().size()==2);
		Assert.assertTrue(cliente.getLstDispositivosInteligentes().get(1).getId_Dispositivo()==3);
		Assert.assertTrue(cliente.getLstDispositivosInteligentes().get(0).getEstado().getNombre().equals("encendido"));
		System.out.println(cliente.getLstDispositivosInteligentes().get(0).getEstado().getFechaInicio());
		cliente.getLstDispositivosInteligentes().get(0).setNombre("TV Smart 55");
		
		//cliente.getCoordenada().setLongitud(20);
		//entityManager.persist(cliente);
		//Cliente cliente1= entityManager.find(Cliente.class,2);
		//Assert.assertTrue(cliente1.getCoordenada().getLatitud()==10);
		//Assert.assertTrue(cliente1.getCoordenada().getLongitud()==20);
		transaction.commit();
		
		
	}
	
	@Test
	public void cambioDeAccion() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Regla regla = entityManager.find(Regla.class,1);
		regla.setAccion("apagar");
		Assert.assertTrue(regla.getAccion().equals("apagar"));
		regla.setOperador("menor");
		Assert.assertTrue(regla.getOperador().equals("menor"));
		
		Actuador actuador = new Actuador();
		DispositivoInteligente dispositivo = entityManager.find(DispositivoInteligente.class,2);
		dispositivo.setActuador(actuador);
		
		Comando comando = new ComandoApagar("apagar",dispositivo);
		actuador.addcomando(comando);
		
		
		dispositivo.getSensor().setValor(1);
		
		transaction.commit();
		
		
		
	}
	*/
	/*
	@SuppressWarnings({ "unused", "unchecked" })
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
		
		
		Query query=entityManager.createQuery("select t from Transformador t ");
		List<Transformador> listaclientesbase=(List<Transformador>)query.getResultList();
		String apellido="lope";

		Query query1=entityManager.createQuery("from Usuario where tipo_usuario='cliente'");
		List<Cliente> listaclientes=(List<Cliente>)query1.getResultList();
		System.out.println(listaclientes.get(0).getApellido());
		//entityManager.createQuery("from usuario").executeUpdate();
		transaction.commit();
		//termina la transaccion
	}
	*/
	/*
	@Test
	public void TestPersistirRepo() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		//repo.agregar(lavarropa);
		//repo.agregar(tv);
		repo.agregar(aire2);
		entityManager.persist(repo);
		transaction.commit();
	}
		*/
	@Test
	public void TestPersistirCliente() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Cliente cliente2=new Cliente("juan1234","1234","juan", "lopez", "dni", 654321,1111111);
		Cliente cliente3=new Cliente("pepe321","1234","pepe", "garcia", "dni", 23123213,576435675);
		Cliente cliente4=new Cliente("luis12","1234","luis", "perez", "dni", 4123213,2222222);
		Administrador cliente5=new Administrador("pier23","1234","pier", "chero");
		entityManager.persist(cliente2);
		entityManager.persist(cliente3);
		entityManager.persist(cliente4);
		entityManager.persist(cliente5);
		
		transaction.commit();
		//termina la transaccion
	}
	/*
	@Test
	public void TestPersistirDispo() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		DispositivoInteligente b = new DispositivoInteligente("CTMR", 0.875);
		entityManager.persist(b);
		transaction.commit();
	}*/
	/*
	@Test
	public void TestPersistirDispo() throws CloneNotSupportedException {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Cliente clientebase = entityManager.find(Cliente.class,1);
		DispositivoInteligente dispo = (DispositivoInteligente) entityManager.createNativeQuery("select * from dispositivo where nombre = 'bgh'",DispositivoInteligente.class).getResultList().get(0);
		//repositorioDispositivo repobase=entityManager.find(repositorioDispositivo.class,2);
		//DispositivoInteligente dispo = repobase.
		System.out.println(dispo.getNombre()+" "+dispo.getId_Dispositivo());
		//DispositivoInteligente dispositivo = (DispositivoInteligente) dispo.clone();
		DispositivoInteligente dispositivo = new DispositivoInteligente(dispo.getNombre(),dispo.getKwPorHora());
		//dispositivo.setId_Dispositivo(15);
		//entityManager.persist(dispositivo);
		System.out.println("soy el nuevo "+dispositivo.getNombre()+" "+dispositivo.getId_Dispositivo());
		clientebase.agregarDispositivosInteligentes(dispositivo);
		transaction.commit();
	}*/
	/*
	@Test
	public void insertDispositivosEstandarRepo() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		DispositivoEstandar pc =new DispositivoEstandar("pc", 0.25);
		DispositivoEstandar televisor =new DispositivoEstandar("televisor", 0.20);
		DispositivoEstandar plancha =new DispositivoEstandar("plancha", 0.40);
		//DispositivoEstandar lampara =new DispositivoEstandar("Lámpara", 0.35);
		//repo.agregarStandar(lampara);
		repo.agregarStandar(pc);
		repo.agregarStandar(televisor);
		repo.agregarStandar(plancha);
		entityManager.persist(repo);
		transaction.commit();
	}*/
}
