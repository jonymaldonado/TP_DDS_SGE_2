package ar.com.sge.usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ar.com.sge.util.Dao;
import ar.com.sge.util.DaoJsonCliente;
import ar.com.sge.util.ModelHelper;

public class RepoCliente {
	private ModelHelper mh;
	private static RepoCliente instance;
	private List<Cliente> clientes;
	private Dao daocliente;
	
	
	public RepoCliente(ModelHelper mh, DaoJsonCliente dao) {
		this.mh = mh;
		this.daocliente = dao;
	}
	
	public static RepoCliente getInstance(ModelHelper mh, DaoJsonCliente dao) {
		if (instance == null)
			instance = new RepoCliente(mh, dao);
		return instance;
	}
	
	public void addCliente(Cliente unCliente) {
		this.mh.agregar(unCliente);
	}
	public void delete(Cliente unCliente) throws IOException {
			this.mh.eliminar(unCliente);
	}
	
	public void modificarCliente(Cliente unCliente) throws IOException {
		this.mh.modificar(unCliente);
	}
	
	public List<Cliente> getAllUsuario() throws IOException {
		return clientes;
	}
	
	public void importarClientes ()  throws IOException {
		this.clientes = this.daocliente.getAll();	
	}	
	}