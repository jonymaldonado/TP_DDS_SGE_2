package ar.com.sge.dispositivos;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Usuario;



@Entity
@Inheritance
@DiscriminatorColumn(name="tipo_Dispositivo")
//@ForceDiscriminator
@Table(name ="dispositivo")
public abstract class IDispositivo implements Cloneable {

	@Id
	@GeneratedValue
	private int Id_Dispositivo;
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name = "id_Usuario")
	//private Cliente cliente;
	//private Usuario cliente;
	/*@ManyToOne(fetch=FetchType.LAZY)
	//@ManyToOne()
	@JoinColumn(name = "id_Usuario")
	private Cliente cliente;*/
	
	public  IDispositivo clone() throws CloneNotSupportedException{
		return (IDispositivo) super.clone();
	}

	public int getId_Dispositivo() {
		return Id_Dispositivo;
	}

	public void setId_Dispositivo(int id_Dispositivo) {
		Id_Dispositivo = id_Dispositivo;
	}/*
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}*/
	
	

}
