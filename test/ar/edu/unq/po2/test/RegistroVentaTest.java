package ar.edu.unq.po2.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.RegistroVenta;

class RegistroVentaTest {

    private ItemCatalogo item;
    private RegistroVenta registro;

    @BeforeEach
    void setUp() {
        item = mock(ItemCatalogo.class);
        registro = new RegistroVenta(item);
    }

    @Test
    void alCrearseGuardaElItemYNoTieneUnidades() {
        assertSame(item, registro.getItem());
        assertEquals(0, registro.getUnidades());
    }

    @Test
    void sinVentasElPrecioPromedioNoEstaDefinido() {
        
        assertTrue(Double.isNaN(registro.getPrecioPromedio()));
    }

    @Test
    void agregarUnaVentaIncrementaLasUnidades() {
        registro.agregarVenta(100);

        assertEquals(1, registro.getUnidades());
    }

    @Test
    void conUnaVentaElPromedioEsElPrecioDeEsaVenta() {
        registro.agregarVenta(150.5);

        assertEquals(150.5, registro.getPrecioPromedio(), 0.0001);
    }

    @Test
    void agregarVariasVentasAcumulaUnidades() {
        registro.agregarVenta(100);
        registro.agregarVenta(200);
        registro.agregarVenta(300);

        assertEquals(3, registro.getUnidades());
    }

    @Test
    void conVariasVentasElPromedioEsRecaudadoSobreUnidades() {
        registro.agregarVenta(100);
        registro.agregarVenta(200);
        registro.agregarVenta(300);

        assertEquals(200, registro.getPrecioPromedio(), 0.0001);
    }

    @Test
    void elPromedioSeActualizaConCadaVenta() {
        registro.agregarVenta(100);
        assertEquals(100, registro.getPrecioPromedio(), 0.0001);

        registro.agregarVenta(300);
        assertEquals(200, registro.getPrecioPromedio(), 0.0001);
    }

    @Test
    void elPromedioConDecimalesEsCorrecto() {
        registro.agregarVenta(10);
        registro.agregarVenta(15);

        assertEquals(12.5, registro.getPrecioPromedio(), 0.0001);
    }
}
