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

import ar.com.sge.geografia.RepoTransformador;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.util.DaoJsonCliente;
import ar.com.sge.util.DaoJsonTransformadores;

public class TestTransformador {
	
	private RepoTransformador repoTransformador;
	private List<Transformador> listainicial;
	
	@Before
	public void init()throws IOException{
		DaoJsonTransformadores dao = new DaoJsonTransformadores();
		dao.setFilePath("document.json");
		repoTransformador = new RepoTransformador(dao);
		listainicial=repoTransformador.getAllTransformadores();
	
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
		Assert.assertTrue(trans1.getIdZonaCorrespondiente() == 5);
		
		
		
		
		trans1 = transformadores.get(1);
		
		Assert.assertTrue(trans1.getIdtransformador()==2);
		Assert.assertTrue(transformadores.size()==3);
		Assert.assertTrue(trans1.getIdZonaCorrespondiente() == 5);
		
	}

}
