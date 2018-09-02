package ar.com.sge.usuarios;

import java.time.LocalDate;

import ar.com.sge.geografia.Coordenada;

public abstract class Usuario {
	private String nombre_usuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private Coordenada domicilio;
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