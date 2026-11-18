package ar.edu.unq.po2.integrador;

import java.util.List;

public class ReporteExposicionProveedor implements Reporte {
    private final List<FilaExposicion> filas;

    public ReporteExposicionProveedor(List<FilaExposicion> filas) { this.filas = List.copyOf(filas); }

    public List<FilaExposicion> getFilas() { return filas; }

    @Override
    public String aceptar(VisitorReporte visitor) { return visitor.visitar(this); }
}