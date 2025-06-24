package ar.edu.unq.po2.Muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    	estadoNoVerificado = new MuestraNoVerificada(); 
    }
    
    @Test
    void test01UnaMuestraNoVerificadaNoEsVerificada() {
    	assertFalse(estadoNoVerificado.esVerificada());
    }
    
    @Test
    void test02PuedeOpinarUnUsuarioBasico() {
    	
        when(usuario2Mock.getId()).thenReturn(1);
        when(muestraMock.getUsuario()).thenReturn(usuarioMock);
    	
        when(usuario2Mock.getId()).thenReturn(2);
        when(opinionMock.getUsuario()).thenReturn(usuario2Mock);

        when(muestraMock.elUsuarioNoOpino(usuario2Mock)).thenReturn(true);
        
        assertTrue(estadoNoVerificado.puedeOpinar(muestraMock, usuario2Mock));
        
        estadoNoVerificado.agregarOpinion(muestraMock, opinionMock);
        verify(muestraMock).agregarOpinionDe(opinionMock);
    }


    
    @Test
    void test03SiUnExpertoOpinaPasaAEstadoEnVerificacion() { 
      
        when(usuarioMock.getId()).thenReturn(1);
        when(usuarioMock.esExperto()).thenReturn(true);
        when(opinionMock.getUsuario()).thenReturn(usuarioMock);

        when(muestraMock.getUsuario()).thenReturn(usuario2Mock);
        when(muestraMock.elUsuarioNoOpino(usuarioMock)).thenReturn(true);
        
        estadoNoVerificado.agregarOpinion(muestraMock, opinionMock);
        
        
        verify(muestraMock).cambiarEstado(any(MuestraEnVerificacion.class));
    }

}
