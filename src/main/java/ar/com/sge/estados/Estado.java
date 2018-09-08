package ar.com.sge.estados;

import java.time.LocalDateTime;

import ar.com.sge.dispositivos.DispositivoInteligente;

public abstract class Estado {
	
	protected String nombre;
	protected LocalDateTime fechaInicio;
	protected LocalDateTime fechaFin;
	protected double consumo;
	
	public Estado() {
		
	}
	
	public abstract void encender(DispositivoInteligente dispositivo) ;

	public abstract void apagar(DispositivoInteligente dispositivo);

	public abstract void ahorroDeEnergia(DispositivoInteligente dispositivo);
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDateTime getFechaInicio() {
		return this.fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return this.fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	public double getConsumo() {
		return this.consumo;
	}
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}
}
