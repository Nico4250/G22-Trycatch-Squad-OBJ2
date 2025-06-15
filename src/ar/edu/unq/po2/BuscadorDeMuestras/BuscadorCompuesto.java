package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorCompuesto implements IBuscadorMuestras {
	
	   private List<IBuscadorMuestras> buscadoresMuestras = new ArrayList<>();

	    @Override
	    public List<Muestra> filtrar(List<Muestra> muestrasAFiltrar) {
	        List<Muestra> resultadosFiltrados = new ArrayList<>();

	        for (IBuscadorMuestras buscador : buscadoresMuestras) {
	            List<Muestra> muestrasFiltradas = buscador.filtrar(muestrasAFiltrar);
	            resultadosFiltrados.addAll(muestrasFiltradas);
	        }

	        return resultadosFiltrados.stream().distinct().collect(Collectors.toList());
	    }

	    public void addBuscador(IBuscadorMuestras buscadorMuestras) {
	        buscadoresMuestras.add(buscadorMuestras);
	    }

	    public void removeBuscador(IBuscadorMuestras buscadorMuestras) {
	        buscadoresMuestras.remove(buscadorMuestras);
	    }


}
