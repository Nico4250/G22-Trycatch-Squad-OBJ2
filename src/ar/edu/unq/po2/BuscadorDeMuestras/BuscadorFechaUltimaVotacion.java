package ar.edu.unq.po2.BuscadorDeMuestras;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Muestra;

public class BuscadorFechaUltimaVotacion implements iBuscadorMuestras {

	private LocalDate fecha;
	
	@Override
	public List<Muestra> Filtrar(List<Muestra> muestrasAFiltrar) {
		return muestrasAFiltrar.stream()
				.filter(muestra -> muestra.getOpiniones().stream()
				.anyMatch(opinion -> opinion.getFechaPublicacion().isAfter(fecha)))
				.collect(Collectors.toList());
	}

}
