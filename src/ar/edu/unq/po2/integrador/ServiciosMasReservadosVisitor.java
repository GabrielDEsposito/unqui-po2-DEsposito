package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class ServiciosMasReservadosVisitor extends ReporteVisitorAdapter {
    private final String desde;
    private final String hasta;
    private final List<RegistroVenta> registros = new ArrayList<>();

    public ServiciosMasReservadosVisitor(String desde, String hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public void visitarReserva(Reserva reserva) {
        if (!reserva.estaEnPeriodo(desde, hasta)) return;
        for (ItemCatalogo item : reserva.getItems()) {
            item.aceptar(this);
        }
    }

    @Override
    public void visitarServicio(ServicioIndividual servicio) { registrar(servicio); }

    @Override
    public void visitarPaquete(PaqueteTuristico paquete) { registrar(paquete); }

    private void registrar(ItemCatalogo item) {
        RegistroVenta registro = buscarRegistro(item);
        if (registro == null) {
            registro = new RegistroVenta(item);
            registros.add(registro);
        }
        registro.agregarVenta(item.getPrecioFinal()); // lo efectivamente cobrado, con descuentos
    }

    private RegistroVenta buscarRegistro(ItemCatalogo item) {
        for (RegistroVenta registro : registros) {
            if (registro.getItem().equals(item)) return registro;
        }
        return null;
    }

    public ReporteServiciosMasReservados getReporte() {
        List<FilaServicioReservado> filas = new ArrayList<>();
        for (RegistroVenta r : registros) {
            filas.add(new FilaServicioReservado(r.getItem().getNombre(),
                                                r.getUnidades(), r.getPrecioPromedio()));
        }
        return new ReporteServiciosMasReservados(desde, hasta, filas);
    }

}

