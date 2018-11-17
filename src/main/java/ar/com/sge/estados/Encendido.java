package ar.com.sge.estados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("encendido")
//@Table(name ="Encendido")
public class Encendido extends Estado {
	

	/*@Id
	@GeneratedValue
	private int IdPeriodo;*/

	
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
	public int getIdPeriodo() {
		return IdPeriodo;
	}
	
}