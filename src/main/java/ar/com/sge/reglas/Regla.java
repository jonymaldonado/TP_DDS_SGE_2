package ar.com.sge.reglas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@Table(name ="regla")
public class Regla {
	@Id
	@GeneratedValue
	private int id_regla;
	private String operador;
	private Double valorcomparacion;
	//private Actuador actuador;
	private String accion;
	
	public Regla() {
		
	}
	
	public Regla(String operador,Double valor,String accion,Actuador actuador) {
		this.operador=operador;
		this.valorcomparacion=valor;
		this.accion=accion;
	//	this.actuador=actuador;
		
	}
	public Regla(String operador,Double valor,String accion) {
		this.operador=operador;
		this.valorcomparacion=valor;
		this.accion=accion;
	//	this.actuador=actuador;
		
	}

	/*public void verificarRegla(double valor) {
		if (evaluar(valor)) {
			actuador.ejecutarAccion(accion);
		}
	}*/
	public void verificarRegla(double valor,DispositivoInteligente dispositivo) {
		if (evaluar(valor)) {
			dispositivo.getActuador().ejecutarAccion(accion);
		}
	}
	
	public boolean evaluar(double valor) {

		boolean a;
		
		if (operador.equalsIgnoreCase("mayor")) {
			a = valor>valorcomparacion;
		}
		else if(operador.equalsIgnoreCase("menor")) {
			a = valor<valorcomparacion;
		}
		else {
			a = valor==valorcomparacion;
		}		
		return a;		
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public void setValorcomparacion(Double valorcomparacion) {
		this.valorcomparacion = valorcomparacion;
	}


	/*public Actuador getActuador() {
		return actuador;
	}

	public void setActuador(Actuador actuador) {
		this.actuador = actuador;
	}*/

	public String getOperador() {
		return this.operador;
	}
	public int getId_regla() {
		return id_regla;
	}

	public void setId_regla(int id_regla) {
		this.id_regla = id_regla;
	}

	public Double getValorcomparacion() {
		return valorcomparacion;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

}
