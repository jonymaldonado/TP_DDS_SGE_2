package ar.com.sge.dispositivos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ar.com.sge.usuarios.Cliente;

@Entity
@DiscriminatorValue("estandar")
public class DispositivoEstandar extends IDispositivo{
	
	private String nombre;
	private double kwPorHora;
	private int horasDeUso;
	@ManyToOne(fetch=FetchType.LAZY)
	//@ManyToOne()
	@JoinColumn(name = "id_Usuario1")
	private Cliente cliente;
	
	public DispositivoEstandar(String nombre, double kw, int hs) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		this.horasDeUso = hs;
	}
	public DispositivoEstandar(String nombre, double kw) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		
	}
	
	public DispositivoEstandar() {
		
	}
	
	@Override
	public IDispositivo clone() throws CloneNotSupportedException{
		DispositivoEstandar inteligente=null;
		try {
			inteligente=(DispositivoEstandar) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return inteligente;
	}
	
	public String getNombre() {
		return nombre;
	}
		
	public double getKwPorHora() {
		return kwPorHora;
	}
	
	public void setHorasDeUso(int hr) {
		this.horasDeUso = hr;
	}
	
	public int getHorasDeUso() {
		return horasDeUso;
	}
	
	//Calcula el consumo del Dispositivo Estandar
	public double consumoEnKw() {
		return horasDeUso * this.getKwPorHora();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setKwPorHora(double kwPorHora) {
		this.kwPorHora = kwPorHora;
	}
	
	
	
}