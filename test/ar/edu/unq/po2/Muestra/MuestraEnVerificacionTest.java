package ar.edu.unq.po2.Muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Usuario.Usuario;

class MuestraEnVerificacionTest {

	private IEstadoMuestra estadoEnVerificacion;
	
	private Muestra muestraMock; 
	private Opinion opinionMock;
	private Opinion opinion2Mock;
	private Usuario usuarioMock;
	private Usuario usuario2Mock;

    @BeforeEach
    void setUp() {
    	muestraMock = mock(Muestra.class);
    	opinionMock = mock(Opinion.class);
    	opinion2Mock = mock(Opinion.class);
    	usuarioMock = mock(Usuario.class);
    	usuario2Mock = mock(Usuario.class);
    	estadoEnVerificacion = new MuestraEnVerificacion(muestraMock); 
    }
    
    @Test
    void unaMuestraEnVerificacionNoEsVerificada() {
    	
    	assertFalse(estadoEnVerificacion.esVerificada());
    }
    
    @Test
    void usuariosBasicosNoPuedenOpinarEnEsteEstado() {
    	
    	when(opinionMock.getUsuario()).thenReturn(usuarioMock);
    	
    	assertFalse(estadoEnVerificacion.puedeOpinar(usuarioMock));
    	

    }
    
    @Test
    void usuariosExpertosPuedenOpinarEnEsteEstado() {
    	
    	when(usuarioMock.getId()).thenReturn(1);
    	when(muestraMock.getUsuario()).thenReturn(usuarioMock);
    	
    	when(usuario2Mock.getId()).thenReturn(2);
    	when(usuario2Mock.esExperto()).thenReturn(true);
    	when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);

    	when(muestraMock.elUsuarioNoOpino(usuario2Mock)).thenReturn(true);
    	when(muestraMock.opinionesExpertos()).thenReturn(List.of());

    	
    	assertTrue(estadoEnVerificacion.puedeOpinar(usuario2Mock));
            
        assertDoesNotThrow(() -> estadoEnVerificacion.agregarOpinion(opinion2Mock));

        verify(muestraMock).agregarOpinionDe(opinion2Mock);
            
        verify(muestraMock).actualizarOpinion();
       
    }
    
    @Test
    void actualizarOpinionCambiaLaOpinionActual() {

        when(opinionMock.getOpinion()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
        when(muestraMock.opinionesExpertos()).thenReturn(List.of(opinionMock));

        estadoEnVerificacion.actualizarOpinion();

        verify(muestraMock).actualizarOpinionActual(any());
    }

    @Test
    void unSegundoExpertoConOpiniónDistintaNoVerificaLaMuestra() {
    	ArrayList<Opinion> opiniones = new ArrayList<Opinion>();
    	
        when(usuarioMock.getId()).thenReturn(1);
        when(usuarioMock.esExperto()).thenReturn(true);
        when(opinionMock.getUsuario()).thenReturn(usuarioMock);
        when(opinionMock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
        
        when(usuario2Mock.getId()).thenReturn(2);
        when(usuario2Mock.esExperto()).thenReturn(true);
        when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
        when(opinion2Mock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
        
        opiniones.add(opinionMock);
        
        //SIMULO UNA LISTA DE OPINIONES DE LA MUESTRA, DONDE ENTRE LAS OPINIONES ESTA opinionMock
        when(muestraMock.opinionesExpertos()).thenReturn(opiniones);

        when(muestraMock.getUsuario()).thenReturn(mock(Usuario.class));
        when(muestraMock.elUsuarioNoOpino(usuario2Mock)).thenReturn(true);

        estadoEnVerificacion.agregarOpinion(opinion2Mock);

        verify(muestraMock).agregarOpinionDe(opinion2Mock);
        
        verify(muestraMock).actualizarOpinion();

        verify(muestraMock, never()).cambiarEstado(any(MuestraVerificada.class));
    }

    @Test
    void unSegundoExpertoConMismaOpinionVerificaLaMuestra() {
    	ArrayList<Opinion> opiniones = new ArrayList<Opinion>();
    	
    	 when(usuarioMock.getId()).thenReturn(1);
         when(usuarioMock.esExperto()).thenReturn(true);
         when(opinionMock.getUsuario()).thenReturn(usuarioMock);
         when(opinionMock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
         
         when(usuario2Mock.getId()).thenReturn(2);
         when(usuario2Mock.esExperto()).thenReturn(true);
         when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
         when(opinion2Mock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
         
         opiniones.add(opinionMock);
         
         //SIMULO UNA LISTA DE OPINIONES DE LA MUESTRA, DONDE ENTRE LAS OPINIONES ESTA opinionMock
         when(muestraMock.opinionesExpertos()).thenReturn(opiniones);

         when(muestraMock.getUsuario()).thenReturn(mock(Usuario.class));
         when(muestraMock.elUsuarioNoOpino(usuario2Mock)).thenReturn(true);

         estadoEnVerificacion.agregarOpinion(opinion2Mock);

         verify(muestraMock).agregarOpinionDe(opinion2Mock);
         
         verify(muestraMock).actualizarOpinion();

         verify(muestraMock).cambiarEstado(any(MuestraVerificada.class));
    }

}

