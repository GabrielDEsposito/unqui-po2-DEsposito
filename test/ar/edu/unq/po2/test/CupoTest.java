package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Cupo;
import ar.edu.unq.po2.integrador.CupoInsuficienteException;

public class CupoTest {
    @Test
    void cupoNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Cupo(-1));
    }

    @Test
    void reservarDisminuyeLosDisponibles() {
        Cupo c = new Cupo(5);
        c.reservar(2);

        assertEquals(3, c.disponibles());
        assertEquals(2, c.getReservados());
        assertEquals(5, c.getTotal());
        assertTrue(c.hayDisponible());
    }

    @Test
    void reservarTodoDejaSinDisponibilidad() {
        Cupo c = new Cupo(2);
        c.reservar(2);
        assertFalse(c.hayDisponible());
    }

    @Test
    void reservarDeMasLanzaExcepcion() {
        Cupo c = new Cupo(2);
        assertThrows(CupoInsuficienteException.class, () -> c.reservar(3));
    }

    @Test
    void liberarAumentaLosDisponibles() {
        Cupo c = new Cupo(5);
        c.reservar(3);
        c.liberar(2);
        assertEquals(1, c.getReservados());
    }

    @Test
    void liberarDeMasNoDejaReservadosNegativos() {
        Cupo c = new Cupo(5);
        c.reservar(1);
        c.liberar(10);
        assertEquals(0, c.getReservados());
    }
}
