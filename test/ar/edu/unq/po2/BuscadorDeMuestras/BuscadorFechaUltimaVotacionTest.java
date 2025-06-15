package ar.edu.unq.po2.BuscadorDeMuestras;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;

class BuscadorFechaUltimaVotacionTest {

	private BuscadorFechaUltimaVotacion buscadorFechaUltimaVotacion;
	private List<Muestra> muestrasAFiltrar;
	
	@Mock
	  Muestra  muestra1 = mock(Muestra.class);
	  Muestra  muestra2 = mock(Muestra.class);
	  Muestra  muestra3 = mock(Muestra.class);
      Muestra  muestra4 = mock(Muestra.class);
      Opinion  opinion1 = mock(Opinion.class);
	  Opinion  opinion2 = mock(Opinion.class);
      Opinion  opinion3 = mock(Opinion.class);
	  Opinion  opinion4 = mock(Opinion.class);
      
	
	@BeforeEach
		public void setUp() {
		 muestrasAFiltrar = new ArrayList<>();
    	 buscadorFechaUltimaVotacion = new BuscadorFechaUltimaVotacion();
		 LocalDate fechaUltimaVotacionABuscar = LocalDate.of(2021, 9, 1);

		 when(opinion1.getFechaPublicacion()).thenReturn(LocalDate.of(2021, 8, 29));
		 when(opinion2.getFechaPublicacion()).thenReturn(LocalDate.of(2021, 9, 2));
		 when(opinion3.getFechaPublicacion()).thenReturn(LocalDate.of(2021, 9, 3));
		 when(opinion4.getFechaPublicacion()).thenReturn(LocalDate.of(2021, 8, 30));

		 when(muestra1.getOpiniones()).thenReturn(new ArrayList<>(List.of(opinion1, opinion2)));
		 when(muestra2.getOpiniones()).thenReturn(new ArrayList<>(List.of(opinion3)));
		 when(muestra3.getOpiniones()).thenReturn(new ArrayList<>(List.of(opinion4)));
		 when(muestra4.getOpiniones()).thenReturn(new ArrayList<>());

		 muestrasAFiltrar.add(muestra1);
		 muestrasAFiltrar.add(muestra2);
		 muestrasAFiltrar.add(muestra3);
		 muestrasAFiltrar.add(muestra4);

		 buscadorFechaUltimaVotacion.setFecha(fechaUltimaVotacionABuscar);
	}
	
	@Test
	public void testFiltrarMuestrasPorFechaUltimaVotacion() {
		 List<Muestra> resultado = buscadorFechaUltimaVotacion.filtrar(muestrasAFiltrar);
		    assertEquals(2, resultado.size());

	
	}
	@Test
	public void noContieneMuestra3() {
		List<Muestra> resultado = buscadorFechaUltimaVotacion.filtrar(muestrasAFiltrar);
			assertFalse(resultado.contains(muestra3));
		
	}
	@Test
	public void ContieneMuestra1() {
		List<Muestra> resultado = buscadorFechaUltimaVotacion.filtrar(muestrasAFiltrar);
		assertTrue(resultado.contains(muestra1));

	}
}

