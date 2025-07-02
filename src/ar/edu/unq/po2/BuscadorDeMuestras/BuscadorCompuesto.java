package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public abstract class BuscadorCompuesto implements IBuscadorMuestras {
	
	   private List<IBuscadorMuestras> buscadoresDeMuestras = new ArrayList<>();

	   
	    public void addBuscador(IBuscadorMuestras buscadorMuestras) {
	        buscadoresDeMuestras.add(buscadorMuestras);
	    }

	    public void removeBuscador(IBuscadorMuestras buscadorMuestras) {
	        buscadoresDeMuestras.remove(buscadorMuestras);
	    }

		@Override
		public List<Muestra> filtrar(List<Muestra> muestrasAFiltrar) {
			// TODO Auto-generated method stub
			return null;
		}


}
