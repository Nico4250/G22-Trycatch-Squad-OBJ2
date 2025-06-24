package ar.edu.unq.po2.Organizacion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unq.po2.Muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OrganizacionTest {

	private Organizacion organizacion;
    private FuncionalidadExterna mockCarga;
    private FuncionalidadExterna mockValidacion;
    private Ubicacion mockUbicacion;
    private TipoDeOrganizacion mockTipo;
    private ZonaDeCobertura mockZona;
    private Muestra mockMuestra;

    @BeforeEach
    public void setUp() {
        mockCarga = mock(FuncionalidadExterna.class);
        mockValidacion = mock(FuncionalidadExterna.class);
        mockUbicacion = mock(Ubicacion.class);
        mockTipo = mock(TipoDeOrganizacion.class);
        mockZona = mock(ZonaDeCobertura.class);
        mockMuestra = mock(Muestra.class);

        organizacion = new Organizacion("EcoClub", mockUbicacion, mockTipo, mockCarga, mockValidacion);
    }

    @Test
    public void testGettersYSetters() {
        assertEquals("EcoClub", organizacion.getNombre());
        assertEquals(mockUbicacion, organizacion.getUbicacion());
        assertEquals(mockTipo, organizacion.getTipoOrganizacion());
        assertEquals(mockCarga, organizacion.getFuncionalidadCarga());
        assertEquals(mockValidacion, organizacion.getFuncionalidadValidacion());

        Ubicacion nuevaUbicacion = mock(Ubicacion.class);
        organizacion.setNombre("BioRed");
        organizacion.setUbicacion(nuevaUbicacion);
        organizacion.setFuncionalidadCarga(null);
        organizacion.setFuncionalidadValidacion(null);

        assertEquals("BioRed", organizacion.getNombre());
        assertEquals(nuevaUbicacion, organizacion.getUbicacion());
        assertNull(organizacion.getFuncionalidadCarga());
        assertNull(organizacion.getFuncionalidadValidacion());
    }

    @Test
    public void testActualizarPorCargaNueva() {
        organizacion.actualizarPorCargaNueva(mockZona, mockMuestra);
        verify(mockCarga).nuevoEvento(organizacion, mockZona, mockMuestra);
    }

    @Test
    public void testActualizarPorValidacion() {
        organizacion.actualizarPorValidacion(mockZona, mockMuestra);
        verify(mockValidacion).nuevoEvento(organizacion, mockZona, mockMuestra);
    }
}