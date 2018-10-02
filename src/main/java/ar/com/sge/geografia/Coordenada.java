package ar.com.sge.geografia;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coordenadas")
public class Coordenada {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "latitud")
    private double latitud;
    @Column(name = "longitud")
    private double longitud;
	
	
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
		distancia= (double) Math.pow(distLat+distLong, -2);

		return distancia;
	}
	
	

}// fin Coordenada
