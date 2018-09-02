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

	public void addTransformador(Transformador untransformador) throws IOException {
		daoTransformador.add(untransformador);
	}

	public void modificarTransformador(Transformador transformador) throws IOException {
		daoTransformador.update(transformador);
	}
	

	/*public void ubicarCliente(RepoCliente unrepo) throws IOException{
		List<Cliente> listaUsuario=unrepo.getAllUsuario();
		listaTransformadores
		
		
		
	}*/
	


}
