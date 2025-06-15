package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.List;

import ar.edu.unq.po2.Muestra.Muestra;

public abstract class BuscadorLogico implements IBuscadorMuestras {
	
	private IBuscadorMuestras filtroBusqueda1;
	private IBuscadorMuestras filtroBusqueda2;

	public BuscadorLogico(IBuscadorMuestras filtroBusqueda1, IBuscadorMuestras filtroBusqueda2) {
		this.filtroBusqueda1 = filtroBusqueda1;
		this.filtroBusqueda2 = filtroBusqueda2;
	}

	public IBuscadorMuestras getFiltroBusqueda1() {
		return filtroBusqueda1;
	}

	public IBuscadorMuestras getFiltroBusqueda2() {
		return filtroBusqueda2;
	}

	@Override
	public List<Muestra> Filtrar(List<Muestra> muestrasAFiltrar) {
		// TODO Auto-generated method stub
		return null;
	}

}
