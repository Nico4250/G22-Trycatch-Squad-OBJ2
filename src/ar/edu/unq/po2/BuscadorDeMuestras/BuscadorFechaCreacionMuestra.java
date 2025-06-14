package ar.edu.unq.po2.BuscadorDeMuestras;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorFechaCreacionMuestra implements iBuscadorMuestras {

 private LocalDate fecha;
 
	@Override
	public List<Muestra> Filtrar(List<Muestra> muestrasAFiltrar) {
		List<Muestra> resultado = new ArrayList<>();
		resultado = muestrasAFiltrar.stream()
				.filter(muestra -> muestra.getFechaCreacion().isAfter(this.fecha))
				.collect(Collectors.toList());
		return (resultado);
	}
	
	public void setFecha (LocalDate fecha) {
		this.fecha = fecha;
	}

}
