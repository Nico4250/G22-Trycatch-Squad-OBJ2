package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorOr extends BuscadorCompuesto {
	
  private List<IBuscadorMuestras> buscadoresDeMuestras = new ArrayList<>();

	   
    public void addBuscador(IBuscadorMuestras buscadorMuestras) {
		        buscadoresDeMuestras.add(buscadorMuestras);
    }

    public void removeBuscador(IBuscadorMuestras buscadorMuestras) {
		        buscadoresDeMuestras.remove(buscadorMuestras);
	}

	public List<Muestra> filtrar(List<Muestra> muestrasAFiltrar) {
		return buscadoresDeMuestras.stream()
									.flatMap(buscador -> buscador.filtrar(muestrasAFiltrar).stream())
									.distinct()
									.collect(Collectors.toList());
		
	}

}
