package ar.edu.unq.po2.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Muestra.OpinionImagen;
import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Sistema.GestorDeSistemas;
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
		assertEquals (false, usuarioInicial.esExperto);
		assertEquals(false, usuarioInicial.esEspecialista);
	}

	@Test
	void test01UsuarioSubeUnaMuestra() {
		juan.subirMuestraA (muestra1Mock, sistemaMock);		
		
		verify(sistemaMock).agregarMuestra(muestra1Mock);

	}
	//CREADOS SUBIR MUESTRA
	//CREADOS REGISTRAR MUESTRA
	
	@Test
	void test02UsuarioOpinaSobreUnaMuestra() {
		juan.subirMuestraA (muestra1Mock, sistemaMock);
		carlos.opinarSobreMuestra(muestra1Mock, OpinionImagen.VINCHUCA_INFESTANS);
		verify(muestra1Mock).agregarOpinion(any(Opinion.class));
	}
	//CREADOS OPINAR SOBRE MUESTRA Y AGREGAR OPINION
	//CREADOS OPINAR SOBRE MUESTRA Y AGREGAR OPINION
		 
	
	@Test
	void test03unUsuarioNoPuedeOpinarSobreSuPropiaMuestra() {
		 
		Sistema sistema = new Sistema();
		Opinion opinion2 = new Opinion(carlos, OpinionImagen.VINCHUCA_INFESTANS);
		Muestra muestra2 = new Muestra(ubicacionMock, opinion2);

		carlos.subirMuestraA (muestra2, sistema);
		assertThrows(RuntimeException.class, () -> {
		    carlos.opinarSobreMuestra(muestra2, OpinionImagen.CHINCHE_FOLIADA);
		});

		assertEquals(0, muestra2.cantidadDeOpiniones());
	}

	//PROBABLEMENTE SEA UN TEST PARA GESTOR DE SISTEMAS
	@Test
	void test05unUsuarioSeConvierteEnExperto() {
		GestorDeSistemas gestor = new GestorDeSistemas();
		// USUARIOS DEL SISTEMA MOCK = EN ESTE CASO SOLO JUAN
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(juan);
		when(sistemaMock.getUsuarios()).thenReturn(usuarios);
		
		// JUAN ES UN USUARIO BASICO
		assertFalse(juan.esExperto());
		
		// MUESTRAS PUBLICADAS POR JUAN (10 PARA SER EXPERTO)
		ArrayList<Muestra> muestrasMock = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Muestra muestraMock = mock(Muestra.class);
			when(muestraMock.getUsuario()).thenReturn(juan);
			
			// POR CADA MUESTRA HAY 2 OPINIONES DE JUAN (20 PARA SER EXPERTO)
			ArrayList<Opinion> opinionesMock = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
				Opinion opinionMock = mock(Opinion.class);
				when(opinionMock.getUsuario()).thenReturn(juan);
				opinionesMock.add(opinionMock);
			}
			when(muestraMock.getOpiniones()).thenReturn(opinionesMock);
			
			muestrasMock.add(muestraMock);
		}
		when(sistemaMock.getMuestras()).thenReturn(muestrasMock);
		gestor.ActualizarNivelesDeUsuarioEn(sistemaMock);
		
		// JUAN AHORA ES UN USUARIO EXPERTO
		assertTrue(juan.esExperto());
	}
	
	@Test
	void test06unUsuarioEspecialistaSiempreEsExperto() {
		GestorDeSistemas gestor = new GestorDeSistemas();
		assertTrue(marcos.esEspecialista);
		assertTrue(marcos.esExperto);
		assertFalse(juan.esEspecialista);
		
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(marcos);
		when(sistemaMock.getUsuarios()).thenReturn(usuarios);
		
		ArrayList<Opinion> opinionesMock = new ArrayList<>();
		opinionesMock.add(opinionMock);
		when(muestra1Mock.getOpiniones()).thenReturn(opinionesMock);
		
		gestor.ActualizarNivelesDeUsuarioEn(sistemaMock);
		
		assertTrue(marcos.esExperto);
		
	}
	
	@Test
	void test07unUsuarioExpertoSeConvierteEnBasico() {
		
		GestorDeSistemas gestor = new GestorDeSistemas();
		juan.promoverAExperto();
		assertFalse(juan.esEspecialista);
		assertTrue(juan.esExperto);
		
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(juan);
		when(sistemaMock.getUsuarios()).thenReturn(usuarios);
		
		ArrayList<Opinion> opinionesMock = new ArrayList<>();
		opinionesMock.add(opinionMock);
		when(muestra1Mock.getOpiniones()).thenReturn(opinionesMock);
		
		gestor.ActualizarNivelesDeUsuarioEn(sistemaMock);
		
		assertFalse(juan.esExperto);
	}

}