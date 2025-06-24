package ar.edu.unq.po2.Organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Sistema.Sistema;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadorGeodesicoTest {

    private Sistema mockSistema;
    private CalculadorGeodesico calculador;

    @BeforeEach
    public void setUp() {
        mockSistema = mock(Sistema.class);
        calculador = new CalculadorGeodesico(mockSistema);
    }

    @Test
    public void testCalcularDistanciaEntreUbicaciones() {
        Ubicacion u1 = mock(Ubicacion.class);
        Ubicacion u2 = mock(Ubicacion.class);

        when(u1.getLatitud()).thenReturn(0.0);
        when(u1.getLongitud()).thenReturn(0.0);
        when(u2.getLatitud()).thenReturn(0.0);
        when(u2.getLongitud()).thenReturn(1.0);

        // Aproximadamente 111 km entre longitud 0 y 1 en el ecuador
        double distancia = calculador.calcularDistancia(u1, u2);

        assertTrue(distancia > 110 && distancia < 112, "La distancia entre 0° y 1° debe estar alrededor de 111km");
    }

    @Test
    public void testFiltrarUbicaciones() {
        Ubicacion origen = mock(Ubicacion.class);
        Ubicacion cercana = mock(Ubicacion.class);
        Ubicacion lejana = mock(Ubicacion.class);

        // Simula 10 km y 1000 km de distancia
        CalculadorGeodesico spyCalculador = spy(calculador);
        doReturn(10.0).when(spyCalculador).calcularDistancia(origen, cercana);
        doReturn(1000.0).when(spyCalculador).calcularDistancia(origen, lejana);

        List<Ubicacion> resultado = spyCalculador.filtrarUbicaciones(origen, List.of(cercana, lejana), 50);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(cercana));
        assertFalse(resultado.contains(lejana));
    }

    @Test
    public void testMuestrasAMenosDe() {
        Muestra origen = mock(Muestra.class);
        Muestra m1 = mock(Muestra.class);
        Muestra m2 = mock(Muestra.class);
        Ubicacion ubicOrigen = mock(Ubicacion.class);
        Ubicacion ubic1 = mock(Ubicacion.class);
        Ubicacion ubic2 = mock(Ubicacion.class);

        when(origen.getUbicacion()).thenReturn(ubicOrigen);
        when(m1.getUbicacion()).thenReturn(ubic1);
        when(m2.getUbicacion()).thenReturn(ubic2);
        when(mockSistema.getMuestras()).thenReturn(new ArrayList<>(List.of(origen, m1, m2)));

        CalculadorGeodesico spyCalculador = spy(calculador);
        doReturn(15.0).when(spyCalculador).calcularDistancia(ubicOrigen, ubic1);
        doReturn(100.0).when(spyCalculador).calcularDistancia(ubicOrigen, ubic2);

        List<Muestra> resultado = spyCalculador.muestrasAMenosDe(origen, 50);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(m1));
        assertFalse(resultado.contains(m2));
        assertFalse(resultado.contains(origen)); // no se incluye a sí misma
    }
}