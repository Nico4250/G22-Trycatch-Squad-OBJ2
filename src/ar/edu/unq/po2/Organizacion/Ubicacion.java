package ar.edu.unq.po2.Organizacion;
import java.util.List;
import ar.edu.unq.po2.Muestra.*;


public class Ubicacion {
  private double latitud;
  private double longitud;
  
  public Ubicacion(double latitud, double longitud) {
	  this.latitud = latitud;
	  this.longitud = longitud;
  }
  
public double getLatitud() {
	return latitud;
}
public void setLatitud(double latitud) {
	this.latitud = latitud;
}
public double getLongitud() {
	return longitud;
}
public void setLongitud(double longitud) {
	this.longitud = longitud;
}
//creamos una clase aparte que se ocupe de estos calculos para dejar sin responsabilidad a ubicacion
public double distanciaEntre(Ubicacion otraUbicacion) {
	final double radioTierra = 6371; // Radio promedio de la Tierra en km
    double deltaLat = Math.toRadians(otraUbicacion.latitud - this.latitud);
    double deltaLon = Math.toRadians(otraUbicacion.longitud - this.longitud);

    double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
            Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(otraUbicacion.latitud)) *
            Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return radioTierra * c;	
}

public List<Ubicacion> ubicacionesAMenosDe(List<Ubicacion> ubicaciones, int distancia) {
	
}
public List<Muestra> muestrasAMenosDe(Muestra muestra, int distancia ) {
	// ESTO VA EN SISTEMA PORQUE NECESITAMOS LA LISTA DE MUESTRAS, NO VA EN UBICACION, lo cambio luego 
}


}

