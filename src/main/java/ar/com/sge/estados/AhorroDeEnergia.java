package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class AhorroDeEnergia extends Estado {
	
	public AhorroDeEnergia() {
		this.nombre = "modo ahorro";
		this.fechaInicio = LocalDateTime.now();
	}
	public void encender(DispositivoInteligente dispositivo) {
		fechaFin = LocalDateTime.now();
		double tiempo = ChronoUnit.HOURS.between(fechaInicio,fechaFin);
		consumo = tiempo * dispositivo.getKwPorHora();
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Encendido());
	}

	public void apagar(DispositivoInteligente dispositivo) {

	}

	public void ahorroDeEnergia(DispositivoInteligente dispositivo) {

	}
}
