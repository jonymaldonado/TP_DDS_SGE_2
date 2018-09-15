package testjava;

import static org.junit.Assert.*;

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

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.geografia.RepoTransformador;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.util.DaoJsonCliente;
import ar.com.sge.util.DaoJsonTransformadores;

public class TestTransformador {
	
	private RepoTransformador repoTransformador;
	private List<Transformador> listainicial;
	private Cliente cliente1,cliente2,cliente3,cliente4,cliente5;
	private Transformador transformador1,transformador2,transformador3;
	private Categoria unaCategoriaR1;
	private DispositivoInteligente unTV40,aire1,unaHeladera,unaHeladera2,pc1,pc2,aire2;
	
	@Before
	public void init()throws IOException{
		DaoJsonTransformadores dao = new DaoJsonTransformadores();
		dao.setFilePath("document.json");
		repoTransformador = new RepoTransformador(dao);
		listainicial=repoTransformador.getAllTransformadores();
		transformador1=new Transformador(001, -43.55f, 3.44f,01);
		transformador2=new Transformador(002, 54.47f,-24.55f,02);
		transformador3=new Transformador(003, 55.55f, 3.23f,01);
		unaCategoriaR1= new Categoria("R1", (float) 18.76,(float) 0.644);
		cliente1= new Cliente("Carlos","Ligorria", "DNI", 14555666, 54363366, unaCategoriaR1, 0, 54.44f, 2.65f);
		cliente2= new Cliente("Leonardo","Silva", "DNI", 66664554, 86543345, unaCategoriaR1, 0, 55.44f, 65.65f);
		cliente3= new Cliente("Gustavo","Apaza", "DNI", 37543232, 54674744, unaCategoriaR1, 0, 22.44f, 6.87f);
		cliente4= new Cliente("Roberto","Castro", "DNI", 44545532, 54676744, unaCategoriaR1, 0, -5.44f, 33.65f);
		cliente5= new Cliente("Susana ","Mosquera", "DNI", 54876578, 78874744, unaCategoriaR1, 0, 9.44f, 65.00f);
		unTV40=new DispositivoInteligente("unTV40", 0.08);
		aire1=new DispositivoInteligente("aire1", 1013f);
		unaHeladera=new DispositivoInteligente("unaHeladera", 0.075);
		unaHeladera2=new DispositivoInteligente("unaHeladera2", 0.09);
		pc1=new DispositivoInteligente("pc1", 0.4f);
		pc2=new DispositivoInteligente("pc2", 0.4f);
		aire2 =new DispositivoInteligente("aire2", 1613f);
	
	}
	
	@After
	public void reiniciarEstadoInit() throws IOException{
		
		DaoJsonCliente dao = new DaoJsonCliente();
		dao.setFilePath("document.json");
		String indicadoresstring=dao.getMyGson().toJson(listainicial);
		PrintWriter pw = new PrintWriter(dao.getFilePath());
		pw.close();
		BufferedWriter buffertowrite = new BufferedWriter(new FileWriter(dao.getFilePath(), true));
		//this.bufferToWrite.append(empleadoSerialized);
		buffertowrite.write(indicadoresstring);
		buffertowrite.close();
	}

	@Test
	public void ObtenerTransformadores()throws IOException {
		
		List<Transformador> transformadores = repoTransformador.getAllTransformadores();
		Transformador trans1=transformadores.get(0);
		Assert.assertTrue(trans1.getIdtransformador()==1);
		Assert.assertTrue(transformadores.size()==3);
		Assert.assertTrue(trans1.getIdZonaCorrespondiente() == 4);
		
		
		
		
		trans1 = transformadores.get(1);
		
		Assert.assertTrue(trans1.getIdtransformador()==2);
		Assert.assertTrue(transformadores.size()==3);
		Assert.assertTrue(trans1.getIdZonaCorrespondiente() == 4);
		
	}

}
