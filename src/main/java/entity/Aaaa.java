package entity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.com.sge.geografia.Zona;
import controller.ZonaController;

public class Aaaa {

	public static EntityManagerFactory emf;
    public static void main(String[] args) {
        /*CategoriasJpaController cat = new CategoriasJpaController(emf);
        Categorias categoria = new Categorias("R0",5.5f,5.5f,5,2);
        cat.create(categoria);
    	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		 Zonas zona = new Zonas();
		 zona.setNombreZona("asdasdasdsdasdsadsadas");
	     //zona.setNombreZona();
		entityManager.persist(zona);
		transaction.commit();
    	
    	*/
    	emf = Persistence.createEntityManagerFactory("db");
        Zona zona = new Zona();
        zona.setNombreDeLaZona("la zona2ss");
        ZonaController z = new ZonaController(emf);
        z.crear(zona);
        
    }

}
