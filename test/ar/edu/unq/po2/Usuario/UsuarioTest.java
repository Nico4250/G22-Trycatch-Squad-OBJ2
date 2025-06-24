package ar.edu.unq.po2.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Muestra.OpinionImagen;
import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Sistema.GestorDeUsuarios;
import ar.edu.unq.po2.Sistema.Sistema;

import static org.mockito.Mockito.*;
import static org.mockito.Spy.*;
import java.util.ArrayList;

class UsuarioTest {
	//SUT
	private Usuario juan;
	private Usuario marcos;
	private Usuario carlos;
	
	//DOC
	private Sistema sistemaMock;
	private Ubicacion ubicacionMuestraMock;
	private Muestra muestra1Mock;
	private Muestra muestra2Mock;
	private Opinion opinionMock;
	private Opinion opinion2Mock;
	private Ubicacion ubicacionMock;
	
	
	
	@BeforeEach
	void setUp() {
		juan = new Usuario(0,"juan200");
		carlos = new Usuario(1,"carlos200");
		marcos = new UsuarioEspecialista(2, "marcosPro");
		sistemaMock = mock(Sistema.class);
		opinionMock = mock(Opinion.class);
		opinion2Mock = mock(Opinion.class);
		ubicacionMock = mock(Ubicacion.class);
		muestra1Mock = mock(Muestra.class);
		muestra2Mock = mock(Muestra.class);

	}	
	
	@Test
	void test00UnUsuarioSeCreaConTodosSusDatos() {
		Usuario usuarioInicial = new Usuario(0, "usuario0.exe");
		
		assertEquals(0, usuarioInicial.getId());
		assertEquals("usuario0.exe", usuarioInicial.nombreUsuario());
		assertEquals (false, usuarioInicial.esExperto());
		assertEquals(false, usuarioInicial.esEspecialista());
	}
	
	@Test 
	void test01UnUsuarioBasicoNoEsEspecialista() {
		assertFalse(juan.esEspecialista());
	}
	
	@Test 
	void test02UnUsuarioEspecialistaNoIniciaComoExperto() {
		assertFalse(juan.esExperto());
	}

	@Test
	void test03UsuarioSubeUnaMuestra() {
		juan.subirMuestraA (muestra1Mock, sistemaMock);		
		
		verify(sistemaMock).agregarMuestra(muestra1Mock);

	}

	@Test
	void test04UsuarioOpinaSobreUnaMuestra() {
		juan.subirMuestraA (muestra1Mock, sistemaMock);
		carlos.opinarSobreMuestra(muestra1Mock, OpinionImagen.VINCHUCA_INFESTANS);
		verify(muestra1Mock).agregarOpinion(any(Opinion.class));
	}
	
	@Test 
	void test05UsuarioBasicoPuedeConvertirseEnExperto() {
		assertFalse(juan.esExperto());
		juan.promoverAExperto();
		assertTrue(juan.esExperto());
	}
	
	@Test 
	void test06UsuarioExpertoPuedeConvertirseEnBasico() {
		juan.promoverAExperto();
		assertTrue(juan.esExperto());
		
		juan.degradarABasico();
		assertFalse(juan.esExperto());
	}


}