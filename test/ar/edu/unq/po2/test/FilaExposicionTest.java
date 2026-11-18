package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.FilaExposicion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilaExposicionTest {

    private FilaExposicion fila;

    @BeforeEach
    void setUp() {
        fila = new FilaExposicion("Provision Fabril", 35.5);
    }

    @Test
    void testGetProveedor() {
        assertEquals("Provision Fabril", fila.getProveedor());
    }

    @Test
    void testGetPorcentajeCatalogo() {
        assertEquals(35.5, fila.getPorcentajeCatalogo(), 0.001);
    }

    
}