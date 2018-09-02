package ar.com.sge.geografia;

public class Coordenada {
	private double latitud;
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
