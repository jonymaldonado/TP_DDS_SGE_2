package ar.com.sge.geografia;

import java.io.IOException;
import java.util.List;

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

	public void addTransformador(Transformador untransformador) throws IOException {
		daoTransformador.add(untransformador);
	}

	public void modificarTransformador(Transformador transformador) throws IOException {
		daoTransformador.update(transformador);
	}
	
	public void actualizarListasDeTransformadores(RepoCliente repoCliente) throws IOException{
		Transformador transfElegido=new Transformador();
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
	

	/*public void ubicarCliente(RepoCliente unrepo) throws IOException{
		List<Cliente> listaUsuario=unrepo.getAllUsuario();
		listaTransformadores
		
		
		
	}*/
	


}
