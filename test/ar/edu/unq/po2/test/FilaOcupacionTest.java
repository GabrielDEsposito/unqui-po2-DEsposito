package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.Categoria;
import ar.edu.unq.po2.integrador.FilaOcupacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilaOcupacionTest {

    private Categoria categoria;
    private FilaOcupacion fila;

    @BeforeEach
    void setUp() {
        // Categoria se mockea para aislar el test de su implementación
        categoria = mock(Categoria.class);
        fila = new FilaOcupacion(categoria, "2026-11", 20, 12);
    }

    @Test
    void testGetCategoria() {
        assertEquals(categoria, fila.getCategoria());
    }

    @Test
    void testGetPeriodo() {
        assertEquals("2026-11", fila.getPeriodo());
    }

    @Test
    void testGetTotales() {
        assertEquals(20, fila.getTotales());
    }

    @Test
    void testGetReservados() {
        assertEquals(12, fila.getReservados());
    }

    @Test
    void testGetLibresEsTotalesMenosReservados() {
        assertEquals(8, fila.getLibres());
    }

    
}