package ar.edu.unq.po2.test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import ar.edu.unq.po2.integrador.MetodoPago;

public class MetodoDePagoTest {

    private MetodoPago metodo;
    
    
    static class MetodoPagoFalso extends MetodoPago {
        @Override public void ValidarDatos() { }
        @Override public void ReservarFondos(double monto) { }
        @Override public void EjecutarTransaccion(double monto) { }
    }

    @BeforeEach
    void setUp() {
        metodo = spy(new MetodoPagoFalso());
    }
    
    @Test
    void elContadorDeTransaccionesEsCompartidoEntreInstancias() {
        MetodoPago otro = spy(new MetodoPagoFalso());

        metodo.RealizarPago(10.0);
        int codigoDelPrimero = metodo.getCodigoTransaccion();

        otro.RealizarPago(10.0);

        assertEquals(codigoDelPrimero + 1, otro.getCodigoTransaccion());
    }
    
   
}