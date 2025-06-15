package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorNivelVerificacion implements IBuscadorMuestras {

	private boolean estadoMuestraABuscar;

	@Override
	public List<Muestra> filtrar (List<Muestra> muestrasAFiltrar) {
	   return muestrasAFiltrar.stream()
	            .filter(muestra -> muestra.getEstadoMuestra().esVerificada() == estadoMuestraABuscar)
	            .collect(Collectors.toList());
	    
	}
	
	public void setEstadoMuestraABuscar(boolean estadoMuestraABuscar) {
		this.estadoMuestraABuscar = estadoMuestraABuscar;
	}

}
