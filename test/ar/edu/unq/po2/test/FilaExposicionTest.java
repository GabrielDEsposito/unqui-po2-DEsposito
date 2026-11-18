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

    @Test
    void testProveedorConTodoElCatalogo() {
        FilaExposicion total = new FilaExposicion("Aperam", 100.0);

        assertEquals("Aperam", total.getProveedor());
        assertEquals(100.0, total.getPorcentajeCatalogo(), 0.001);
    }

    @Test
    void testProveedorSinExposicion() {
        FilaExposicion nula = new FilaExposicion("Genebre", 0.0);

        assertEquals(0.0, nula.getPorcentajeCatalogo(), 0.001);
    }
}