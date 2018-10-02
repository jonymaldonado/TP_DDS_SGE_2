package ar.com.sge.usuarios;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String nombre;
    @Column(name = "valor_fijo")
    private float valorFijo;
    @Column(name = "valor_variable")
    private float valorVariable;
    @Column(name = "consumo_minimo")
    private Integer consumoMinimo;
    @Column(name = "consumo_maximo")
    private Integer consumoMaximo;

    public Categoria() {
    }
    
	public Categoria(String _nombre,float _valorF,float _valorV) {
		this.nombre = _nombre;
		this.valorFijo = _valorF;
		this.valorVariable = _valorV;
	}
	
	public Categoria(String _nombre,float _valorF,float _valorV,int min,int max) {
		this.nombre = _nombre;
		this.valorFijo = _valorF;
		this.valorVariable = _valorV;
		this.consumoMinimo = min;
		this.consumoMaximo = max;
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


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public Integer getConsumoMinimo() {
        return consumoMinimo;
    }

    public void setConsumoMinimo(Integer consumoMinimo) {
        this.consumoMinimo = consumoMinimo;
    }

    public Integer getConsumoMaximo() {
        return consumoMaximo;
    }

    public void setConsumoMaximo(Integer consumoMaximo) {
        this.consumoMaximo = consumoMaximo;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}