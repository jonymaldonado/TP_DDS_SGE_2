package ar.com.sge.dispositivos;

public class DispositivoEstandar implements IDispositivo{
	
	private String nombre;
	protected double kwPorHora;
	private int horasDeUso;
	
	public DispositivoEstandar(String nombre, double kw, int hs) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		this.horasDeUso = hs;
	}
	public DispositivoEstandar(String nombre, double kw) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		
	}
	
	@Override
	public IDispositivo clone() throws CloneNotSupportedException{
		DispositivoEstandar inteligente=null;
		try {
			inteligente=(DispositivoEstandar) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return inteligente;
	}
	
	public String getNombre() {
		return nombre;
	}
		
	public double getKwPorHora() {
		return kwPorHora;
	}
	
	public void setHorasDeUso(int hr) {
		this.horasDeUso = hr;
	}
	
	public int getHorasDeUso() {
		return horasDeUso;
	}

	public double consumoEnKw() {
		return horasDeUso * this.getKwPorHora();
	}
	
}