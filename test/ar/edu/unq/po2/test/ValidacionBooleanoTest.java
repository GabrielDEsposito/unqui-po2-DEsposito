package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ValidacionBooleano;

class ValidacionBooleanoTest {

    private ValidacionBooleano validacion;

    @BeforeEach
    void setUp() {
        validacion = new ValidacionBooleano();
    }

    @Test
    void trueEsConsistente() {
        assertTrue(validacion.esConsistente("true"));
    }
}
