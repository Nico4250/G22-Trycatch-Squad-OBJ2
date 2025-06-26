package ar.edu.unq.po2.Muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Usuario.Usuario;

class MuestraTest {
	
	//SUT
	private Muestra unaMuestra;
	//DOC
	private Opinion opinionMock;
	private Opinion opinion2Mock;
	private Opinion opinion3Mock;
	private Ubicacion ubicacionMock;
	private Usuario usuarioMock;
	private Usuario usuario2Mock;
	private Usuario usuario3Mock;
	
	@BeforeEach
	void setUp() throws Exception {
		opinionMock = mock(Opinion.class);
		opinion2Mock = mock(Opinion.class);
		opinion3Mock = mock(Opinion.class);
		ubicacionMock = mock(Ubicacion.class);
		usuarioMock = mock(Usuario.class);	
		usuario2Mock = mock(Usuario.class);
		usuario3Mock = mock(Usuario.class);
	}
	
	@Test 
	void test00SeCreaUnaMuestraConTodosSusDatos() {
		
	    when(opinionMock.getUsuario()).thenReturn(usuarioMock);
	    when(opinionMock.getOpinion()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
	    
		unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen01");
		
		assertEquals(ubicacionMock, unaMuestra.getUbicacion());
		assertEquals(usuarioMock, unaMuestra.getUsuario());
		assertEquals(LocalDate.now(), unaMuestra.getFechaCreacion());
		assertEquals(OpinionImagen.CHINCHE_FOLIADA, unaMuestra.getTipoInsecto());
		assertEquals(0,unaMuestra.getOpiniones().size());
		assertEquals("imagen01.JPG", unaMuestra.getFoto());
	}

	    @Test
	    void test01UnaMuestraSeCreaComoNoVerificada() { 
			unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen01");
			 
			assertEquals(MuestraNoVerificada.class, unaMuestra.getEstadoMuestra().getClass());
	    }

	    @Test
	    void test02UnaMuestraInicialmenteTieneLaOpinionDelUsuarioQueLaSubio() {
	    	when(usuarioMock.getId()).thenReturn(1);
		    when(opinionMock.getUsuario()).thenReturn(usuarioMock);
		    when(opinionMock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_SORDIDA);
		    
	    	unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen01");
	    	
	    	 assertEquals(OpinionImagen.VINCHUCA_SORDIDA, unaMuestra.getTipoInsecto());
	    	
	    }
	    
		@Test
		void test03LaMuestraNoPuedeSerOpinadaPorQuienLaSubio() {
			when(usuarioMock.getId()).thenReturn(1);
			when(opinionMock.getUsuario()).thenReturn(usuarioMock);
			
			unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen02");
			
			when(opinion2Mock.getUsuario()).thenReturn(usuarioMock);
			
			
			
			assertThrows(RuntimeException.class, () -> {
				unaMuestra.agregarOpinion(opinion2Mock);
			   });
		}
		 
		@Test
		void test04LaMuestraNoPuedeSerOpinada2VecesPorElMismoUsuario() {
			
			when(usuarioMock.getId()).thenReturn(1);
			when(opinionMock.getUsuario()).thenReturn(usuarioMock);
			
			unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen03");
			
			when(usuario2Mock.getId()).thenReturn(2);
			when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
			when(opinion3Mock.getUsuario()).thenReturn(usuario2Mock);
			
			unaMuestra.agregarOpinion(opinion2Mock);
			
			assertEquals(1, unaMuestra.getOpiniones().size());

			assertThrows(RuntimeException.class, () -> {
				unaMuestra.agregarOpinion(opinion3Mock);
			   });
		}

	    @Test
	    void test05UnaMuestraEsDelTipoMasVotado() { 
	    	when(usuarioMock.getId()).thenReturn(1);
		    when(opinionMock.getUsuario()).thenReturn(usuarioMock);
		    when(opinionMock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_INFESTANS);
		    
	    	unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen04");
	    	
	    	when(usuario2Mock.getId()).thenReturn(2);
		    when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
		    when(opinion2Mock.getOpinion()).thenReturn(OpinionImagen.IMAGEN_POCO_CLARA);
		    
	    	when(usuario3Mock.getId()).thenReturn(3);
		    when(opinion3Mock.getUsuario()).thenReturn(usuario3Mock);
		    when(opinion3Mock.getOpinion()).thenReturn(OpinionImagen.IMAGEN_POCO_CLARA);
		    
		    unaMuestra.agregarOpinion(opinion2Mock);
		    unaMuestra.agregarOpinion(opinion3Mock);
		    
		    assertEquals(OpinionImagen.IMAGEN_POCO_CLARA, unaMuestra.getTipoInsecto());
	    }

	    @Test
	    void test06TipoInsectoEsNoDefinidoEnCasoDeEmpate() {
	    	when(usuarioMock.getId()).thenReturn(1);
			when(opinionMock.getUsuario()).thenReturn(usuarioMock);
			when(opinionMock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
			    
		    unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen05");
		    	
		    when(usuario2Mock.getId()).thenReturn(2);
		    when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
			when(opinion2Mock.getOpinion()).thenReturn(OpinionImagen.PHTIA_CHINCHE);
				 
			when(usuario3Mock.getId()).thenReturn(3);
			when(opinion3Mock.getUsuario()).thenReturn(usuario3Mock);
			when(opinion3Mock.getOpinion()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
				 
			unaMuestra.agregarOpinion(opinion2Mock);
			unaMuestra.agregarOpinion(opinion3Mock);
				 
			assertEquals(OpinionImagen.NO_DEFINIDA, unaMuestra.getTipoInsecto());
	    }
	    
	    @Test
	    void test07MuestraSeparaOpinionesGeneralesDeOpinionesDeExpertos() {
	    	unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen06");
	    	
		    when(usuario2Mock.getId()).thenReturn(2);
		    when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
			when(opinion2Mock.getOpinion()).thenReturn(OpinionImagen.PHTIA_CHINCHE);
				 
			when(usuario3Mock.getId()).thenReturn(3);
			when(usuario3Mock.esExperto()).thenReturn(true);
			when(opinion3Mock.getUsuario()).thenReturn(usuario3Mock);
			when(opinion3Mock.getOpinion()).thenReturn(OpinionImagen.CHINCHE_FOLIADA);
			
			unaMuestra.agregarOpinion(opinion2Mock);
			unaMuestra.agregarOpinion(opinion3Mock);
			
			assertEquals(2, unaMuestra.getOpiniones().size());
			assertEquals(1, unaMuestra.opinionesExpertos().size());		
			assertNotEquals(unaMuestra.getOpiniones().size(), unaMuestra.opinionesExpertos().size());
	    }
	    
	    @Test 
	    void test08MuestraDelegaElPermisoParaOpinarAlEstado() {
	    	unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen07");
	    	
	    	//HARDCODEAREMOS UN ESTADO PARA COMPROBAR SI SE LLAMA A SUS METODOS
	    	IEstadoMuestra estadoMock = mock(IEstadoMuestra.class);
	    	unaMuestra.cambiarEstado(estadoMock); 
	    	
	        when(estadoMock.puedeOpinar(unaMuestra, usuarioMock)).thenReturn(true);
	    	
	    	assertTrue(unaMuestra.puedeOpinar(usuarioMock));
	    	
	    	verify(estadoMock).puedeOpinar(unaMuestra, usuarioMock); 	
	    }
	    
	    @Test
	    void test09UnaMuestraDelegaElCambioDeEstadoASuEstadoActual() {
			when(usuarioMock.getId()).thenReturn(1);
			when(opinionMock.getUsuario()).thenReturn(usuarioMock);
			unaMuestra = new Muestra(ubicacionMock, opinionMock, "imagen08");
			
			//LA MUESTRA EN ESTA INSTANCIA NO ESTABA VERIFICADA
			assertEquals(MuestraNoVerificada.class, unaMuestra.getEstadoMuestra().getClass());
			
			when(usuario2Mock.getId()).thenReturn(2);
			when (usuario2Mock.esExperto()).thenReturn(true);
			when(opinion2Mock.getUsuario()).thenReturn(usuario2Mock);
			when(opinion2Mock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
			
			//LA OPINION DE UN EXPERTO CAMBIA SU ESTADO A MUESTRAENVERIFICACION
			unaMuestra.agregarOpinion(opinion2Mock);
			
			//LA MUESTRA EN ESTA INSTANCIA ESTABA EN PROCESO DE VERIFICACION
			assertEquals(MuestraEnVerificacion.class, unaMuestra.getEstadoMuestra().getClass());
			
			when(usuario3Mock.getId()).thenReturn(3);
			when (usuario3Mock.esExperto()).thenReturn(true);
			when(opinion3Mock.getUsuario()).thenReturn(usuario3Mock);
			when(opinion3Mock.getOpinion()).thenReturn(OpinionImagen.VINCHUCA_GUASAYANA);
			when (usuario3Mock.esExperto()).thenReturn(true);
			
			//LA OPINION DE 2 EXPERTOS QUE COINCIDEN CAMBIA SU ESTADO A MUESTRAVERIFICADA
			assertTrue(unaMuestra.puedeOpinar(usuario3Mock));
			unaMuestra.agregarOpinion(opinion3Mock);
			
			//LA MUESTRA PASA A ESTAR VERIFICADA
			assertTrue(unaMuestra.esVerificada());
			assertEquals(MuestraVerificada.class, unaMuestra.getEstadoMuestra().getClass());
			
		} 
	    
	}