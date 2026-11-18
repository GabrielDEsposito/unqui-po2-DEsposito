package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;

abstract class FormatoReporteTest {

    protected abstract VisitorReporte formato();
    protected abstract String etiqueta();

    @Test
    void formateaServiciosMasReservados() {
        FilaServicioReservado fila = mock(FilaServicioReservado.class);
        when(fila.getNombre()).thenReturn("Pack Escapada Urbana");
        when(fila.getUnidadesVendidas()).thenReturn(2);
        when(fila.getPrecioPromedioCobrado()).thenReturn(18900.0);

        ReporteServiciosMasReservados reporte = mock(ReporteServiciosMasReservados.class);
        when(reporte.getDesde()).thenReturn("2026-01-01");
        when(reporte.getHasta()).thenReturn("2026-12-31");
        when(reporte.getFilas()).thenReturn(List.of(fila));

        String salida = formato().visitar(reporte);

        assertTrue(salida.contains(etiqueta()));
        assertTrue(salida.contains("2026-01-01 a 2026-12-31"));
        assertTrue(salida.contains("Pack Escapada Urbana"));
        assertTrue(salida.contains("unidades: 2"));
        assertTrue(salida.contains("precio promedio cobrado: 18900.0"));
    }
}