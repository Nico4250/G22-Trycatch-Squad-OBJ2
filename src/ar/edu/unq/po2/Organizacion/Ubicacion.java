package ar.edu.unq.po2.Organizacion;
import java.util.List;

import ar.edu.unq.po2.Muestra.*;
import ar.edu.unq.po2.Sistema.*;

public class Ubicacion {
  private double latitud;
  private double longitud;
  
  private CalculadorGeodesico calculador;
  
  public Ubicacion(double latitud, double longitud, CalculadorGeodesico calculador,Sistema sistema) {
	  this.latitud = latitud;
	  this.longitud = longitud;
	  this.calculador = calculador;
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
	return calculador.muestrasAMenosDe(muestra,distancia);
}


}

