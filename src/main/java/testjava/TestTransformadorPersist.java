package testjava;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.*;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.geografia.Transformador;

public class TestTransformadorPersist {
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	Transformador unTransformador;
	@Before
	public void init() {
		/*transaction.begin();// comenza la transaccion
		
		
		entityManager.persist(unTransformador);//guardar registro en base de datos
		
		transaction.commit();//termina la transaccion*/
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
	
		transaction.begin();// comenza la transaccion
		
		Transformador unTransformador=new Transformador(333,43.4,43.5,2); 
		entityManager.persist(unTransformador);//guardar registro en base de datos
		transaction.commit();//termina la transaccion
	}
	

	@Test
	public void test() {
		
		fail("Not yet implemented");
	}

}
