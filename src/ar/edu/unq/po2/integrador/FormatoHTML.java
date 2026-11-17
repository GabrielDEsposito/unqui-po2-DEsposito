package ar.edu.unq.po2.integrador;

public class FormatoHTML implements VisitorReporte {

    @Override
    public String visitar(ReporteServiciosMasReservados reporte) {
        String salida = "[HTML] Servicios más reservados (" + reporte.getDesde() + " a " + reporte.getHasta() + ")\n";
        for (FilaServicioReservado fila : reporte.getFilas()) {
            salida += fila.getNombre() + " - unidades: " + fila.getUnidadesVendidas()
                    + " - precio promedio cobrado: " + fila.getPrecioPromedioCobrado() + "\n";
        }
        return salida;
    }

    @Override
    public String visitar(ReporteOcupacion reporte) {
        String salida = "[HTML] Ocupación y cupo disponible\n";
        for (FilaOcupacion fila : reporte.getFilas()) {
            salida += fila.getCategoria() + " (" + fila.getPeriodo() + ") - totales: " + fila.getTotales()
                    + " - reservados: " + fila.getReservados() + " - libres: " + fila.getLibres() + "\n";
        }
        return salida;
    }

    @Override
    public String visitar(ReporteExposicionProveedor reporte) {
        String salida = "[HTML] Exposición por proveedor\n";
        for (FilaExposicion fila : reporte.getFilas()) {
            salida += fila.getProveedor() + " - " + fila.getPorcentajeCatalogo() + "% del catálogo\n";
        }
        return salida;
    }
}