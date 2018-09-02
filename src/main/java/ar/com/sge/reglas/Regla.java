package ar.com.sge.reglas;

public class Regla {
	private String operador;
	private float valorcomparacion;
	private Actuador actuador;
	private String accion;
	
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
