package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ValidacionFecha;

class ValidacionFechaTest {

    private ValidacionFecha validacion;

    @BeforeEach
    void setUp() {
        validacion = new ValidacionFecha();
    }

    @Test
    void unaFechaConFormatoISOEsConsistente() {
        assertTrue(validacion.esConsistente("2026-11-17"));
    }

    @Test
    void unaFechaBisiestaValidaEsConsistente() {
        assertTrue(validacion.esConsistente("2024-02-29"));
    }

    @Test
    void unTextoQueNoEsFechaNoEsConsistente() {
        assertFalse(validacion.esConsistente("hola"));
    }
}