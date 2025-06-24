package ar.edu.unq.po2.Organizacion;

import java.util.List; 
import java.util.stream.Collectors;
import ar.edu.unq.po2.Muestra.*;
import ar.edu.unq.po2.Sistema.*;

public class CalculadorGeodesico {
	private Sistema sistema;
	/*usamos el calculo de Haversine para resolver distancias, si quisieramos o necesitaramos mas 
	  algoritmos de calculo como Manhattan o euclidiana tendriamos que crear una interfaz
	*/
    public CalculadorGeodesico(Sistema sistema) {
    	this.sistema = sistema;
    }
    
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
				.filter(ubicacion -> this.calcularDistancia(ubicacionBase, ubicacion)<= distancia)
	         //   .filter(ubicacion -> ubicacionBase.distanciaEntre(ubicacion) <= distancia)
	            .collect(Collectors.toList()); //convertimos el stream a lista
	}
	
	public List<Muestra> muestrasAMenosDe(Muestra muestra, int distancia) {
        Ubicacion origen = muestra.getUbicacion();
        List<Muestra> muestrasTotales = sistema.getMuestras();
        return muestrasTotales.stream()
            .filter(m -> !m.equals(muestra))
            .filter(m -> this.calcularDistancia(origen, m.getUbicacion()) <= distancia)
           // .filter(m -> origen.distanciaEntre(m.getUbicacion()) <= distancia)
            .toList();
    }


}
