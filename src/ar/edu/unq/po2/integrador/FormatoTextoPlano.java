package ar.edu.unq.po2.integrador;

public class FormatoTextoPlano implements VisitorReporte {

    @Override
    public String visitar(ReporteServiciosMasReservados reporte) {
        String salida = "[TEXTO PLANO] Servicios más reservados (" + reporte.getDesde() + " a " + reporte.getHasta() + ")\n";
        for (FilaServicioReservado fila : reporte.getFilas()) {
            salida += fila.getNombre() + " - unidades: " + fila.getUnidadesVendidas()
                    + " - precio promedio cobrado: " + fila.getPrecioPromedioCobrado() + "\n";
        }
        return salida;
    }

    @Override
    public String visitar(ReporteOcupacion reporte) {
        String salida = "[TEXTO PLANO] Ocupación y cupo disponible\n";
        for (FilaOcupacion fila : reporte.getFilas()) {
            salida += fila.getCategoria() + " (" + fila.getPeriodo() + ") - totales: " + fila.getTotales()
                    + " - reservados: " + fila.getReservados() + " - libres: " + fila.getLibres() + "\n";
        }
        return salida;
    }

    @Override
    public String visitar(ReporteExposicionProveedor reporte) {
        String salida = "[TEXTO PLANO] Exposición por proveedor\n";
        for (FilaExposicion fila : reporte.getFilas()) {
            salida += fila.getProveedor() + " - " + fila.getPorcentajeCatalogo() + "% del catálogo\n";
        }
        return salida;
    }
}