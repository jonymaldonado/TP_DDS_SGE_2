package ar.com.sge.dispositivos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dispositivos")
public class DispositivoEstandar implements IDispositivo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nombre_dispositivo")
	private String nombre;
	@Column(name = "inteligente")
	private String inteligente;
	@Column(name = "bajo_consumo")
	private String bajoConsumo;
	@Column(name = "consumo_kwh")
	private double kwPorHora;
	private int horasDeUso;
	
	public DispositivoEstandar() {
		
	}
	
	public DispositivoEstandar(String nombre, double kw, int hs) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		this.horasDeUso = hs;
	}
	
	public DispositivoEstandar(String nombre, double kw) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		
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

	public double consumoEnKw() {
		return horasDeUso * this.getKwPorHora();
	}
	
}