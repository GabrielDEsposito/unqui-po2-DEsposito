package ar.edu.unq.po2.integrador;

public class TarjetaCreditoInternacional extends MetodoPago {
    private int NumeroTarjeta;
    private String cvv;
    private String fechaVencimiento;
    private ItarjetaInternacional tarjetaInternacional;
    private Double montoUltimaTransaccion;
    private List<Voucher> vouchers = new ArrayList<>();

    @Override
    protected void validarDatos() {
        if (!tarjetaInternacional.validarDatosTarjeta(numeroTarjeta, cvv, vencimiento)) {
            throw new IllegalStateException("Datos de tarjeta inválidos");
        }
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
        Voucher voucher = new Voucher(monto, this.getCodigoTransaccion());
        this.vouchers.add(voucher);
    }

    public List<Voucher> getVouchers() { return vouchers; }
}