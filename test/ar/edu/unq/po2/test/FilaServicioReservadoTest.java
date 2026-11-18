package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.FilaServicioReservado;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilaServicioReservadoTest {

    private FilaServicioReservado fila;

    @BeforeEach
    void setUp() {
        fila = new FilaServicioReservado("Hospedaje", 10, 1500.50);
    }

    @Test
    void testGetNombre() {
        assertEquals("Hospedaje", fila.getNombre());
    }

    @Test
    void testGetUnidadesVendidas() {
        assertEquals(10, fila.getUnidadesVendidas());
    }

    @Test
    void testGetPrecioPromedioCobrado() {
        assertEquals(1500.50, fila.getPrecioPromedioCobrado(), 0.001);
    }

    @Test
    void testFilaConValoresEnCero() {
        FilaServicioReservado vacia = new FilaServicioReservado("Sin ventas", 0, 0.0);

        assertEquals("Sin ventas", vacia.getNombre());
        assertEquals(0, vacia.getUnidadesVendidas());
        assertEquals(0.0, vacia.getPrecioPromedioCobrado(), 0.001);
    }
}