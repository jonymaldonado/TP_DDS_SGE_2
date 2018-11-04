package ar.com.sge.usuarios;

public class Session {

	private int id;
	private String nombreUsuario;
	private String tipoUsuario;
	
	public Session(int id_usuario,String nombreUsuario, String tipoUsuario) {
		this.id = id_usuario;
		this.nombreUsuario = nombreUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public int getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	
}
