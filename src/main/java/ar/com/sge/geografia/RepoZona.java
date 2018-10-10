package ar.com.sge.geografia;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.RepoCliente;
import ar.com.sge.util.DaoJsonZona;
import ar.com.sge.util.DaoZona;


public class RepoZona {
	private DaoZona daoZonas;

	private List<Zona> listaZona;


	private static RepoZona instance;

	public RepoZona(DaoJsonZona dao) {
		this.daoZonas = dao;

	}

	public static RepoZona getInstance(DaoJsonZona dao) {
		if (instance == null)
			instance = new RepoZona(dao);
		return instance;
	}

	public void delete(Zona zona) throws IOException {
		this.daoZonas.delete(zona);

	}

	public List<Zona> getAllZonas() throws IOException {
		
		this.listaZona = daoZonas.getAll();
		return this.listaZona;
		
	}
	/*
	public void asignarZonas() throws IOException{
		this.listaZona=getAllZonas();
	}*/

	public void addZona(Zona unaZona) throws IOException {
		daoZonas.add(unaZona);
	}
	
	public List<Zona> getZona() throws IOException{
		return this.listaZona;
	}

	public void modificarZona(Zona zona) throws IOException {
		daoZonas.update(zona);
	}
	
	public void actualizarListasDeZonas(RepoTransformador repoTransformador) throws IOException{

		try {
			
			for (Transformador transformador : repoTransformador.getAllTransformadores()) {
				//double transformadorMasCercano = cliente.getDomicilio().distanciaAlPunto(listaTransformadores.get(0).getPosTransformador());
				for (Zona zona : this.getZona()) {
					
					if(transformador.getZona().getIdZona()==zona.getIdZona()) {
						System.out.println(transformador.getZona().getIdZona());
						System.out.println(zona.getIdZona());
						zona.agregarTransformador(transformador);
					}
				}
			}
					
					
			
		} catch (Exception e) {
		
		}
	}
	
	public void persistir() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		
		entityManager.createQuery("delete from transformador").executeUpdate();
		entityManager.createQuery("delete from zona").executeUpdate();
		
		for(Zona z: this.listaZona) {
			entityManager.persist(z);
		}
		transaction.commit();
	}
	

	/*public void ubicarCliente(RepoCliente unrepo) throws IOException{
		List<Cliente> listaUsuario=unrepo.getAllUsuario();
		listaTransformadores
		
		
		
	}*/
	


}
