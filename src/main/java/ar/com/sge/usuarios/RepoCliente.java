package ar.com.sge.usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.sge.util.Dao;
import ar.com.sge.util.DaoJsonCliente;

public class RepoCliente {
	private Dao daocliente;

	private static RepoCliente instance;

	public RepoCliente(DaoJsonCliente dao) {
		this.daocliente = dao;

	}

	public static RepoCliente getInstance(DaoJsonCliente dao) {
		if (instance == null)
			instance = new RepoCliente(dao);
		return instance;
	}

	public void delete(Cliente cliente) throws IOException {
		this.daocliente.delete(cliente);

	}

	public List<Cliente> getAllUsuario() throws IOException {
		return daocliente.getAll();
	}

	/*
	 * public Cliente getUsuario(String nombre,String clave)throws IOException{
	 * List<Cliente> lista =
	 * this.daocliente.getAll().stream().filter(s->s.getNombre().equalsIgnoreCase(
	 * nombre)&&
	 * s.getContrase�a().equalsIgnoreCase(clave)).collect(Collectors.toList());
	 * return lista.get(0); }
	 */

	public void addCliente(Cliente cliente) throws IOException {
		daocliente.add(cliente);
	}

	public void modificarCliente(Cliente cliente) throws IOException {
		daocliente.update(cliente);
	}

}