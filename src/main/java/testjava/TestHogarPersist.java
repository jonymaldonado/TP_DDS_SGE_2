package testjava;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Hogar;

public class TestHogarPersist {

	private EntityManager entityManager ;
	private Hogar hogar;
	private Cliente cliente;
	private DispositivoInteligente dispositivo;
	
	@Before
	public void init() throws IOException{
		entityManager = PerThreadEntityManagers.getEntityManager();
		hogar = new Hogar();
		cliente = new Cliente("pierchero","1234","jean", "chero", "dni",12345678,1111111111);
		
	}
	
	@Test
	public void TestPersistirHogar() throws IOException{
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		hogar.setCalle("juni");
		hogar.setNumero(456);
		entityManager.persist(hogar);
		cliente.agregarHogar(hogar);
		entityManager.persist(cliente);
		transaction.commit();
	}
	
	@Test
	public void TestPersistirDispositivoHogar() throws IOException{
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		dispositivo = entityManager.find(DispositivoInteligente.class,2);
		hogar.setCalle("once");
		hogar.setNumero(456);
		hogar.agregarDispositivo(dispositivo);
		entityManager.persist(hogar);
		transaction.commit();
	}
}
