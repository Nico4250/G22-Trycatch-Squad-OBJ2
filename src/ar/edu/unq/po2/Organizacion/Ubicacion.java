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
public double distanciaEntre(Ubicacion ubicacion1, Ubicacion ubicacion2) {
	
}
public List<Ubicacion> ubicacionesAMenosDe(List<Ubicacion> ubicaciones, int distancia) {
	
}
public List<Muestra> muestrasAMenosDe(Muestra muestra, int distancia ) {
	// ESTO VA EN SISTEMA PORQUE NECESITAMOS LA LISTA DE MUESTRAS, NO VA EN UBICACION, lo cambio luego 
}


}

