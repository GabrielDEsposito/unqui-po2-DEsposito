package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ItarjetaInternacional;
import ar.edu.unq.po2.integrador.TarjetaCreditoInternacional;

class TarjetaCreditoInternacionalTest {

    private static final int NUMERO = 123456789;
    private static final String CVV = "123";
    private static final String VENCIMIENTO = "12/30";

    private ItarjetaInternacional servicio;
    private TarjetaCreditoInternacional tarjeta;

    @BeforeEach
    void setUp() {
        servicio = mock(ItarjetaInternacional.class);
        tarjeta = new TarjetaCreditoInternacional(NUMERO, CVV, VENCIMIENTO, servicio);
    }

    @Test
    void validarDatosConDatosValidosNoLanzaExcepcion() {
        when(servicio.validarDatosTarjeta(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);

        try {
            tarjeta.ValidarDatos();
        } catch (IllegalStateException e) {
            fail("No debería lanzar excepción con datos válidos");
        }

        verify(servicio).validarDatosTarjeta(NUMERO, CVV, VENCIMIENTO);
    }

    @Test
    void validarDatosConDatosInvalidosLanzaIllegalStateException() {
        when(servicio.validarDatosTarjeta(NUMERO, CVV, VENCIMIENTO)).thenReturn(false);

        try {
            tarjeta.ValidarDatos();
            fail("Debería haber lanzado IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Datos de tarjeta inválidos", e.getMessage());
        }
    }
}