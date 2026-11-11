package ar.edu.unq.po2.integrador;

public class TarjetaCreditoInternacional extends MetodoPago {
    private int NumeroTarjeta;
    private String cvv;
    private String fechaVencimiento;
    private ItarjetaInternacional tarjetaInternacional;

    @Override
    protected void validarDatos() {
        tarjetaInternacional.validarDatosTarjeta(numeroTarjeta, cvv, vencimiento);
    }

    @Override
    protected void reservarFondos(double monto) {
        tarjetaInternacional.preAutorizacionDelBanco(monto);
    }

    @Override
    protected void ejecutarTransaccion(double monto) {
        tarjetaInternacional.transferenciaInmediata(monto);
    }

    @Override
    protected void notificarResultado() {

    }
}