package ar.com.sge.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.com.sge.usuarios.Cliente;

public interface Dao{
	public void add(Cliente cliente) throws IOException;
	public void delete(Cliente cliente) throws IOException;
	public List<Cliente> getAll() throws IOException;
	public void update(Cliente cliente);

}