package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorAnd extends BuscadorLogico {

	public BuscadorAnd(IBuscadorMuestras filtroBusqueda1, IBuscadorMuestras filtroBusqueda2) {
		super(filtroBusqueda1, filtroBusqueda2);
	}

	
	public List<Muestra> filtrar(List<Muestra> muestrasAFiltrar) {
		List<Muestra> muestrasAFiltrarPorPrimerBuscador  = new ArrayList<Muestra>();
		List<Muestra> muestrasAFiltrarPorSegundoBuscador = new ArrayList<Muestra>();
		
		muestrasAFiltrarPorPrimerBuscador.addAll(this.getFiltroBusqueda1().filtrar(muestrasAFiltrar));
		muestrasAFiltrarPorSegundoBuscador.addAll(this.getFiltroBusqueda2().filtrar(muestrasAFiltrar));
		
		muestrasAFiltrarPorPrimerBuscador.retainAll(muestrasAFiltrarPorSegundoBuscador); // Me quedo con las muestras que aparecen en ambas listas
		return(muestrasAFiltrarPorPrimerBuscador);
	}
}