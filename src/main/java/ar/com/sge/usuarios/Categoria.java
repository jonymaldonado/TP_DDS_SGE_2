package ar.com.sge.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Categorias")
public class Categoria {
	
	@Id
	@GeneratedValue
	@Column(nullable=false,unique=true)
	private int idCategoria;
	@Column(length = 45)
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