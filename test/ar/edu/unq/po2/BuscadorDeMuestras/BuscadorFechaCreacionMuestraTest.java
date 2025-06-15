package ar.edu.unq.po2.BuscadorDeMuestras;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ar.edu.unq.po2.Muestra.Muestra;

class BuscadorFechaCreacionMuestraTest {

	  private BuscadorFechaCreacionMuestra buscadorFechaCreacionMuestra;
	  private List<Muestra> muestrasAFiltrar;
	  
	  
	  @Mock
	  Muestra muestra1 = Mockito.mock(Muestra.class);
	  Muestra muestra2 = Mockito.mock(Muestra.class);
	  Muestra muestra3 = Mockito.mock(Muestra.class);

	    @BeforeEach
	    public void setup() { 
	    	muestrasAFiltrar = new ArrayList<>();
	    	buscadorFechaCreacionMuestra = new BuscadorFechaCreacionMuestra();
	        LocalDate fecha = LocalDate.of(2022, 1, 1);
	        when(muestra1.getFechaCreacion()).thenReturn(LocalDate.of(2021, 12, 31));
	        when(muestra2.getFechaCreacion()).thenReturn(LocalDate.of(2022, 2, 1));
	        when(muestra3.getFechaCreacion()).thenReturn(LocalDate.of(2022, 1, 2));

	        muestrasAFiltrar.add(muestra1);
	        muestrasAFiltrar.add(muestra2);
	        muestrasAFiltrar.add(muestra3);

	        buscadorFechaCreacionMuestra.setFecha(fecha);
	        
	        
	    }

	    @Test
	    public void testFiltrar() {			        
	   
	        List<Muestra> resultado = buscadorFechaCreacionMuestra.filtrar(muestrasAFiltrar);

	        // Assert
	        assertEquals(2, resultado.size());
	        assertTrue (resultado.contains(muestra2));
	        assertTrue (resultado.contains(muestra3));

	    }
}


