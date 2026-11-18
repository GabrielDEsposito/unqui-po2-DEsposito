package ar.edu.unq.po2.integrador;

import java.util.List;

public class ReporteOcupacion implements Reporte {
    private final List<FilaOcupacion> filas;

    public ReporteOcupacion(List<FilaOcupacion> filas) { 
        this.filas = List.copyOf(filas); 
    }

    public List<FilaOcupacion> getFilas() { 
        return filas; 
    }

    @Override
    public String aceptar(VisitorReporte visitor) { 
        return visitor.visitar(this); 
    }
}