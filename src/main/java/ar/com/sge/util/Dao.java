package ar.com.sge.util;

import java.io.IOException;
import java.util.ArrayList;

import ar.com.sge.usuarios.Cliente;

public interface Dao {
	public void add(Cliente cliente) throws IOException;
	public void delete(Cliente cliente) throws IOException;
	public ArrayList<Cliente> getAll() throws IOException;
	public void update(Cliente cliente);

}
