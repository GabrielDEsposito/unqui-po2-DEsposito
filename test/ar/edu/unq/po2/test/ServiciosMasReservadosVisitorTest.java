package ar.edu.unq.po2.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.FilaServicioReservado;
import ar.edu.unq.po2.integrador.PaqueteTuristico;
import ar.edu.unq.po2.integrador.ReporteServiciosMasReservados;
import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.ServicioIndividual;
import ar.edu.unq.po2.integrador.ServiciosMasReservadosVisitor;

class ServiciosMasReservadosVisitorTest {

    private static final String DESDE = "2026-01-01";
    private static final String HASTA = "2026-12-31";

    private ServiciosMasReservadosVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ServiciosMasReservadosVisitor(DESDE, HASTA);
    }

    // Crea un servicio mock que, al ser aceptado, se visita a sí mismo en el visitor
    private ServicioIndividual crearServicio(String nombre, double precioFinal) {
        ServicioIndividual servicio = mock(ServicioIndividual.class);
        when(servicio.getNombre()).thenReturn(nombre);
        when(servicio.getPrecioFinal()).thenReturn(precioFinal);
        return servicio;
    }

    @Test
    void sinVisitasElReporteNoTieneFilas() {
        ReporteServiciosMasReservados reporte = visitor.getReporte();

        assertTrue(reporte.getFilas().isEmpty());
    }

    @Test
    void visitarServicioRegistraUnaFilaConSusDatos() {
        ServicioIndividual hotel = crearServicio("Hotel Sol", 1000.0);

        visitor.visitarServicio(hotel);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(1, filas.size());
        assertEquals("Hotel Sol", filas.get(0).getNombre());
        assertEquals(1, filas.get(0).getUnidadesVendidas());
        assertEquals(1000.0, filas.get(0).getPrecioPromedioCobrado(), 0.0001);
    }

    @Test
    void visitarElMismoServicioDosVecesAcumulaEnLaMismaFila() {
        ServicioIndividual hotel = crearServicio("Hotel Sol", 1000.0);

        visitor.visitarServicio(hotel);
        visitor.visitarServicio(hotel);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(1, filas.size());
        assertEquals(2, filas.get(0).getUnidadesVendidas());
        assertEquals(1000.0, filas.get(0).getPrecioPromedioCobrado(), 0.0001);
    }

    @Test
    void elPrecioPromedioSeCalculaConLoEfectivamenteCobrado() {
        ServicioIndividual hotel = mock(ServicioIndividual.class);
        when(hotel.getNombre()).thenReturn("Hotel Sol");
        when(hotel.getPrecioFinal()).thenReturn(1000.0, 500.0);

        visitor.visitarServicio(hotel);
        visitor.visitarServicio(hotel);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(750.0, filas.get(0).getPrecioPromedioCobrado(), 0.0001);
    }

    @Test
    void visitarServiciosDistintosGeneraUnaFilaPorCadaUno() {
        ServicioIndividual hotel = crearServicio("Hotel Sol", 1000.0);
        ServicioIndividual excursion = crearServicio("Excursion Glaciar", 300.0);

        visitor.visitarServicio(hotel);
        visitor.visitarServicio(excursion);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(2, filas.size());
        assertEquals("Hotel Sol", filas.get(0).getNombre());
        assertEquals("Excursion Glaciar", filas.get(1).getNombre());
    }

    @Test
    void visitarPaqueteLoRegistraComoUnItemMas() {
        PaqueteTuristico paquete = mock(PaqueteTuristico.class);
        when(paquete.getNombre()).thenReturn("Paquete Patagonia");
        when(paquete.getPrecioFinal()).thenReturn(5000.0);

        visitor.visitarPaquete(paquete);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(1, filas.size());
        assertEquals("Paquete Patagonia", filas.get(0).getNombre());
        assertEquals(1, filas.get(0).getUnidadesVendidas());
        assertEquals(5000.0, filas.get(0).getPrecioPromedioCobrado(), 0.0001);
    }

    @Test
    void visitarReservaEnPeriodoRegistraTodosSusItems() {
        ServicioIndividual hotel = crearServicio("Hotel Sol", 1000.0);
        ServicioIndividual excursion = crearServicio("Excursion Glaciar", 300.0);
        Reserva reserva = mock(Reserva.class);
        when(reserva.estaEnPeriodo(DESDE, HASTA)).thenReturn(true);
        when(reserva.getItems()).thenReturn(List.of(hotel, excursion));

        visitor.visitarReserva(reserva);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(2, filas.size());
        assertEquals(1, filas.get(0).getUnidadesVendidas());
        assertEquals(1, filas.get(1).getUnidadesVendidas());
    }

    @Test
    void visitarReservaFueraDePeriodoNoRegistraNada() {
        ServicioIndividual hotel = crearServicio("Hotel Sol", 1000.0);
        Reserva reserva = mock(Reserva.class);
        when(reserva.estaEnPeriodo(DESDE, HASTA)).thenReturn(false);
        when(reserva.getItems()).thenReturn(List.of(hotel));

        visitor.visitarReserva(reserva);

        assertTrue(visitor.getReporte().getFilas().isEmpty());
    }

    @Test
    void variasReservasConElMismoServicioAcumulanUnidades() {
        ServicioIndividual hotel = crearServicio("Hotel Sol", 1000.0);
        Reserva reserva1 = mock(Reserva.class);
        Reserva reserva2 = mock(Reserva.class);
        when(reserva1.estaEnPeriodo(DESDE, HASTA)).thenReturn(true);
        when(reserva2.estaEnPeriodo(DESDE, HASTA)).thenReturn(true);
        when(reserva1.getItems()).thenReturn(List.of(hotel));
        when(reserva2.getItems()).thenReturn(List.of(hotel));

        visitor.visitarReserva(reserva1);
        visitor.visitarReserva(reserva2);

        List<FilaServicioReservado> filas = visitor.getReporte().getFilas();
        assertEquals(1, filas.size());
        assertEquals(2, filas.get(0).getUnidadesVendidas());
    }
}
