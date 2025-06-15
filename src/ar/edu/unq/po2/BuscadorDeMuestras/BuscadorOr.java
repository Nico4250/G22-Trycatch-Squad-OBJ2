package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorOr extends BuscadorLogico {
	public BuscadorOr(IBuscadorMuestras filtroBusqueda1, IBuscadorMuestras filtroBusqueda2) {
		super(filtroBusqueda1, filtroBusqueda2);
		
	}

	public List<Muestra> filtrar(List<Muestra> muestrasAFiltrar) {
		
		List<Muestra> muestrasResultantes = new ArrayList <Muestra>();
		
	    muestrasResultantes.addAll(this.getFiltroBusqueda1().filtrar(muestrasAFiltrar));
	    muestrasResultantes.addAll(this.getFiltroBusqueda2().filtrar(muestrasAFiltrar));
		
		return muestrasResultantes.stream().distinct().toList(); // me quedo con todo lo que está en ambas listas y le saco los duplicados.
		
	}

}
