package ar.com.sge.dispositivos;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("modulo")
public class Modulo extends DispositivoInteligente {

	/*@Id
	@GeneratedValue
	private int Id;*/
	
	//@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="condicion")
	
	
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private DispositivoEstandar dispositivoEstandar;
		
	
	public Modulo() {
		//super(nombre, kw);
	}

	public Modulo(DispositivoEstandar dispositivo) {
		super(dispositivo.getNombre(),dispositivo.getKwPorHora());
		this.dispositivoEstandar = dispositivo;
	}
	
	public DispositivoEstandar getDispositivoEstandar() {
		return dispositivoEstandar;
	}

	public void setDispositivoEstandar(DispositivoEstandar dispositivoEstandar) {
		this.dispositivoEstandar = dispositivoEstandar;
	}

	public int getId() {
		return Id;
	}


	
	
}
