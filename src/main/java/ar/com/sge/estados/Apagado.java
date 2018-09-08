package ar.com.sge.estados;

import java.time.LocalDateTime;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class Apagado extends Estado{
	
	public Apagado() {
		this.nombre = "apagado";
		this.fechaInicio =LocalDateTime.now();
	}
	public Apagado (String nombre,LocalDateTime inicio,LocalDateTime fin) {
		this.nombre = nombre;
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.consumo = 0;
	}
	
	public void encender(DispositivoInteligente dispositivo) {
		fechaFin = LocalDateTime.now();
		consumo = 0;
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Encendido());	
		dispositivo.setEstadoDipositivo(true);
	}

	public void apagar(DispositivoInteligente dispositivo) {
		
	}

	public void ahorroDeEnergia(DispositivoInteligente dispositivo) {

	}
	
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> bb6cc89b10d0cf2c1d77370ff58e58d1a8c812d8
