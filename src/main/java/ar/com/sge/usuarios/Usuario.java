package ar.com.sge.usuarios;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ar.com.sge.geografia.Coordenada;
import ar.com.sge.util.Persistible;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Usuario extends Persistible {
	@Column(name = "nombreUsuario", length=45)
	private String nombre_usuario;
	@Column(length=45)
	private String contrasenia;
	@Column(length=45)
	private String nombre;
	@Column(length=45)
	private String apellido;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "georef_id", referencedColumnName = "id_coordenada")
	private Coordenada domicilio;
	@Column(length=45)
	private LocalDate alta;
	
	public Usuario(String _nombre,String _apellido,double latitud,double longitud) {
		this.nombre = _nombre;
		this.apellido = _apellido;
		this.domicilio=new Coordenada(latitud, longitud);

		//this.alta = LocalDate.now();
	}
	public Usuario(String _nombre,String _apellido) {
		this.nombre = _nombre;
		this.apellido = _apellido;
		
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Coordenada getDomicilio() {
		return domicilio;
	}
	public LocalDate getAlta() {
        return alta;
	}
	
	public String getNombreUsuario() {
		return nombre_usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	
	
}