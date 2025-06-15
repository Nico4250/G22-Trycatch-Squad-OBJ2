package ar.edu.unq.po2.BuscadorDeMuestras;

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
import ar.edu.unq.po2.Muestra.OpinionImagen;

class BuscadorCompuestoTest {

	List<Muestra> muestrasAFiltrar = new ArrayList<Muestra>();
	List<IBuscadorMuestras> buscadoresAAplicar = new ArrayList<IBuscadorMuestras>();
	BuscadorTipoInsecto buscadorTipoInsecto;
	BuscadorFechaCreacionMuestra buscadorFechaCreacionMuestra;
	BuscadorCompuesto buscadorCompuesto;

	@Mock
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	Muestra muestra3 = mock(Muestra.class);
	Muestra muestra4 = mock(Muestra.class);


	@BeforeEach
	void setUp() {
		buscadorFechaCreacionMuestra = new BuscadorFechaCreacionMuestra();
		buscadorTipoInsecto = new BuscadorTipoInsecto();
		buscadorCompuesto = new BuscadorCompuesto();
		
		when(muestra1.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
	  	when(muestra2.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
	  	when(muestra3.getTipoInsecto()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
	  	when(muestra4.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_SORDIDA);
	  	
	  	LocalDate fecha = LocalDate.of(2022, 1, 1);
        when(muestra1.getFechaCreacion()).thenReturn(LocalDate.of(2021, 12, 31));
        when(muestra2.getFechaCreacion()).thenReturn(LocalDate.of(2022, 2, 1));
        when(muestra3.getFechaCreacion()).thenReturn(LocalDate.of(2022, 1, 2));
        when(muestra4.getFechaCreacion()).thenReturn(LocalDate.of(1998, 12, 15));
        

	    
	
        muestrasAFiltrar.add(muestra1);
		muestrasAFiltrar.add(muestra2);     
		muestrasAFiltrar.add(muestra3);
		muestrasAFiltrar.add(muestra4);
		
		buscadorFechaCreacionMuestra.setFecha(fecha);
		
		buscadorCompuesto.addBuscador(this.buscadorTipoInsecto);
		buscadorCompuesto.addBuscador(this.buscadorFechaCreacionMuestra);
		

	}

	@Test
	void busquedaAvanzada() {
		buscadorTipoInsecto.setInsectoABuscar("vinchuca");
	    
		assertTrue(buscadorCompuesto.filtrar(muestrasAFiltrar).contains(muestra4));
	}

}
