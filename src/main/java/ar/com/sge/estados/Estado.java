package ar.com.sge.estados;

import java.time.LocalDateTime;

import ar.com.sge.dispositivos.DispositivoInteligente;

public abstract class Estado {
	
	protected String nombre;
	protected DispositivoInteligente dispositivo;
	protected LocalDateTime fechaInicio;
	protected LocalDateTime fechaFin;
	protected double consumo;

	public Estado(DispositivoInteligente d) {
		this.dispositivo = d;
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
	public abstract void encender();

	public abstract void apagar();

	public abstract void ahorroDeEnergia();
	
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
