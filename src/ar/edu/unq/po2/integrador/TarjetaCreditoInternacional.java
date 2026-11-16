package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class TarjetaCreditoInternacional extends MetodoPago {
    private int numeroTarjeta;
    private String cvv;
    private String fechaVencimiento;
    private ItarjetaInternacional tarjetaInternacional;
    private List<Voucher> vouchers = new ArrayList<>();

    @Override
    protected void ValidarDatos() {
        if (!tarjetaInternacional.validarDatosTarjeta(numeroTarjeta, cvv, fechaVencimiento)) {
            throw new IllegalStateException("Datos de tarjeta inválidos");
        }
    }

    @Override
    protected void ReservarFondos(double monto) {
        tarjetaInternacional.preAutorizacionDelBanco(monto);
        
    }

    @Override
    protected void EjecutarTransacion(double monto) {
        tarjetaInternacional.transferenciaInmediata(monto);
    }

    @Override
    protected void NotificarResultado(double monto) {
        Voucher voucher = new Voucher(monto);
        this.vouchers.add(voucher);
    }

    public List<Voucher> getVouchers() { return vouchers; }
}