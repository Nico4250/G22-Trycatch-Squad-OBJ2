package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.List;

import ar.edu.unq.po2.Muestra.Muestra;

public interface iBuscadorMuestras {

	List <Muestra> Filtrar(List<Muestra> muestrasAFiltrar);
	
}
