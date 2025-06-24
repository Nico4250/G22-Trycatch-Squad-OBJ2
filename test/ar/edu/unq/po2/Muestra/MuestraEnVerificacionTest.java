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
    	estadoEnVerificacion = new MuestraEnVerificacion(); 
    }
    
    @Test
    void test01UnaMuestraEnVerificacionNoEsVerificada() {
    	
    	assertFalse(estadoEnVerificacion.esVerificada());
    }
    
    @Test
    void test02UsuariosBasicosNoPuedenOpinarEnEsteEstado() {
    	
    	when(opinionMock.getUsuario()).thenReturn(usuarioMock);
    	
    	assertFalse(estadoEnVerificacion.puedeOpinar(muestraMock, usuarioMock));
    	

    }
    
    @Test
    void test03UsuariosExpertosPuedenOpinarEnEsteEstado() {
    	
    	when(usuarioMock.getId()).thenReturn(1);
    	when(muestraMock.getUsuario()).thenReturn(usuarioMock);
    	
    	when(usuario2Mock.getId()).thenReturn(2);
    	when(usuario2Mock.esExperto()).thenReturn(true);
    	when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);

    	when(muestraMock.elUsuarioNoOpino(usuario2Mock)).thenReturn(true);
    	when(muestraMock.puedeOpinar(usuario2Mock)).thenReturn(true);
    	when(muestraMock.opinionesExpertos()).thenReturn(List.of());

    	
    	assertTrue(estadoEnVerificacion.puedeOpinar(muestraMock, usuario2Mock));
            
        assertDoesNotThrow(() -> estadoEnVerificacion.agregarOpinion(muestraMock, opinion2Mock));

        verify(muestraMock).agregarOpinionDe(opinion2Mock);
            
        verify(muestraMock).actualizarOpinion();
       
    }
    
    @Test
    void test04ActualizarOpinionCambiaLaOpinionActual() {

        when(opinionMock.getOpinion()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
        when(muestraMock.opinionesExpertos()).thenReturn(List.of(opinionMock));

        estadoEnVerificacion.actualizarOpinion(muestraMock);

        verify(muestraMock).actualizarOpinionActual(any());
    }
    


    @Test
    void test05UnSegundoExpertoConOpiniónDistintaNoVerificaLaMuestra() {
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
        when(muestraMock.puedeOpinar(usuario2Mock)).thenReturn(true);

        estadoEnVerificacion.agregarOpinion(muestraMock, opinion2Mock);

        verify(muestraMock).agregarOpinionDe(opinion2Mock);
        
        verify(muestraMock).actualizarOpinion();

        verify(muestraMock, never()).cambiarEstado(any(MuestraVerificada.class));
    }

 
    @Test
    void test06UnSegundoExpertoConMismaOpinionVerificaLaMuestra() {
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
         when(muestraMock.puedeOpinar(usuario2Mock)).thenReturn(true);

         estadoEnVerificacion.agregarOpinion(muestraMock, opinion2Mock);

         verify(muestraMock).agregarOpinionDe(opinion2Mock);
         
         verify(muestraMock).actualizarOpinion();

         verify(muestraMock).cambiarEstado(any(MuestraVerificada.class));
    }

}

