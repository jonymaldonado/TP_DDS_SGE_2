package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@Table(name ="Encendido")
public class Encendido extends Estado {
	

	@Id
	@GeneratedValue
	private int IdPeriodo;

	
	public Encendido(DispositivoInteligente d) {
		super(d);
		this.nombre = "encendido";
		fechaInicio = LocalDateTime.now();	
	}
	public Encendido (String nombre,LocalDateTime inicio,LocalDateTime fin,float consumo) {
		super(nombre,inicio,fin,consumo);
	}
		
	
	public void encender(DispositivoInteligente inteligente) {
		
	}

	public void apagar(DispositivoInteligente dispositivo) {		
		fechaFin = LocalDateTime.now();
		float tiempo= ChronoUnit.HOURS.between(fechaInicio,fechaFin);
		consumo = tiempo * dispositivo.getKwPorHora();
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Apagado(dispositivo));
	}

	public void ahorroDeEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroDeEnergia(dispositivo));
	}
}
