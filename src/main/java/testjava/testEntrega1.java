package testjava;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.sge.*;
import ar.com.sge.comandos.AdapterAhorroDeEnergia;
import ar.com.sge.comandos.AdapterApagar;
import ar.com.sge.comandos.AdapterEncender;
import ar.com.sge.comandos.Comando;
import ar.com.sge.comandos.ComandoAhorroDeEnergia;
import ar.com.sge.comandos.ComandoApagar;
import ar.com.sge.comandos.ComandoEncender;
import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.dispositivos.Modulo;
import ar.com.sge.estados.AhorroDeEnergia;
import ar.com.sge.estados.Apagado;
import ar.com.sge.estados.Encendido;
import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Regla;
import ar.com.sge.reglas.Sensor;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;

public class testEntrega1 {
	private Actuador actuador1;
	private Sensor sensor1;
	private Regla regla1;
	private Comando comandoprender;
	private Comando comandoApagar;
	private Comando comandoAhorroDeEnergia;
	private DispositivoInteligente inteligente1,inteligente2;
	private Encendido estado1 ,estado2,estado1inteligente2,estado2inteligente2;
	private Apagado apagado1,apagadointeligente2;
	private AhorroDeEnergia estadoahorro;
	private AdapterApagar adapterApagar;
	private AdapterEncender adapterEncender;
	private AdapterAhorroDeEnergia adapterAhorroDeEnergia;
	private LocalDateTime inicioestado,inicioestado2,inicioapagado;
	private LocalDateTime finestado,finestado2,finapagado;
	private Categoria categoria1;
	private Cliente cliente1;
	private Modulo modulo;
	private DispositivoEstandar estandar1;
	
	
	
	@Before
	public void init(){
		inteligente1=new DispositivoInteligente("tv", 28);
		actuador1=new Actuador();
		sensor1=new Sensor();
		regla1=new Regla("mayor",25,"apagar",actuador1);
		sensor1.agregarObservador(regla1);
		adapterApagar=new AdapterApagar("apagar",inteligente1);
		adapterAhorroDeEnergia= new AdapterAhorroDeEnergia("ahorroDeEnergia",inteligente1);
		adapterEncender=new AdapterEncender("encender",inteligente1);
		//comandoApagar=new ComandoApagar("apagar",inteligente1);
		//comandoprender=new ComandoEncender("encender",inteligente1);
		inicioestado=LocalDateTime.of(2018, 6, 5, 10, 00);
		finestado=LocalDateTime.of(2018, 6, 5, 12, 00);
		estado1=new Encendido("encendido",inicioestado,finestado,56);
		inicioestado2=LocalDateTime.of(2018, 6, 5, 14, 00);
		finestado2=LocalDateTime.of(2018, 6, 5, 15, 00);
		estado2=new Encendido("encendido",inicioestado2,finestado2,28);
		inicioapagado=LocalDateTime.of(2018, 6, 5, 12, 00);
		finapagado=LocalDateTime.of(2018, 6, 5, 14, 00);
		apagado1=new Apagado("apagado", inicioapagado, finapagado);
		inteligente1.agregarEstado(estado1);
		inteligente1.agregarEstado(estado2);
		inteligente1.setSensor(sensor1);
		actuador1.addcomando(comandoApagar);
		actuador1.addcomando(comandoprender);
		//comandoAhorroDeEnergia=new ComandoAhorroDeEnergia(adapterAhorroDeEnergia,"ahorroDeEnergia",inteligente1);
		actuador1.addcomando(comandoAhorroDeEnergia);
		categoria1=new Categoria("R1",18,1);

		cliente1=new Cliente("Marta","Farias","DNI",12123,342324,5.94,3.56);

		estandar1=new DispositivoEstandar("radio",30,1);

		//modulo=new Modulo("radio",30);
		inteligente2=new DispositivoInteligente("AireAcondicionado", 10);
		estado1inteligente2=new Encendido("encendido",inicioestado,finestado,20);
		estado2inteligente2=new Encendido("encendido",inicioestado2,finestado2,10);
		inteligente2.agregarEstado(estado1inteligente2);
		inteligente2.agregarEstado(estado2inteligente2);

		modulo=new Modulo(estandar1);

		
			
	}	
	
	@Test
	public void obtenerconsumoestadosentreelrango() {
		double consumo,horastotal;
		consumo=inteligente1.consumidoUltimasNhoras(38);
		Assert.assertTrue(inteligente1.getEstados().size()==2);
		LocalDateTime hoy=LocalDateTime.of(2018, 6, 6, 9,0 );
		LocalDateTime ayer=LocalDateTime.of(2018, 6, 5, 9,0 );
		double consumido=inteligente1.consumidoComprendidoEntre(ayer,hoy);
		Assert.assertTrue(consumido==84.0);
		Assert.assertTrue(inteligente1.listaDeEstadosSegun(ayer, hoy, "encendido").size()==2);
		Assert.assertTrue(inteligente1.cumpleCondicion(estado1, ayer, hoy));
		Assert.assertTrue(inteligente1.cumpleCondicion(estado2, ayer, hoy));
		horastotal=inteligente1.totalDeHoras(inteligente1.listaDeEstadosSegun(ayer, hoy, "encendido"), ayer, hoy);

		System.out.println("hora total "+horastotal);

		Assert.assertTrue(horastotal==3);
		Assert.assertTrue(consumo==84);
		}
	
