package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ReporteOcupacionTest {

    @Test
    void aceptarSeIdentificaAnteElVisitorYDevuelveSuResultado() {
        VisitorReporte visitor = mock(VisitorReporte.class);
        ReporteOcupacion reporte = new ReporteOcupacion(List.of());
        when(visitor.visitar(reporte)).thenReturn("resultado");

        assertEquals("resultado", reporte.aceptar(visitor));
        verify(visitor).visitar(reporte);
    }
    
}