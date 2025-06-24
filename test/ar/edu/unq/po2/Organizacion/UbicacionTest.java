package ar.edu.unq.po2.Organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Sistema.Sistema;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UbicacionTest {

    private Ubicacion ubicacion;
    private CalculadorGeodesico mockCalculador;
    private Sistema mockSistema;

    @BeforeEach
    public void setUp() {
        mockCalculador = mock(CalculadorGeodesico.class);
        mockSistema = mock(Sistema.class);
        ubicacion = new Ubicacion(10.0, 20.0, mockCalculador, mockSistema);
    }

    @Test
    public void testGettersYSetters() {
        assertEquals(10.0, ubicacion.getLatitud());
        assertEquals(20.0, ubicacion.getLongitud());

        ubicacion.setLatitud(15.0);
        ubicacion.setLongitud(25.0);

        assertEquals(15.0, ubicacion.getLatitud());
        assertEquals(25.0, ubicacion.getLongitud());
    }

    @Test
    public void testDistanciaEntreUbicaciones() {
        Ubicacion otraUbicacion = mock(Ubicacion.class);
        when(mockCalculador.calcularDistancia(ubicacion, otraUbicacion)).thenReturn(12.5);

        double resultado = ubicacion.distanciaEntre(otraUbicacion);

        assertEquals(12.5, resultado);
        verify(mockCalculador).calcularDistancia(ubicacion, otraUbicacion);
    }

    @Test
    public void testUbicacionesAMenosDe() {
        Ubicacion u1 = mock(Ubicacion.class);
        Ubicacion u2 = mock(Ubicacion.class);
        List<Ubicacion> todas = Arrays.asList(u1, u2);

        when(mockCalculador.filtrarUbicaciones(ubicacion, todas, 5)).thenReturn(List.of(u1));

        List<Ubicacion> resultado = ubicacion.ubicacionesAMenosDe(todas, 5);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(u1));
        verify(mockCalculador).filtrarUbicaciones(ubicacion, todas, 5);
    }

    @Test
    public void testMuestrasAMenosDe() {
        Muestra muestra = mock(Muestra.class);
        Muestra m1 = mock(Muestra.class);
        List<Muestra> muestrasCercanas = List.of(m1);

        when(mockCalculador.muestrasAMenosDe(muestra, 10)).thenReturn(muestrasCercanas);

        List<Muestra> resultado = ubicacion.muestrasAMenosDe(muestra, 10);

        assertEquals(muestrasCercanas, resultado);
        verify(mockCalculador).muestrasAMenosDe(muestra, 10);
    }
}