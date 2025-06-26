package ar.edu.unq.po2.Sistema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Usuario.Usuario;

class gestorDeUsuariosTest {
	
	private GestorDeUsuarios gestor;
	private Sistema sistemaMock;
	private Usuario usuarioMock; // Ahora es un mock de Usuario
    
	@BeforeEach
    void setUp() {
		sistemaMock = mock(Sistema.class); 
		gestor = new GestorDeUsuarios(sistemaMock);
		usuarioMock = mock(Usuario.class); // Creamos un mock de Usuario
	     
		
		// Cuando se llama a getId() en el mock, devuelve un valor fijo
	        when(usuarioMock.getId()).thenReturn(1);
	        // Por defecto, un mock de Usuario no es experto ni especialista
	        when(usuarioMock.esExperto()).thenReturn(false);
	        when(usuarioMock.esEspecialista()).thenReturn(false);
	    }
	

	@Test
	void test01UsuarioSeConvierteEnExpertoCuandoCumpleCondiciones() {
		
		when(usuarioMock.getId()).thenReturn(1);
		when(usuarioMock.esExperto()).thenReturn(false);
		assertFalse(usuarioMock.esExperto());

		//METEMOS AL USUARIO AL SISTEMA PARA PODER COMPARARLO
	    ArrayList<Usuario> usuariosEnSistema = new ArrayList<>();
	    usuariosEnSistema.add(usuarioMock);
	    when(sistemaMock.getUsuarios()).thenReturn(usuariosEnSistema);
   
	    //CREACION DE MUESTRAS (10)
	    ArrayList<Muestra> muestrasEnviadasPorUsuario = new ArrayList<>();
	    for (int i = 0; i < 10; i++) { // 10 muestras
	        Muestra mockMuestra = mock(Muestra.class);
	        //CADA MUESTRA SERA DE USUARIOMOCK (NECESITAMOS 10 MUESTRAS)
	        when(mockMuestra.getUsuario()).thenReturn(usuarioMock);

	        //CREACION DE OPINIONES (20)
	        ArrayList<Opinion> opinionesDelUsuarioEnMuestraActual = new ArrayList<>();
	        for (int j = 0; j < 2; j++) {
	            Opinion mockOpinion = mock(Opinion.class);
	            //CADA OPINION SERA DE USUARIOMOCK (2 OPINIONES POR MUESTRA = 20 OPINIONES)
	            when(mockOpinion.getUsuario()).thenReturn(usuarioMock);
	            opinionesDelUsuarioEnMuestraActual.add(mockOpinion);
	        }
	        //AGREGAMOS AMBAS OPINIONES A LA MUESTRA ACTUAL
	        when(mockMuestra.getOpiniones()).thenReturn(opinionesDelUsuarioEnMuestraActual);
	        
	        //LA MUESTRA ACTUAL SE AGREGA A LA LISTA DE MUESTRAS (SE HACE HASTA TENER 10 MUESTRAS)
	        muestrasEnviadasPorUsuario.add(mockMuestra);
	    }
	    //SISTEMAMOCK ALBERGARA LAS 10 MUESTRAS 
	    when(sistemaMock.getMuestras()).thenReturn(muestrasEnviadasPorUsuario);

	    gestor.ActualizarNivelesDeUsuario();

	    //ES LLAMADO EL METODO "PROMOVER A EXPERTO"
	    verify(usuarioMock, times(1)).promoverAExperto();

	}

	@Test
	void test02UsuarioExpertoSeConvierteEnBasicoCuandoNoCumpleCondiciones() {
		
		when(usuarioMock.getId()).thenReturn(1);
		when(usuarioMock.esExperto()).thenReturn(true);
		assertTrue(usuarioMock.esExperto());

		//METEMOS AL USUARIO AL SISTEMA PARA PODER COMPARARLO
	    ArrayList<Usuario> usuariosEnSistema = new ArrayList<>();
	    usuariosEnSistema.add(usuarioMock);
	    when(sistemaMock.getUsuarios()).thenReturn(usuariosEnSistema);
   
	    //EL USUARIO NO TIENE NI MUESTRAS NI OPINIONES SUBIDAS

	    gestor.ActualizarNivelesDeUsuario();

	    //ES LLAMADO EL METODO "DEGRADAR A BASICO"
	    verify(usuarioMock, times(1)).degradarABasico();

	}
	
	@Test
	void test03UsuarioEspecialistaSiempreEsExpertoAPesarDeNoCumplirCondiciones() {
		
		when(usuarioMock.getId()).thenReturn(1);
		//EL USUARIO ES ESPECIALISTA
		when(usuarioMock.esEspecialista()).thenReturn(true);
		when(usuarioMock.esExperto()).thenReturn(true);
		assertTrue(usuarioMock.esExperto());

		//METEMOS AL USUARIO AL SISTEMA PARA PODER COMPARARLO
	    ArrayList<Usuario> usuariosEnSistema = new ArrayList<>();
	    usuariosEnSistema.add(usuarioMock);
	    when(sistemaMock.getUsuarios()).thenReturn(usuariosEnSistema);
   
	    //EL USUARIO NO TIENE NI MUESTRAS NI OPINIONES SUBIDAS

	    gestor.ActualizarNivelesDeUsuario();

	    //ES LLAMADO EL METODO "PROMOVER A EXPERTO"
	    verify(usuarioMock, times(1)).promoverAExperto();

	}
	
}
