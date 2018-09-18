package testjava;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.estados.Encendido;
import ar.com.sge.estados.Estado;
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
	private Transformador transformador1,transformador2;
	private Categoria unaCategoriaR1;
	private DispositivoInteligente tv,aire,heladera,pc,lampara;
	private Estado estadoTV,estadoAire,estadoHeladera,estadoPC,estadoLampara;
	private LocalDateTime inicioTV,finTV,inicioAire,finAire,inicioHeladera,finHeladera,inicioPC,finPC,inicioLampara,finLampara;
	
	@Before
	public void init()throws IOException{
		DaoJsonTransformadores dao = new DaoJsonTransformadores();
		dao.setFilePath("document.json");
		repoTransformador = new RepoTransformador(dao);
		listainicial=repoTransformador.getAllTransformadores();
		
		transformador1=new Transformador(001, -43.55f, 3.44f,01);
		transformador2=new Transformador(002, 54.47f,-24.55f,02);
		
		unaCategoriaR1= new Categoria("R1", (float) 18.76,(float) 0.644);
		
		cliente1= new Cliente("Carlos","Ligorria", "DNI", 14555666, 54363366, unaCategoriaR1, 0, 54.44f, 2.65f);
		cliente2= new Cliente("Leonardo","Silva", "DNI", 66664554, 86543345, unaCategoriaR1, 0, 55.44f, 65.65f);
		cliente3= new Cliente("Gustavo","Apaza", "DNI", 37543232, 54674744, unaCategoriaR1, 0, 22.44f, 6.87f);
		cliente4= new Cliente("Roberto","Castro", "DNI", 44545532, 54676744, unaCategoriaR1, 0, -5.44f, 33.65f);
		cliente5= new Cliente("Susana ","Mosquera", "DNI", 54876578, 78874744, unaCategoriaR1, 0, 9.44f, 65.00f);
		
		transformador1.agregarCliente(cliente1);
		transformador1.agregarCliente(cliente2);
		
		transformador2.agregarCliente(cliente3);
		transformador2.agregarCliente(cliente4);
		transformador2.agregarCliente(cliente5);
		
		inicioTV = LocalDateTime.of(2018, 6, 5, 10, 00);
		finTV = LocalDateTime.of(2018, 6, 5, 12, 00);
		estadoTV = new Encendido("encendido", inicioTV, finTV, 0.16);
		
		inicioAire = LocalDateTime.of(2018, 6, 5, 14, 00);
		finAire = LocalDateTime.of(2018, 6, 5, 15, 00);
		estadoAire = new Encendido("encendido", inicioAire, finAire, 1013);
		
		inicioHeladera = LocalDateTime.of(2018, 6, 5, 14, 00);
		finHeladera = LocalDateTime.of(2018, 6, 5, 17, 00);
		estadoHeladera = new Encendido("encendido", inicioHeladera, finHeladera, 0.225);
		
		inicioPC = LocalDateTime.of(2018, 6, 5, 14, 00);
		finPC = LocalDateTime.of(2018, 6, 5, 17, 00);
		estadoPC = new Encendido("encendido", inicioPC, finPC, 1.2);
		
		inicioLampara = LocalDateTime.of(2018, 6, 7, 14, 00);
		finLampara = LocalDateTime.of(2018, 6, 7, 17, 00);
		estadoLampara = new Encendido("encendido", inicioLampara, finLampara, 0.06);
		
		tv=new DispositivoInteligente("unTV40", 0.08);
		aire=new DispositivoInteligente("aire1", 1013);
		heladera=new DispositivoInteligente("unaHeladera", 0.075);
		pc=new DispositivoInteligente("pc", 0.4);
		lampara=new DispositivoInteligente("lampara", 0.02);
		
		tv.agregarEstado(estadoTV);
		aire.agregarEstado(estadoAire);
		heladera.agregarEstado(estadoHeladera);
		pc.agregarEstado(estadoPC);
		lampara.agregarEstado(estadoLampara);
		
		tv.setInicioPeriodo(LocalDateTime.of(2018, 6, 1, 00, 00));
		aire.setInicioPeriodo(LocalDateTime.of(2018, 6, 1, 00, 00));
		heladera.setInicioPeriodo(LocalDateTime.of(2018, 6, 1, 00, 00));
		pc.setInicioPeriodo(LocalDateTime.of(2018, 6, 1, 00, 00));
		lampara.setInicioPeriodo(LocalDateTime.of(2018, 6, 1, 00, 00));
		
		cliente1.agregarDispositivosInteligentes(tv);
		cliente2.agregarDispositivosInteligentes(aire);
		cliente3.agregarDispositivosInteligentes(heladera);
		cliente4.agregarDispositivosInteligentes(pc);
		cliente5.agregarDispositivosInteligentes(lampara);
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
	
	@Test
	public void obtenerConsumoTransformador() {
		Assert.assertTrue(transformador1.totalDeConsumoDelTransformadores() == 1013.16f);
		Assert.assertTrue(transformador2.totalDeConsumoDelTransformadores() == 1.485f);
	}

}