	@Test
	public void obtenerconsumoestadofuerarango() {
		double consumo;
		LocalDateTime hoy=LocalDateTime.of(2018, 6, 6, 9,0 );
		LocalDateTime ayer=LocalDateTime.of(2018, 6, 5, 11,30 );
		consumo=inteligente1.consumidoComprendidoEntre(ayer,hoy);
		//Assert.assertTrue(consumo==56.0);
		Assert.assertTrue(consumo==42.0);
		
		
	}
	
	@Test
	public void prenderDispositivo() {
		adapterEncender.ejecutar();
		//comandoprender.ejecutar();
		Assert.assertTrue(inteligente1.getEstado().getNombre()== "encendido");
	}
	
	@Test
	public void apagarDispositivo() {
		adapterEncender.ejecutar();
		adapterApagar.ejecutar();
		//comandoprender.ejecutar();
		//comandoApagar.ejecutar();
		Assert.assertTrue(inteligente1.getEstado().getNombre()== "apagado");
	}
	
	@Test
	public void ahorroDeEnergiaDispositivo() {
		adapterEncender.ejecutar();
		adapterAhorroDeEnergia.ejecutar();
		//comandoprender.ejecutar();
		//comandoAhorroDeEnergia.ejecutar();
		Assert.assertTrue(inteligente1.getEstado().getNombre()== "modo ahorro");
	}
	@Test
	public void tomarmedicion() {
		adapterEncender.ejecutar();
		//comandoprender.ejecutar();
		Assert.assertTrue(inteligente1.getEstado().getNombre()== "encendido");
		sensor1.setValor(30);
		Assert.assertTrue(inteligente1.getEstado().getNombre()== "apagado");
	}
	@Test
	public void tomarmedicionnocumpleRegla() {
		adapterEncender.ejecutar();
		//comandoprender.ejecutar();
		Assert.assertTrue(inteligente1.getEstado().getNombre()== "encendido");
		sensor1.setValor(22);
		Assert.assertFalse(inteligente1.getEstado().getNombre()== "apagado");
	}
	
	@Test
	public void cambiarDeEstandarAInteligente() {
		cliente1.agregarDispositivosEstandares(estandar1);
		cliente1.agregarUnModuloA(estandar1,modulo);
		Assert.assertTrue(cliente1.cantidadDeDispositivosEstandares()==0);
		Assert.assertTrue(cliente1.cantidadDedispositivosInteligentes()==1);
		Assert.assertTrue(cliente1.getPuntos()==10);

		}	
	@Test
	public void cantidadpuntos() {
		cliente1.agregarDispositivosEstandares(estandar1);
		Assert.assertTrue(cliente1.cantidadDeDispositivosEstandares()==1);
		cliente1.agregarUnModuloA(estandar1,modulo);
		cliente1.agregarDispositivosInteligentes(inteligente1);
		Assert.assertTrue(cliente1.cantidadDeDispositivosEstandares()==0);
		Assert.assertTrue(cliente1.cantidadDedispositivosInteligentes()==2);
		Assert.assertTrue(cliente1.getPuntos()==25);
		
		
	}
	@Test
	public void consumoenergiaclienteEnPeriodo() {
		cliente1.agregarDispositivosEstandares(estandar1);
		cliente1.agregarDispositivosInteligentes(inteligente1);
		cliente1.agregarDispositivosInteligentes(inteligente2);
		cliente1.setCategoria(categoria1);
		LocalDateTime iniciojunio=LocalDateTime.of(2018, 6, 1, 0, 0);
		LocalDateTime finjunio=LocalDateTime.of(2018, 6, 30, 23, 0);
		
		float gasto=cliente1.facturacionEntre(iniciojunio, finjunio);
		Assert.assertTrue(gasto==132);

	
		}		

	
	
	@Test
	public void cantidadDeDispositivosEncendidos() {
		DispositivoInteligente dispo2 = new DispositivoInteligente("smart",50);
		cliente1.agregarDispositivosInteligentes(inteligente1);
		cliente1.agregarDispositivosInteligentes(dispo2);
		cliente1.agregarDispositivosEstandares(estandar1);
		inteligente1.encender();
		dispo2.encender();	
		Assert.assertTrue(cliente1.cantidadDispositivosEncendidos()==2);
	}

	@Test
	public void verificarCambio(){
		Assert.assertEquals(modulo.getNombre(),"radio");
	}
	

}
