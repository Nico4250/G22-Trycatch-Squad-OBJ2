package ar.edu.unq.po2.Muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Usuario.Usuario;

class OpinionTest {


	@Test
	void test00SeCreaUnaOpinionConTodosSusDatos() {
		Usuario usuarioMock = mock(Usuario.class);
		Opinion opinionInicial = new Opinion(usuarioMock, OpinionImagen.VINCHUCA_GUASAYANA);
		
		assertEquals(usuarioMock, opinionInicial.getUsuario());
		assertEquals(OpinionImagen.VINCHUCA_GUASAYANA, opinionInicial.getOpinion());
		assertEquals(LocalDate.now(), opinionInicial.getFechaPublicacion());
	}

}
