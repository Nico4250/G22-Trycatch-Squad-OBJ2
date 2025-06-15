package ar.edu.unq.po2.BuscadorDeMuestras;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.Muestra.IEstadoMuestra;
import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.MuestraVerificada;
import ar.edu.unq.po2.Muestra.OpinionImagen;

class BuscadorAndTest {

	List<Muestra> muestrasAFiltrar = new ArrayList<Muestra>();
	BuscadorAnd					 buscadorAnd;
	BuscadorNivelVerificacion 	 buscadorNivelVerificacion;
	BuscadorTipoInsecto			 buscadorTipoInsecto;
	Boolean						 estadoMuestraABuscar;

 
	
	@Mock
	Muestra  muestra1 = mock(Muestra.class);
	Muestra  muestra2 = mock(Muestra.class);
	Muestra  muestra3 = mock(Muestra.class);
    Muestra  muestra4 = mock(Muestra.class);
    MuestraVerificada muestraVerificada =  mock(MuestraVerificada.class);
    
    IEstadoMuestra estadoMuestraVerificado;
    IEstadoMuestra estadoMuestraNoVerificado;
	
	
	@BeforeEach
	void setUp() {
		buscadorNivelVerificacion = new BuscadorNivelVerificacion();
		buscadorTipoInsecto = new BuscadorTipoInsecto();
		buscadorAnd = new BuscadorAnd(buscadorTipoInsecto, buscadorNivelVerificacion);
		muestrasAFiltrar = new ArrayList<>();


		estadoMuestraVerificado = mock(IEstadoMuestra.class);
	    estadoMuestraNoVerificado = mock(IEstadoMuestra.class);
	    
	    when(muestra1.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
	  	when(muestra2.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
	  	when(muestra3.getTipoInsecto()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
	  	when(muestra4.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_SORDIDA);
	  
		when(muestra1.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
        when(muestra2.getEstadoMuestra()).thenReturn(estadoMuestraNoVerificado);
        when(muestra3.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
        when(muestra4.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
        
        when(estadoMuestraVerificado.esVerificada()).thenReturn(true);
        when(estadoMuestraNoVerificado.esVerificada()).thenReturn(false);
		
		muestrasAFiltrar.add(muestra1);
		muestrasAFiltrar.add(muestra2);
		muestrasAFiltrar.add(muestra3);
		muestrasAFiltrar.add(muestra4);
	
	}
	
	@Test
	void buscadorAndNoContiene() {
		buscadorNivelVerificacion.setEstadoMuestraABuscar(false);
		buscadorTipoInsecto.setInsectoABuscar("vinchuca");
		
		assertFalse(buscadorAnd.filtrar(muestrasAFiltrar).contains(muestra1));
		
		
	}
	
	@Test
	void buscadorAnd() {
		buscadorNivelVerificacion.setEstadoMuestraABuscar(true);
		buscadorTipoInsecto.setInsectoABuscar("chinche");
		assertTrue(buscadorAnd.filtrar(muestrasAFiltrar).contains(muestra3));
	}

}
