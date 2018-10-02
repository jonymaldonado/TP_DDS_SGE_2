package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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


	public void apagar(DispositivoInteligente inteligente) {

	}

	public void ahorroDeEnergia(DispositivoInteligente inteligente) {



	}
}