package ar.edu.unq.po2.Sistema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Organizacion.Organizacion;
import ar.edu.unq.po2.Organizacion.ZonaDeCobertura;
import ar.edu.unq.po2.Usuario.Usuario;
import ar.edu.unq.po2.Organizacion.Ubicacion;

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
	void test07SistemaRegistraUnaZona() {
	    Sistema sistema = new Sistema();
	    assertTrue(sistema.getZonasDeCobertura().isEmpty());

	    ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);

	    sistema.registrarZonaDeCobertura(zonaMock);

	    assertTrue(sistema.getZonasDeCobertura().contains(zonaMock));
	}

	@Test
	void test08SistemaNoRegistraUnaMismaZona2Veces() {
	    Sistema sistema = new Sistema();
	    assertTrue(sistema.getZonasDeCobertura().isEmpty());
	    assertEquals(0, sistema.getZonasDeCobertura().size());

	    ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);

	    sistema.registrarZonaDeCobertura(zonaMock);
	    assertEquals(1, sistema.getZonasDeCobertura().size());

	    sistema.registrarZonaDeCobertura(zonaMock);
	    assertEquals(1, sistema.getZonasDeCobertura().size());
	}
	
	@Test
	void test09AgregarMuestraNotificaZonasDeCoberturaCercanas() {
	    Sistema sistema = new Sistema();

	    // Mock de la muestra y su ubicación
	    Muestra muestraMock = mock(Muestra.class);
	    Ubicacion ubicacionMuestra = mock(Ubicacion.class);
	    when(muestraMock.getUbicacion()).thenReturn(ubicacionMuestra);

	    // Mock de zona de cobertura 1 (cercana)
	    ZonaDeCobertura zonaCercana = mock(ZonaDeCobertura.class);
	    Ubicacion epicentroCercano = mock(Ubicacion.class);
	    when(zonaCercana.getEpicentro()).thenReturn(epicentroCercano);
	    when(epicentroCercano.distanciaEntre(ubicacionMuestra)).thenReturn(5.0); // dentro del radio
	    when(zonaCercana.getRadio()).thenReturn(10);

	    // Mock de zona de cobertura 2 (lejana)
	    ZonaDeCobertura zonaLejana = mock(ZonaDeCobertura.class);
	    Ubicacion epicentroLejano = mock(Ubicacion.class);
	    when(zonaLejana.getEpicentro()).thenReturn(epicentroLejano);
	    when(epicentroLejano.distanciaEntre(ubicacionMuestra)).thenReturn(20.0); // fuera del radio
	    when(zonaLejana.getRadio()).thenReturn(10);

	    // Registrar zonas
	    sistema.registrarZonaDeCobertura(zonaCercana);
	    sistema.registrarZonaDeCobertura(zonaLejana);

	    // Agregar muestra
	    sistema.agregarMuestra(muestraMock);

	    // Verifica que solo la zona cercana fue notificada
	    verify(zonaCercana).notificarCargaMuestra(muestraMock);
	    verify(zonaLejana, never()).notificarCargaMuestra(muestraMock);
	}
	
	@Test
	void test10SistemaNotificaValidacionAMuestrasCercanas() {
	    Sistema sistema = new Sistema();

	    // Mocks compartidos de ubicaciones
	    Ubicacion ubicacionMuestra = mock(Ubicacion.class);
	    Ubicacion epicentro1 = mock(Ubicacion.class);
	    Ubicacion epicentro2 = mock(Ubicacion.class);

	    // Muestra
	    Muestra muestraMock = mock(Muestra.class);
	    when(muestraMock.getUbicacion()).thenReturn(ubicacionMuestra);

	    // Zona 1: cercana (distancia 5 <= radio 10)
	    ZonaDeCobertura zonaMock1 = mock(ZonaDeCobertura.class);
	    when(zonaMock1.getEpicentro()).thenReturn(epicentro1);
	    when(zonaMock1.getRadio()).thenReturn(10);
	    when(epicentro1.distanciaEntre(ubicacionMuestra)).thenReturn(5.0);

	    // Zona 2: lejana (distancia 20 > radio 10)
	    ZonaDeCobertura zonaMock2 = mock(ZonaDeCobertura.class);
	    when(zonaMock2.getEpicentro()).thenReturn(epicentro2);
	    when(zonaMock2.getRadio()).thenReturn(10);
	    when(epicentro2.distanciaEntre(ubicacionMuestra)).thenReturn(20.0);

	    // Registrar zonas
	    sistema.registrarZonaDeCobertura(zonaMock1);
	    sistema.registrarZonaDeCobertura(zonaMock2);

	    // Ejecutar
	    sistema.muestraFueValidada(muestraMock);

	    // Verificar
	    verify(zonaMock1).notificarValidacionMuestra(muestraMock);
	    verify(zonaMock2, never()).notificarValidacionMuestra(muestraMock);
	}

}
