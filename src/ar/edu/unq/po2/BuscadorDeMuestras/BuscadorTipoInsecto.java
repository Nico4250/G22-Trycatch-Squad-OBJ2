package ar.edu.unq.po2.BuscadorDeMuestras;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorTipoInsecto implements IBuscadorMuestras {
	
private String insectoABuscar;

	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasAFiltrar) {
	    return muestrasAFiltrar.stream()
					.filter(muestra -> muestra.getTipoInsecto().name().toLowerCase().contains(insectoABuscar.toLowerCase()))
					.collect(Collectors.toList());
							
		
	 }
	
	public void setInsectoABuscar (String insectoABuscar) {
		this.insectoABuscar = insectoABuscar;
	}

}

