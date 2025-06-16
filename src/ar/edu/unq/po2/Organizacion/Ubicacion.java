package ar.edu.unq.po2.Organizacion;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.*;


public class Ubicacion {
  private double latitud;
  private double longitud;
  private CalculadorGeodesico calculador = new CalculadorGeodesico();
  
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

public double distanciaEntre(Ubicacion otraUbicacion) {
	return calculador.calcularDistancia(this, otraUbicacion);
}

public List<Ubicacion> ubicacionesAMenosDe(List<Ubicacion> ubicaciones, int distancia) {
	return calculador.filtrarUbicaciones(this, ubicaciones, distancia);
}
	
public List<Muestra> muestrasAMenosDe(Muestra muestra, int distancia ) {
	// ESTO VA EN SISTEMA PORQUE NECESITAMOS LA LISTA DE MUESTRAS, NO VA EN UBICACION, lo cambio luego 
}


}

