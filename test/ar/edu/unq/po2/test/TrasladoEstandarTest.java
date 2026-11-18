package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.PlataformaTransporte;
import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.TrasladoEstandar;

class TrasladoEstandarTest {

    private static final int PASAJEROS = 3;
    private static final String DIRECCION = "Av. Siempre Viva 742";

    private PlataformaTransporte plataforma;
    private Reserva reserva;
    private TrasladoEstandar traslado;

    @BeforeEach
    void setUp() {
        plataforma = mock(PlataformaTransporte.class);
        reserva = mock(Reserva.class);
        traslado = new TrasladoEstandar(PASAJEROS, DIRECCION, plataforma);
    }

    @Test
    void calcularCostoTrasladoDevuelveLaEstimacionDeLaPlataforma() {
        when(plataforma.estimarTraslado(PASAJEROS, DIRECCION)).thenReturn(1500.0);

        assertEquals(1500.0, traslado.calcularCostoTraslado(reserva), 0.0001);
    }

    @Test
    void calcularCostoTrasladoLeConsultaALaPlataformaConPasajerosYDireccion() {
        traslado.calcularCostoTraslado(reserva);

        verify(plataforma).estimarTraslado(PASAJEROS, DIRECCION);
    }

    @Test
    void sinPlataformaCalcularCostoLanzaNullPointerException() {
        TrasladoEstandar sinPlataforma = new TrasladoEstandar(PASAJEROS, DIRECCION, null);

        try {
            sinPlataforma.calcularCostoTraslado(reserva);
            fail("Debería haber lanzado NullPointerException");
        } catch (NullPointerException e) {
     
        }
    }
}
