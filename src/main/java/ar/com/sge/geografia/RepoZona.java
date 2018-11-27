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
			
			for (Transformador transformador : repoTransformador.getTransformadores()) {
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
	
	public void actualizarZonas(RepoTransformador repoTransformador) throws IOException{
		
		try {
			repoTransformador.actualizarListasDeTransformadoresbase();
			for (Transformador transformador : repoTransformador.getTransformadores()) {
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
		
		
	//	entityManager.createQuery("delete from transformador").executeUpdate(); 
		//entityManager.createQuery("delete from zona").executeUpdate();
		
		for(Zona z: this.listaZona) {
			entityManager.persist(z);
		}
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public void persistir2() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<Zona> listaZonabase=(List<Zona>) entityManager.createNativeQuery("select * from zona", Zona.class).getResultList(); 
		//entityManager.createQuery("delete from zona").executeUpdate();
		
//		transaction.commit();
		System.out.println(this.listaZona);
		System.out.println(listaZonabase);
		if (listaZonabase.size()!=0) {
				
			for(Zona z: this.listaZona) {
				for(Zona zonabase: listaZonabase) {
					if(z.getIdZona()==zonabase.getIdZona()) {
						comparar(zonabase, z);
						
					}
					
				}
				//entityManager.persist(z);
			}
			transaction.commit();
		}
	   } 
	public void comparar(Zona zona1,Zona zona2) {
		

		for(Transformador t: zona1.getListaDeTransformadores()) {
			boolean activo = false;
			for(Transformador transformador: zona2.getListaDeTransformadores()) {
				if(t.getIdtransformador()==transformador.getIdtransformador()) {
					activo = true;
				}
			}
			t.setActivo(activo);
			//zona1.agregarTransformador(Transformador);
			
		}
		
		for(Transformador t: zona2.getListaDeTransformadores()) {
			boolean encontrado = false;
			for(Transformador transformador: zona1.getListaDeTransformadores()) {
				if(t.getIdtransformador()==transformador.getIdtransformador()) {
					encontrado = true;
				}
			}
			if(encontrado == false) {
				zona1.agregarTransformador(t);
			}
			
		}
		
	}
	

	/*public void ubicarCliente(RepoCliente unrepo) throws IOException{
		List<Cliente> listaUsuario=unrepo.getAllUsuario();
		listaTransformadores
		
		
		
	}*/
	


}
