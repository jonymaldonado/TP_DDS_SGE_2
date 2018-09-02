package ar.com.sge.dispositivos;


public class Modulo extends DispositivoInteligente {

	private DispositivoEstandar dispositivoEstandar;
		
	
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
	
}
