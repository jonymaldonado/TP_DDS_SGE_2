package testjava;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.RepoCliente;
import ar.com.sge.util.DaoJsonCliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestClientes {
	private RepoCliente repoCliente;
	private List<Cliente> clientes;
	private List<Cliente> listainicial;
	private Cliente clienteNuevo;
	
	@Before
	public void init()throws IOException{
		DaoJsonCliente dao = new DaoJsonCliente();
		dao.setFilePath("jsonClientes.json");
		repoCliente = new RepoCliente(dao);
		listainicial=repoCliente.getAllUsuario();
	
	}
	
	@After
	public void reiniciarEstadoInit() throws IOException{
		
		DaoJsonCliente dao = new DaoJsonCliente();
		dao.setFilePath("jsonClientes.json");
		String indicadoresstring=dao.getMyGson().toJson(listainicial);
		PrintWriter pw = new PrintWriter(dao.getFilePath());
		pw.close();
		BufferedWriter buffertowrite = new BufferedWriter(new FileWriter(dao.getFilePath(), true));
		//this.bufferToWrite.append(empleadoSerialized);
		buffertowrite.write(indicadoresstring);
		buffertowrite.close();
	}
	
	@Test
	public void ObtenerclienteTest()throws IOException {
		
		clientes = repoCliente.getAllUsuario();
		Cliente cliente1=clientes.get(0);
		String nombre ="Snapchat";
		Assert.assertTrue(cliente1.cantidadDeDispositivos()==3);
		Assert.assertTrue(clientes.size()==2);
		Assert.assertEquals(cliente1.getNombre(),"tomi");
		Assert.assertEquals(cliente1.getApellido(),"perez");
		Assert.assertEquals(cliente1.getCategoria(),"r1");
		
		
		
		cliente1 = clientes.get(1);
		
		Assert.assertTrue(cliente1.cantidadDeDispositivos()==2);
		Assert.assertEquals(cliente1.getNombre(),"roberto");
		Assert.assertEquals(cliente1.getApellido(),"gonzales");
		Assert.assertEquals(cliente1.getCategoria(),"r2");
		
	}
	
	@Test
	public void cantidadEncendidoclienteTest()throws IOException {
		clientes = repoCliente.getAllUsuario();
		Cliente cliente1=clientes.get(0);
		Assert.assertTrue(cliente1.cantidadDispositivosEncendidos()==2);
		
		cliente1=clientes.get(1);
		Assert.assertTrue(cliente1.cantidadDispositivosEncendidos()==2);
	}
	
	@Test
	public void cantidadApagadosclienteTest()throws IOException {
		clientes = repoCliente.getAllUsuario();
		Cliente cliente1=clientes.get(0);
		Assert.assertTrue(cliente1.cantidadDispositivosApagados()==1);
		
		cliente1=clientes.get(1);
		Assert.assertTrue(cliente1.cantidadDispositivosApagados()==0);
	}
	
	@Test
	public void agregarclienteTest()throws IOException {
		Cliente clientenuevo= new Cliente("cris","sele","dni",40908923,4392382,10.4,14.6);
		clientenuevo.setTelefono(42981321);
		
		
		repoCliente.addCliente(clientenuevo);
		clientes = repoCliente.getAllUsuario();
		Assert.assertTrue(clientes.size()==3);
	}
	
	
}
