package ar.edu.unq.po2.Muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import ar.edu.unq.po2.Usuario.Usuario;

class MuestraNoVerificadaTest {

	private IEstadoMuestra estadoNoVerificado;
	
	private Muestra muestraMock; 
	private Opinion opinionMock;
	private Usuario usuarioMock;
	private Usuario usuario2Mock;

    @BeforeEach
    void setUp() {
    	muestraMock = mock(Muestra.class);
    	opinionMock = mock(Opinion.class);
    	usuarioMock = mock(Usuario.class);
    	usuario2Mock = mock(Usuario.class);
    	estadoNoVerificado = new MuestraNoVerificada(muestraMock); 
    }
    
    @Test
    void unaMuestraNoVerificadaNoEsVerificada() {
    	assertFalse(estadoNoVerificado.esVerificada());
    }
    
    @Test
    void puedeOpinarUnUsuarioBasico() {
    	
        when(usuario2Mock.getId()).thenReturn(1);
        when(muestraMock.getUsuario()).thenReturn(usuarioMock);
    	
        when(usuario2Mock.getId()).thenReturn(2);
        when(opinionMock.getUsuario()).thenReturn(usuario2Mock);

        when(muestraMock.elUsuarioNoOpino(usuario2Mock)).thenReturn(true);
        
        assertTrue(estadoNoVerificado.puedeOpinar(usuario2Mock));
        
        estadoNoVerificado.agregarOpinion(opinionMock);
        verify(muestraMock).agregarOpinionDe(opinionMock);
    }


    
    @Test
    void siUnExpertoOpinaPasaAEstadoEnVerificacion() { 
      
        when(usuarioMock.getId()).thenReturn(1);
        when(usuarioMock.esExperto()).thenReturn(true);
        when(opinionMock.getUsuario()).thenReturn(usuarioMock);

        when(muestraMock.getUsuario()).thenReturn(usuario2Mock);
        when(muestraMock.elUsuarioNoOpino(usuarioMock)).thenReturn(true);
        
        estadoNoVerificado.agregarOpinion(opinionMock);
        
        
        verify(muestraMock).cambiarEstado(any(MuestraEnVerificacion.class));
    }

}
