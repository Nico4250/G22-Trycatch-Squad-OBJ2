package ar.edu.unq.po2.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsuarioEspecialistaTest {

	@Test
	void test00UnUsuarioEspecialistaSeCreaConTodosSusDatos() {
		Usuario usuarioInicial = new UsuarioEspecialista(0, "usuario0.exe");
		
		assertEquals(0, usuarioInicial.getId());
		assertEquals("usuario0.exe", usuarioInicial.nombreUsuario());
		assertEquals (true, usuarioInicial.esExperto());
	}
	
	@Test 
	void test02UnUsuarioEspecialistaIniciaComoExperto() {
		Usuario usuarioInicial = new UsuarioEspecialista(0, "usuario0.exe");
		assertTrue(usuarioInicial.esExperto());
	}
}
