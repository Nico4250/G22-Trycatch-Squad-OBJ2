package ar.edu.unq.po2.BuscadorDeMuestras;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.Muestra.IEstadoMuestra;
import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.MuestraVerificada;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Muestra.OpinionImagen;

class BuscadorAndTest {

	List<Muestra> muestrasAFiltrar;

	BuscadorNivelVerificacion buscadorNivelVerificacion;
	BuscadorTipoInsecto buscadorTipoInsecto;
	BuscadorFechaUltimaVotacion buscadorFechaUltimaVotacion;

	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	Muestra muestra4;
	Muestra muestra5;

	Opinion opinionAntigua;
	Opinion opinionReciente;

	IEstadoMuestra estadoMuestraVerificado;
	IEstadoMuestra estadoMuestraNoVerificado;

	@BeforeEach
	void setUp() {
	
		buscadorNivelVerificacion     = new BuscadorNivelVerificacion();
		buscadorTipoInsecto           = new BuscadorTipoInsecto();
		buscadorFechaUltimaVotacion   = new BuscadorFechaUltimaVotacion();

	
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);

		opinionAntigua  = mock(Opinion.class);
		opinionReciente = mock(Opinion.class);

		estadoMuestraVerificado   = mock(IEstadoMuestra.class);
		estadoMuestraNoVerificado = mock(IEstadoMuestra.class);

	
		when(muestra1.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
		when(muestra2.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
		when(muestra3.getTipoInsecto()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
		when(muestra4.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_SORDIDA);
		when(muestra5.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);

	
		when(muestra1.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
		when(muestra2.getEstadoMuestra()).thenReturn(estadoMuestraNoVerificado);
		when(muestra3.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
		when(muestra4.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
		when(muestra5.getEstadoMuestra()).thenReturn(estadoMuestraNoVerificado);

		when(estadoMuestraVerificado.esVerificada()).thenReturn(true);
		when(estadoMuestraNoVerificado.esVerificada()).thenReturn(false);

	
		when(opinionAntigua.getFechaPublicacion()).thenReturn(LocalDate.of(2018, 1, 1));
		when(opinionReciente.getFechaPublicacion()).thenReturn(LocalDate.of(2021, 1, 1));

		
		when(muestra5.getOpiniones()).thenReturn(new ArrayList<>(List.of(opinionReciente)));

		muestrasAFiltrar = List.of(muestra1, muestra2, muestra3, muestra4, muestra5);
	}

	@Test
	void buscadorAndNoContieneMuestra1() {
		buscadorTipoInsecto.setInsectoABuscar("vinchuca");
		buscadorNivelVerificacion.setEstadoMuestraABuscar(false);

		BuscadorAnd and = new BuscadorAnd();
		and.addBuscador(buscadorTipoInsecto);
		and.addBuscador(buscadorNivelVerificacion);

		assertFalse(and.filtrar(muestrasAFiltrar).contains(muestra1));
	}

	@Test
	void buscadorAndContieneMuestra3() {
		buscadorTipoInsecto.setInsectoABuscar("chinche");
		buscadorNivelVerificacion.setEstadoMuestraABuscar(true);

		BuscadorAnd and = new BuscadorAnd();
		and.addBuscador(buscadorTipoInsecto);
		and.addBuscador(buscadorNivelVerificacion);

		assertTrue(and.filtrar(muestrasAFiltrar).contains(muestra3));
	}

	@Test
	void muestraCumpleVinchucaYVerificadaOFecha() {
		buscadorTipoInsecto.setInsectoABuscar("vinchuca");
		buscadorNivelVerificacion.setEstadoMuestraABuscar(true);
		buscadorFechaUltimaVotacion.setFecha(LocalDate.of(2019, 4, 20));

		
		BuscadorOr or = new BuscadorOr();
		or.addBuscador(buscadorNivelVerificacion);
		or.addBuscador(buscadorFechaUltimaVotacion);


		BuscadorAnd and = new BuscadorAnd();
		and.addBuscador(buscadorTipoInsecto);
		and.addBuscador(or);

		List<Muestra> resultado = and.filtrar(muestrasAFiltrar);

		assertTrue(resultado.contains(muestra5));
	}
}



	
