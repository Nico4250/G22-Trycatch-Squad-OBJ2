package ar.edu.unq.po2.Muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Usuario.Usuario;

class MuestraVerificadaTest {
	
	private IEstadoMuestra estadoVerificado;
	
	private Muestra muestraMock; 
	private Opinion opinionMock;
	private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
    	muestraMock = mock(Muestra.class);
    	opinionMock = mock(Opinion.class);
    	usuarioMock = mock(Usuario.class);
    	estadoVerificado = new MuestraVerificada(); 
    }

    @Test
    void unaMuestraVerificadaEsVerificada() {
        assertTrue(estadoVerificado.esVerificada());
    }

    @Test
    void noSePuedeOpinarSobreUnaMuestraVerificada() {
    	assertDoesNotThrow(() -> estadoVerificado.agregarOpinion(muestraMock, opinionMock));
    }

    @Test
    void actualizarOpinionNoHaceNadaEnMuestraVerificada() {
    	//EN ESTE ESTADO ACTUALIZAR OPINION NO TIENE COMPORTAMIENTO
        assertDoesNotThrow(() -> estadoVerificado.actualizarOpinion(muestraMock));
    }

    @Test
    void puedeOpinarSiempreDevuelveFalse() {
    	//EL USUARIO EN ESTE CASO ES BASICO
        assertFalse(estadoVerificado.puedeOpinar(muestraMock, usuarioMock));
        
        //EL USUARIO EN ESTE CASO ES EXPERTO
        when(usuarioMock.esExperto()).thenReturn(true);
        assertFalse(estadoVerificado.puedeOpinar(muestraMock, usuarioMock));
        
        //NO ACEPTA OPINIONES EN GENERAL
    }
}