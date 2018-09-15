package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;


@Entity
@Table(name ="AhorroEnergia")
public class AhorroDeEnergia extends Estado {

	@Id
	@GeneratedValue
	private int Id;
	
	public AhorroDeEnergia(DispositivoInteligente d) {
		super(d);
		this.nombre = "modo ahorro";
		fechaInicio = LocalDateTime.now();
	}
	public void encender(DispositivoInteligente dispositivo) {
		fechaFin = LocalDateTime.now();
		float tiempo = ChronoUnit.HOURS.between(fechaInicio,fechaFin);
		consumo = tiempo * dispositivo.getKwPorHora();
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Encendido(dispositivo));
	}

	public void apagar(DispositivoInteligente inteligente) {

	}

	public void ahorroDeEnergia(DispositivoInteligente inteligente) {

	}
}
