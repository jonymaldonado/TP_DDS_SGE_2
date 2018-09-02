package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class Encendido extends Estado {

	
	public Encendido(DispositivoInteligente d) {
		super(d);
		this.nombre = "encendido";
		fechaInicio = LocalDateTime.now();	
	}
	public Encendido (String nombre,LocalDateTime inicio,LocalDateTime fin,float consumo) {
		super(nombre,inicio,fin,consumo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void encender() {
		
	}

	public void apagar() {		
		fechaFin = LocalDateTime.now();
		float tiempo= ChronoUnit.HOURS.between(fechaInicio,fechaFin);
		consumo = tiempo * dispositivo.getKwPorHora();
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Apagado(dispositivo));
	}

	public void ahorroDeEnergia() {
		dispositivo.setEstado(new AhorroDeEnergia(dispositivo));
	}
}
