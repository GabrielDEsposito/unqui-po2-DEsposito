package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class TarjetaCreditoInternacional extends MetodoPago {
    private int numeroTarjeta;
    private String cvv;
    private String fechaVencimiento;
    private ItarjetaInternacional tarjetaInternacional;
    private List<Voucher> vouchers = new ArrayList<>();

    public TarjetaCreditoInternacional(int numeroTarjeta, String cvv, String fechaVencimiento,ItarjetaInternacional tarjetaInternacional) {
    		this.numeroTarjeta = numeroTarjeta;
    		this.cvv = cvv;
    		this.fechaVencimiento = fechaVencimiento;
    		this.tarjetaInternacional = tarjetaInternacional;
    }
    
    @Override
	public void ValidarDatos() {
        if (!tarjetaInternacional.validarDatosTarjeta(numeroTarjeta, cvv, fechaVencimiento)) {
            throw new IllegalStateException("Datos de tarjeta inválidos");
        }
    }

    @Override
    public void ReservarFondos(double monto) {
        tarjetaInternacional.preAutorizacionDelBanco(monto);
        
    }

    @Override
    public void EjecutarTransaccion(double monto) {
        tarjetaInternacional.transferenciaInmediata(monto);
    }

    @Override
    public void NotificarResultado(double monto) {
        Voucher voucher = new Voucher(monto);
        this.vouchers.add(voucher);
    }

    public List<Voucher> getVouchers() { return vouchers; }
}