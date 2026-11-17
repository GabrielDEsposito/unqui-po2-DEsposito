package ar.edu.unq.po2.integrador;

import java.util.Comparator;
import java.util.List;

public class ReporteServiciosMasReservados implements Reporte {
    private final String desde;
    private final String hasta;
    private final List<FilaServicioReservado> filas;

    public ReporteServiciosMasReservados(String desde, String hasta, List<FilaServicioReservado> filas) {
        this.desde = desde;
        this.hasta = hasta;
        this.filas = filas.stream()
            .sorted(Comparator.comparingInt(FilaServicioReservado::getUnidadesVendidas).reversed())
            .toList();
    }

    public String getDesde() { return desde; }
    public String getHasta() { return hasta; }
    public List<FilaServicioReservado> getFilas() { return filas; }

    @Override
    public String aceptar(VisitorReporte visitor) { return visitor.visitar(this); }
}