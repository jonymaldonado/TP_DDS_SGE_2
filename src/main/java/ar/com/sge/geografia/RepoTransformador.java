package ar.com.sge.geografia;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.RepoCliente;
import ar.com.sge.usuarios.Usuario;
import ar.com.sge.util.DaoJsonTransformadores;
import ar.com.sge.util.DaoTransFormadores;
public class RepoTransformador {
	private DaoTransFormadores daoTransformador;

	private List<Transformador> listaTransformadores;


	private static RepoTransformador instance;

	public RepoTransformador(DaoJsonTransformadores dao) {
		this.daoTransformador = dao;

	}

	public static RepoTransformador getInstance(DaoJsonTransformadores dao) {
		if (instance == null)
			instance = new RepoTransformador(dao);
		return instance;
	}

	public void delete(Transformador transformador) throws IOException {
		this.daoTransformador.delete(transformador);

	}

	public List<Transformador> getAllTransformadores() throws IOException {
		return daoTransformador.getAll();
	}
	public void asignarTransformadores() throws IOException{
		listaTransformadores=getAllTransformadores();
	}
	public List<Transformador> getTransformadores() throws IOException {
		return listaTransformadores;
	}

	public void addTransformador(Transformador untransformador) throws IOException {
		daoTransformador.add(untransformador);
	}

	public void modificarTransformador(Transformador transformador) throws IOException {
		daoTransformador.update(transformador);
	}
	
	public void actualizarListasDeTransformadores(RepoCliente repoCliente) throws IOException{
		Transformador transfElegido=new Transformador();
		asignarTransformadores();
		try {
			
			double distancia;
			for (Cliente cliente : repoCliente.getAllUsuario()) {
				double transformadorMasCercano = cliente.getDomicilio().distanciaAlPunto(listaTransformadores.get(0).getPosTransformador());
				for (Transformador transformador : listaTransformadores) {
					distancia=cliente.getDomicilio().distanciaAlPunto(transformador.getPosTransformador());
					distancia=Math.abs(distancia);
					transformadorMasCercano=Math.abs(transformadorMasCercano);
					if (transformadorMasCercano>=distancia)  {
						transformadorMasCercano=distancia;
						transfElegido=transformador;
						//cliente.setIdTransformadorCorrespondiente(transformador.getIdtransformador()); 
					};//fin if
				};//fin for transfo
				//transfElegido=this.buscarTransformador(cliente.getIdTransformadorCorrespondiente());
				transfElegido.agregarCliente(cliente);
			};//fin for clientes
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Transformador> getAllUsuario() throws IOException {
		return daoTransformador.getAll();
	}
	
	
	

	@SuppressWarnings("unchecked")
	public void actualizarListasDeTransformadoresbase() throws IOException{
		Transformador transfElegido=new Transformador();
		asignarTransformadores();
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		
		List<Cliente> listaclientesbase=(List<Cliente>) entityManager.createQuery("from Usuario where tipo_usuario='cliente'").getResultList(); 
		//entityManager.createQuery("from usuario").executeUpdate();
		
		
		try {
			
			double distancia;
			for (Cliente cliente : listaclientesbase) {
				double transformadorMasCercano = cliente.getDomicilio().distanciaAlPunto(listaTransformadores.get(0).getPosTransformador());
				for (Transformador transformador : listaTransformadores) {
					distancia=cliente.getDomicilio().distanciaAlPunto(transformador.getPosTransformador());
					distancia=Math.abs(distancia);
					transformadorMasCercano=Math.abs(transformadorMasCercano);
					if (transformadorMasCercano>=distancia)  {
						transformadorMasCercano=distancia;
						transfElegido=transformador;
						//cliente.setIdTransformadorCorrespondiente(transformador.getIdtransformador()); 
					};//fin if
				};//fin for transfo
				//transfElegido=this.buscarTransformador(cliente.getIdTransformadorCorrespondiente());
				transfElegido.agregarCliente(cliente);
			};//fin for clientes
		} catch (Exception e) {
			// TODO: handle exception
		}
		transaction.commit();
	}
	

	/*public void ubicarCliente(RepoCliente unrepo) throws IOException{
		List<Cliente> listaUsuario=unrepo.getAllUsuario();
		listaTransformadores
		
		
		
	}*/
	


}
