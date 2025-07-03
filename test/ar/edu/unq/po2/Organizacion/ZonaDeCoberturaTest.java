package ar.edu.unq.po2.Organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ar.edu.unq.po2.Muestra.Muestra;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZonaDeCoberturaTest {

    private ZonaDeCobertura zona;
    private Ubicacion epicentro;
    private Muestra muestra1, muestra2;
    private IObserver obs1, obs2;

    @BeforeEach
    public void setUp() {
        epicentro = mock(Ubicacion.class);
        zona = new ZonaDeCobertura("Zona Sur", 10, epicentro);
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        obs1 = mock(IObserver.class);
        obs2 = mock(IObserver.class);
    }

    @Test
    public void testGettersYSetters() {
        assertEquals("Zona Sur", zona.getNombre());
        assertEquals(epicentro, zona.getEpicentro());
        assertEquals(10, zona.getRadio());

        zona.setNombre("Zona Norte");
        zona.setRadio(20);
        Ubicacion nuevaUbicacion = mock(Ubicacion.class);
        zona.setEpicentro(nuevaUbicacion);

        assertEquals("Zona Norte", zona.getNombre());
        assertEquals(20, zona.getRadio());
        assertEquals(nuevaUbicacion, zona.getEpicentro());
    }

    @Test
    public void testSuscripciones() {
        zona.suscribirParaCarga(obs1);
        zona.suscribirParaValidacion(obs2);

        assertTrue(zona.getSubscritosACarga().contains(obs1));
        assertTrue(zona.getSubscritosAValidacion().contains(obs2));

        zona.desuscribirParaCarga(obs1);
        zona.desuscribirParaValidacion(obs2);

        assertFalse(zona.getSubscritosACarga().contains(obs1));
        assertFalse(zona.getSubscritosAValidacion().contains(obs2));
    }

    @Test
    public void testNotificaACarga() {
        zona.suscribirParaCarga(obs1);
        zona.suscribirParaCarga(obs2);
        zona.notificarCargaMuestra(muestra1);

        verify(obs1).actualizarPorCargaNueva(zona, muestra1);
        verify(obs2).actualizarPorCargaNueva(zona, muestra1);
    }

    @Test
    public void testNotificarValidacion() {
        zona.suscribirParaValidacion(obs1);
        zona.notificarValidacionMuestra(muestra2);
        verify(obs1).actualizarPorValidacion(zona, muestra2);
    }

    @Test
    public void testZonasSolapadas() {
        Ubicacion ubicacion1 = mock(Ubicacion.class);
        Ubicacion ubicacion2 = mock(Ubicacion.class);
        ZonaDeCobertura zona1 = new ZonaDeCobertura("Z1", 5, ubicacion1);
        ZonaDeCobertura zona2 = new ZonaDeCobertura("Z2", 5, ubicacion2);

        when(epicentro.distanciaEntre(ubicacion1)).thenReturn(20.0); // no solapada
        when(epicentro.distanciaEntre(ubicacion2)).thenReturn(9.0);  // sí solapada

        ArrayList<ZonaDeCobertura> zonas = new ArrayList<>(Arrays.asList(zona1, zona2, zona));
        ArrayList<ZonaDeCobertura> solapadas = zona.zonasSolapadas(zonas);

        assertTrue(solapadas.contains(zona2));
        assertFalse(solapadas.contains(zona1));
        assertFalse(solapadas.contains(zona)); // no se incluye a sí misma
    }
}