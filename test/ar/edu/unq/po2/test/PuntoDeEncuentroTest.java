package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PuntoDeEncuentroTest {

    @Test
    void conCuposDisponiblesVerificarDisponibilidadEsTrue() {
        PuntoDeEncuentro punto = new PuntoDeEncuentro(3);
        assertTrue(punto.verificarDisponibilidad());
    }

    @Test
    void sinCuposVerificarDisponibilidadEsFalse() {
        PuntoDeEncuentro punto = new PuntoDeEncuentro(0);
        assertFalse(punto.verificarDisponibilidad());
    }

    @Test
    void elCostoDeTrasladoEnPuntoDeEncuentroEsCero() {
        PuntoDeEncuentro punto = new PuntoDeEncuentro(1);
        Reserva reserva = org.mockito.Mockito.mock(Reserva.class);
        assertEquals(0, punto.calcularCostoTraslado(reserva));
    }
}