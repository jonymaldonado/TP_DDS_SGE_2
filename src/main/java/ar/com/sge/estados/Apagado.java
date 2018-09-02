package ar.com.sge.estados;

import java.time.LocalDateTime;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class Apagado extends Estado{

	public Apagado(DispositivoInteligente d) {
		super(d);
		this.nombre = "apagado";
		fechaInicio =LocalDateTime.now();
	}
	public Apagado (String nombre,LocalDateTime inicio,LocalDateTime fin) {
		super(nombre,inicio,fin,0);
	}
	
	public void encender() {
		fechaFin = LocalDateTime.now();
		consumo = 0;
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Encendido(dispositivo));			
	}

	public void apagar() {
		
	}

	public void ahorroDeEnergia() {

	}
}
