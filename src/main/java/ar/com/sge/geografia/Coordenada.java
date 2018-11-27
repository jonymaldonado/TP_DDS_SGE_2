package ar.com.sge.geografia;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="coordenada")
public class Coordenada {
	@Id
	@GeneratedValue
	private int id_coordenada;
	private double latitud;
	private double longitud;
	
	public Coordenada() {
		
	}
	
	public Coordenada(double unLatitud,double unLongitud) {

		this.latitud=unLatitud;
		this.longitud=unLongitud;
	}// constructor
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
	
	
		return longitud;
	}
	
	//distanciaEntre2Punto = raiz[(x2-x1)^2+(x2-x1)^2]
	//distanciaEntre2Punto = raiz[distLat+distLong]
	//siendo p1=(x1,y1) y p2=(x2,y2)
	
	public double distanciaAlPunto(Coordenada unPunto) {
		double distancia,distLat,distLong;
		distLat=(double) Math.pow((unPunto.getLatitud() - this.getLatitud()),2);
		distLong=(double) Math.pow((unPunto.getLongitud() - this.getLongitud()),2);
		distancia= (double) Math.pow(distLat+distLong, 0.5);

		return distancia;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
	
	

}// fin Coordenada
