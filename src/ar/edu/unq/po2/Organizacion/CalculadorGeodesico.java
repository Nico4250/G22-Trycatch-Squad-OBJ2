package ar.edu.unq.po2.Organizacion;

import java.util.List;
import java.util.stream.Collectors;

public class CalculadorGeodesico {
	/*usamos el calculo de Haversine para resolver distancias, si quisieramos o necesitaramos mas 
	  algoritmos de calculo como Manhattan o euclidiana tendriamos que crear una interfaz
	*/
	public double calcularDistancia(Ubicacion ubicacionBase, Ubicacion otraUbicacion) {
		final double radioTierra = 6371; // Radio promedio de la Tierra en km
	    double deltaLat = Math.toRadians(otraUbicacion.getLatitud() - ubicacionBase.getLatitud());
	    double deltaLon = Math.toRadians(otraUbicacion.getLongitud() - ubicacionBase.getLongitud());

	    double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
	            Math.cos(Math.toRadians(ubicacionBase.getLatitud())) * Math.cos(Math.toRadians(otraUbicacion.getLatitud())) *
	            Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return radioTierra * c;	
	}
	
	public List<Ubicacion> filtrarUbicaciones (Ubicacion ubicacionBase,List<Ubicacion> ubicaciones, int distancia) {
		return ubicaciones.stream()
	            .filter(ubicacion -> ubicacionBase.distanciaEntre(ubicacion) <= distancia)
	            .collect(Collectors.toList()); //convertimos el stream a lista
	}


}
