package ar.edu.unq.po2.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Muestra.OpinionImagen;
import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Sistema.Sistema;

import static org.mockito.Mockito.*;
import java.util.ArrayList;

class UsuarioTest {
	//SUT
	private Usuario juan;
	private Usuario carlos;
	//DOC
	private Sistema sistemaMock;
	private Ubicacion ubicacionMock;
	private Muestra muestraMock;
	private Opinion opinionMock;
	
	@BeforeEach
	void setUp() {
		juan = new Usuario(0, "juan200");
		carlos = new Usuario(2, "carlos200");
		sistemaMock = mock(Sistema.class);
		muestraMock = mock(Muestra.class);
	}


	@Test
	void test01UsuarioSubeUnaMuestra() {
		juan.subirMuestraA (muestraMock, sistemaMock);
		
		verify(sistemaMock).registrarMuestra(muestraMock);
	}
	//CREADOS SUBIR MUESTRA
	//CREADOS REGISTRAR MUESTRA
	
	@Test
	void test02UsuarioOpinaSobreUnaMuestra() {
		juan.subirMuestraA (muestraMock, sistemaMock);
		carlos.opinarSobreMuestra(muestraMock, OpinionImagen.VINCHUCA_INFESTANS);
		verify(muestraMock).agregarOpinion(any(Opinion.class));
	}
	//CREADOS OPINAR SOBRE MUESTRA Y AGREGAR OPINION
	
	@Test
	void test03unUsuarioNoPuedeOpinarSobreSuPropiaMuestra() {
		juan.subirMuestraA (muestraMock, sistemaMock);
		juan.opinarSobreMuestra(muestraMock, OpinionImagen.VINCHUCA_INFESTANS);
		assertThrows(RuntimeException.class, () -> {
	        juan.opinarSobreMuestra(muestraMock, OpinionImagen.VINCHUCA_INFESTANS);
	    });
	}
	
	@Test
	void test04unUsuarioNoPuedeOpinarSobreUnaMuestra2Veces() {
		//misma condicion que test 3
		juan.subirMuestraA (muestraMock, sistemaMock);
		carlos.opinarSobreMuestra(muestraMock, OpinionImagen.VINCHUCA_INFESTANS);
		carlos.opinarSobreMuestra(muestraMock, OpinionImagen.CHINCHE_FOLIADA);
		verify(muestraMock).agregarOpinion(any(Opinion.class));
	}

	

}
