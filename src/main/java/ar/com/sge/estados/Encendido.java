package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class Encendido extends Estado {

	
	public Encendido() {
		this.nombre = "encendido";
		this.fechaInicio = LocalDateTime.now();
	}
	
	public Encendido (String nombre,LocalDateTime inicio,LocalDateTime fin,double consumo) {
		this.nombre = nombre;
		this.fechaInicio = inicio;
		this.fechaFin= fin;
		this.consumo = consumo;
	}
	
	public void encender(DispositivoInteligente dispositivo) {
	}

	public void apagar(DispositivoInteligente dispositivo) {		
		fechaFin = LocalDateTime.now();
		double tiempo= ChronoUnit.HOURS.between(fechaInicio,fechaFin);
		consumo = tiempo * dispositivo.getKwPorHora();
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Apagado());
		dispositivo.setEstadoDipositivo(false);
	}

	
	public void ahorroDeEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroDeEnergia());
	}
	
}
