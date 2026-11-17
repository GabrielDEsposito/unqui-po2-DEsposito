package ar.edu.unq.po2.integrador;

public interface VisitorReporte {
    String visitar(ReporteServiciosMasReservados reporte);
    String visitar(ReporteOcupacion reporte);
}