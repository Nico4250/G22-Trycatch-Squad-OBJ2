package ar.edu.unq.po2.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Muestra.OpinionImagen;
import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Sistema.GestorDeSistemas;
import ar.edu.unq.po2.Sistema.Sistema;

import static org.mockito.Mockito.*;
import java.util.ArrayList;

class UsuarioTest {
	//SUT
	private Usuario juan;
	private Usuario carlos;
	//DOC
	private Sistema sistema;
	private Ubicacion ubicacionMuestra;
	private Muestra muestra1;
	private Opinion opinion;
	private GestorDeSistemas gestor;
	
	@BeforeEach
	void setUp() {
		juan = new Usuario(0,"juan200");
		carlos = new Usuario(1,"carlos200");
		sistema = new Sistema();
		opinion = new Opinion(juan, OpinionImagen.VINCHUCA_INFESTANS);
		muestra1 = new Muestra(ubicacionMuestra, opinion);
		sistema.registrarUsuario(juan);
		sistema.registrarUsuario(carlos);
		
	}


	@Test
	void test01UsuarioSubeUnaMuestra() {
		juan.subirMuestraA (muestra1, sistema);
		
		//verify(sistemaMock).agregarMuestra(muestra1);
		assertTrue(sistema.contieneMuestra(muestra1));
	}
	//CREADOS SUBIR MUESTRA
	//CREADOS REGISTRAR MUESTRA
	
	@Test
	void test02UsuarioOpinaSobreUnaMuestra() {
		juan.subirMuestraA (muestra1, sistema);
		carlos.opinarSobreMuestra(muestra1, OpinionImagen.IMAGEN_POCO_CLARA);
		carlos.opinarSobreMuestra(muestra1, OpinionImagen.IMAGEN_POCO_CLARA);
		//verify(muestra1).agregarOpinion(any(Opinion.class));
		
		assertEquals(2, muestra1.cantidadDeOpiniones());
	}
	//CREADOS OPINAR SOBRE MUESTRA Y AGREGAR OPINION
	
//FALLA ALGO EN COMPROBACION DE MUESTRAS	
	@Test
	void test03unUsuarioNoPuedeOpinarSobreUnaMuestra2Veces() {
		//misma condicion que test 3
		juan.subirMuestraA (muestra1, sistema);
		carlos.opinarSobreMuestra(muestra1, OpinionImagen.VINCHUCA_INFESTANS);
		carlos.opinarSobreMuestra(muestra1, OpinionImagen.CHINCHE_FOLIADA);
		//verify(muestra1).agregarOpinion(any(Opinion.class));
		assertEquals(1, muestra1.cantidadDeOpiniones());
	}

	
	@Test
	void test04unUsuarioSeConvierteEnExperto() {
		//misma condicion que test 3
		assertFalse(juan.esExperto());
		gestor.ActualizarNivelesDeUsuarioEn(sistema);
		juan.subirMuestraA (muestra1, sistema);
		carlos.opinarSobreMuestra(muestra1, OpinionImagen.VINCHUCA_INFESTANS);
		carlos.opinarSobreMuestra(muestra1, OpinionImagen.CHINCHE_FOLIADA);
		//verify(muestra1).agregarOpinion(any(Opinion.class));
		assertEquals(0, muestra1.cantidadDeOpiniones());
	}
	
	


}