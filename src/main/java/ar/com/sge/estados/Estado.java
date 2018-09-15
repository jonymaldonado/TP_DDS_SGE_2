package ar.com.sge.estados;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;


@Entity
@Table(name ="Estado")
public abstract class Estado {
	
	protected String nombre;
	//protected DispositivoInteligente dispositivo;
	protected LocalDateTime fechaInicio;
	protected LocalDateTime fechaFin;
	protected double consumo;

	public Estado(DispositivoInteligente inteligente) {
		//this.dispositivo = d;
	}
	
	
	
	public Estado(String nombre,  LocalDateTime fechaInicio, LocalDateTime fechaFin,
			float consumo) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.consumo = consumo;
	}



	public String getNombre() {
		return nombre;
	}	
	public abstract void encender(DispositivoInteligente inteligente);

	public abstract void apagar(DispositivoInteligente inteligente);

	public abstract void ahorroDeEnergia(DispositivoInteligente inteligente);
	
	public double getConsumo() {
		return consumo;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
