package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class ReporteServiciosMasReservadosTest {

    @Test
    void aceptarSeIdentificaAnteElVisitorYDevuelveSuResultado() {
        VisitorReporte visitor = mock(VisitorReporte.class);
        ReporteServiciosMasReservados reporte =
            new ReporteServiciosMasReservados("2026-01-01", "2026-12-31", List.of());
        when(visitor.visitar(reporte)).thenReturn("resultado");

        assertEquals("resultado", reporte.aceptar(visitor));
        verify(visitor).visitar(reporte);
    }
}