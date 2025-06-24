package ar.edu.unq.po2.Sistema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Organizacion.Organizacion;
import ar.edu.unq.po2.Usuario.Usuario;

class SistemaTest {


	@Test
	void test00SistemaSeIniciaConTodosSusDatos() {
		Sistema sistema = new Sistema();
		
		assertTrue(sistema.getMuestras().isEmpty());
		assertTrue(sistema.getOrganizaciones().isEmpty());
		assertTrue(sistema.getUsuarios().isEmpty());
		
	}
	
	@Test
	void test01SistemaRegistraUnUsuario() {
		Sistema sistema = new Sistema();
		assertTrue(sistema.getUsuarios().isEmpty());
		
		Usuario usuarioMock = mock(Usuario.class);
		
		sistema.registrarUsuario(usuarioMock);
		
		assertTrue(sistema.getUsuarios().contains(usuarioMock));

	}
	
	@Test
	void test02SistemaNoRegistraMismoUsuario2Veces() {
		Sistema sistema = new Sistema();
		assertTrue(sistema.getUsuarios().isEmpty());
		assertEquals(0, sistema.getUsuarios().size());
		
		Usuario usuarioMock = mock(Usuario.class);
		
		sistema.registrarUsuario(usuarioMock);
		assertEquals(1, sistema.getUsuarios().size());
		
		sistema.registrarUsuario(usuarioMock);
		assertEquals(1, sistema.getUsuarios().size());
		
	}
	
	@Test
	void test03SistemaRegistraUnaOrganizacion() {
		Sistema sistema = new Sistema();
		assertTrue(sistema.getOrganizaciones().isEmpty());
		
		Organizacion organizacionMock = mock(Organizacion.class);
		
		sistema.registrarOrganizacion(organizacionMock);
		
		assertTrue(sistema.getOrganizaciones().contains(organizacionMock));

	}
	
	@Test
	void test04SistemaNoRegistraUnaMismaOrganizacion2Veces() {
		Sistema sistema = new Sistema();
		assertTrue(sistema.getOrganizaciones().isEmpty());
		assertEquals(0, sistema.getOrganizaciones().size());
		
		Organizacion organizacionMock = mock(Organizacion.class);
		
		sistema.registrarOrganizacion(organizacionMock);
		assertEquals(1, sistema.getOrganizaciones().size());
		
		sistema.registrarOrganizacion(organizacionMock);
		assertEquals(1, sistema.getOrganizaciones().size());

	}
	
	@Test
	void test05SistemaRegistraUnaMuestra() {
	    Sistema sistema = new Sistema();
	    assertTrue(sistema.getMuestras().isEmpty());

	    Muestra muestraMock = mock(Muestra.class);

	    sistema.agregarMuestra(muestraMock);

	    assertTrue(sistema.getMuestras().contains(muestraMock));
	}

	@Test
	void test06SistemaNoRegistraUnaMismaMuestra2Veces() {
	    Sistema sistema = new Sistema();
	    assertTrue(sistema.getMuestras().isEmpty());
	    assertEquals(0, sistema.getMuestras().size());

	    Muestra muestraMock = mock(Muestra.class);

	    sistema.agregarMuestra(muestraMock);
	    assertEquals(1, sistema.getMuestras().size());

	    sistema.agregarMuestra(muestraMock);
	    assertEquals(1, sistema.getMuestras().size());
	}
	
	@Test
	void test07SistemaDelegaLaActualizacionDeNivelesAUnGestorDeUsuarios() {
		Sistema sistema = new Sistema();
		Usuario usuarioMock = mock(Usuario.class);
		
		when(usuarioMock.esExperto()).thenReturn(true);
		
		sistema.registrarUsuario(usuarioMock);
		assertTrue(sistema.getUsuarios().contains(usuarioMock));
		
		sistema.actualizarNivelesDeUsuarios();		
		
		verify(usuarioMock).degradarABasico();
	}

}
