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
import ar.com.sge.geografia.RepoZona;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.geografia.Zona;
import ar.com.sge.util.DaoJsonCliente;
import ar.com.sge.util.DaoJsonTransformadores;
import ar.com.sge.util.DaoJsonZona;

public class TestZona {
	
	private RepoZona repoZona;
	private List<Zona> listainicial;
	
	@Before
	public void init()throws IOException{
		DaoJsonZona dao = new DaoJsonZona();
		dao.setFilePath("jsonZona.json");
		repoZona = new RepoZona(dao);
		listainicial=repoZona.getAllZonas();
	
	}
	
	@After
	public void reiniciarEstadoInit() throws IOException{
		
		DaoJsonZona dao = new DaoJsonZona();
		dao.setFilePath("jsonZona.json");
		String indicadoresstring=dao.getMyGson().toJson(listainicial);
		PrintWriter pw = new PrintWriter(dao.getFilePath());
		pw.close();
		BufferedWriter buffertowrite = new BufferedWriter(new FileWriter(dao.getFilePath(), true));
		//this.bufferToWrite.append(empleadoSerialized);
		buffertowrite.write(indicadoresstring);
		buffertowrite.close();
	}

	@Test
	public void ObtenerZonas()throws IOException {
		
		List<Zona> zonas = repoZona.getAllZonas();
		Zona zona1=zonas.get(0);
		Assert.assertTrue(zona1.getIdZona()==5);
		Assert.assertTrue(zonas.size()==3);
		Assert.assertEquals(zona1.getNombreDeLaZona(),"CapitalFederal");
		Assert.assertTrue(zona1.getRadioEnMetros() == 50);
		
		
		
		
		zona1 = zonas.get(1);
		
		Assert.assertTrue(zona1.getIdZona()==4);
		Assert.assertTrue(zonas.size()==3);
		Assert.assertEquals(zona1.getNombreDeLaZona(), "Lugano");
		Assert.assertTrue(zona1.getRadioEnMetros() == 35);
		
	}

}
