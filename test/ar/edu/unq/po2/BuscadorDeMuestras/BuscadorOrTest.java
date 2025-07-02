package ar.edu.unq.po2.BuscadorDeMuestras;

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

class BuscadorOrTest {
	
	List<Muestra> muestrasAFiltrar;

	BuscadorNivelVerificacion buscadorNivelVerificacion;
	BuscadorTipoInsecto buscadorTipoInsecto;
	BuscadorFechaUltimaVotacion buscadorFechaUltimaVotacion;

	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	Muestra muestra4;
	Muestra muestra5;

	Opinion opinionReciente;

	IEstadoMuestra estadoMuestraVerificado;
	IEstadoMuestra estadoMuestraNoVerificado;

	@BeforeEach
	void setUp() {
		buscadorNivelVerificacion   = new BuscadorNivelVerificacion();
		buscadorTipoInsecto         = new BuscadorTipoInsecto();
		buscadorFechaUltimaVotacion = new BuscadorFechaUltimaVotacion();

		
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);

		
		opinionReciente = mock(Opinion.class);

		
		estadoMuestraVerificado   = mock(IEstadoMuestra.class);
		estadoMuestraNoVerificado = mock(IEstadoMuestra.class);

		
		when(muestra1.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
		when(muestra2.getTipoInsecto()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
		when(muestra3.getTipoInsecto()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
		when(muestra4.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_SORDIDA);
		when(muestra5.getTipoInsecto()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);

	
		when(muestra1.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
		when(muestra2.getEstadoMuestra()).thenReturn(estadoMuestraNoVerificado);
		when(muestra3.getEstadoMuestra()).thenReturn(estadoMuestraVerificado);
		when(muestra4.getEstadoMuestra()).thenReturn(estadoMuestraNoVerificado);
		when(muestra5.getEstadoMuestra()).thenReturn(estadoMuestraNoVerificado);

		
		when(estadoMuestraVerificado.esVerificada()).thenReturn(true);
		when(estadoMuestraNoVerificado.esVerificada()).thenReturn(false);

		
		when(opinionReciente.getFechaPublicacion()).thenReturn(LocalDate.of(2022, 1, 1));
		when(muestra5.getOpiniones()).thenReturn(new ArrayList<>(List.of(opinionReciente)));

		
		muestrasAFiltrar = List.of(muestra1, muestra2, muestra3, muestra4, muestra5);
	}


	@Test
	void muestraEsVerificadaOEsVinchuca() {
		buscadorNivelVerificacion.setEstadoMuestraABuscar(true);
		buscadorTipoInsecto.setInsectoABuscar("vinchuca");

		BuscadorOr buscador = new BuscadorOr();
		buscador.addBuscador(buscadorNivelVerificacion);
		buscador.addBuscador(buscadorTipoInsecto);

		List<Muestra> resultado = buscador.filtrar(muestrasAFiltrar);

		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra3)); 
		assertTrue(resultado.contains(muestra4)); 
		assertTrue(resultado.contains(muestra5)); 
		assertFalse(resultado.contains(muestra2));
	}

	@Test
	void muestraEsVerificadaOFechaPosterior2019() {
		buscadorNivelVerificacion.setEstadoMuestraABuscar(true);
		buscadorFechaUltimaVotacion.setFecha(LocalDate.of(2019, 4, 20));

		BuscadorOr buscador = new BuscadorOr();
		buscador.addBuscador(buscadorNivelVerificacion);
		buscador.addBuscador(buscadorFechaUltimaVotacion);

		List<Muestra> resultado = buscador.filtrar(muestrasAFiltrar);

		assertTrue(resultado.contains(muestra1)); 
		assertTrue(resultado.contains(muestra3)); 
		assertTrue(resultado.contains(muestra5)); 
		assertFalse(resultado.contains(muestra2)); 
		assertFalse(resultado.contains(muestra4)); 
	}


	@Test
	void muestraEsVerificadaOEsVinchucaConFechaReciente() {
		
		buscadorNivelVerificacion.setEstadoMuestraABuscar(true);
		buscadorTipoInsecto.setInsectoABuscar("vinchuca");
		buscadorFechaUltimaVotacion.setFecha(LocalDate.of(2019, 4, 20));

		
		BuscadorAnd subAnd = new BuscadorAnd();
		subAnd.addBuscador(buscadorTipoInsecto);
		subAnd.addBuscador(buscadorFechaUltimaVotacion);

		
		BuscadorOr buscador = new BuscadorOr();
		buscador.addBuscador(buscadorNivelVerificacion);
		buscador.addBuscador(subAnd);

		List<Muestra> resultado = buscador.filtrar(muestrasAFiltrar);

		assertTrue(resultado.contains(muestra1)); 
		assertTrue(resultado.contains(muestra3)); 
		assertTrue(resultado.contains(muestra5)); 
		assertFalse(resultado.contains(muestra2)); 
		assertFalse(resultado.contains(muestra4)); 
	}
}


