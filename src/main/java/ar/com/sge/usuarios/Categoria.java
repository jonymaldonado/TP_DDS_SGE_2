package ar.com.sge.usuarios;

public class Categoria {
	
	private String nombre;
	private float valorFijo;
	private float valorVariable;

	public Categoria(String _nombre,float _valorF,float _valorV) {
		this.nombre = _nombre;
		this.valorFijo = _valorF;
		this.valorVariable = _valorV;
	}
	
	public String getNombre() {
		return nombre;
	}
	public float getValorFijo() {
		return valorFijo;
	}

	public void setValorFijo(float valorFijo) {
		this.valorFijo = valorFijo;
	}

	public float getValorVariable() {
		return valorVariable;
	}

	public void setValorVariable(float valorVariable) {
		this.valorVariable = valorVariable;
	}
	
	
}