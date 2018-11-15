package ar.com.sge.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.geografia.Coordenada;

@Entity
@Inheritance
//@DiscriminatorColumn(name="tipo_usuario")
//@ForceDiscriminator
@Table(name ="Usuario")
public abstract class Usuario {

	@Id
	@GeneratedValue
	//@Column(nullable=false,unique=true)
	private int id_Usuario;
	private String nombre_usuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "id_coordenada")
	private Coordenada coordenada;
	private LocalDate alta;
	/*@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="cliente")
	private List<DispositivoInteligente> lstDispositivosInteligentes ;
	//private List<IDispositivo> lstDispositivosInteligentes ;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="cliente")
	private List<DispositivoEstandar> lstDispositivosEstandares ;
	//private List<IDispositivo> lstDispositivosEstandares ;*/
	protected String tipo_usuario;
	
	public Usuario(String _nombre,String _apellido,double latitud,double longitud) {
		this.nombre = _nombre;
		this.apellido = _apellido;
		this.coordenada=new Coordenada(latitud, longitud);
		//this.lstDispositivosInteligentes  = new ArrayList<>();
		//this.lstDispositivosEstandares  = new ArrayList<>();

		this.alta = LocalDate.now();
	}
	
	public Usuario() {
		
	}
	public Usuario(String _nombre,String _apellido) {
		this.nombre = _nombre;
		this.apellido = _apellido;
		
	}
	
	public Usuario(String nombre_usuario,String contrasenia,String _nombre,String _apellido) {
		this.nombre_usuario = nombre_usuario;
		this.contrasenia = contrasenia;
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
		return coordenada;
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
	public int getId_Usuario() {
		return id_Usuario;
	}
	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setAlta(LocalDate alta) {
		this.alta = alta;
	}
	
	/*public void setTipoUsuario(String tipo) {
		this.tipo_usuario = tipo;
	}
	*/
	public String getTipoUsuario() {
		return this.tipo_usuario;
	}
	
	
	
	
}