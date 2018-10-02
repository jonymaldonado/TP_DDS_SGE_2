package ar.com.sge.reglas;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entity.Actuadores;

@Entity
@Table(name = "reglas")
public class Regla {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "operador")
    private String operador;
    @Column(name = "valor")
    private float valorcomparacion;
    @Column(name = "accion")
    private String accion;
    @JoinColumn(name = "actuador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Actuador actuador;
	
	
	public Regla(String operador,float valor,String accion,Actuador actuador) {
		this.operador=operador;
		this.valorcomparacion=valor;
		this.accion=accion;
		this.actuador=actuador;
		
	}

	public void verificarRegla(double valor) {
		if (evaluar(valor)) {
			actuador.ejecutarAccion(accion);
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
	public void setValorcomparacion(float valorcomparacion) {
		this.valorcomparacion = valorcomparacion;
	}


	public Actuador getActuador() {
		return actuador;
	}

	public void setActuador(Actuador actuador) {
		this.actuador = actuador;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

}
