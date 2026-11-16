package ar.edu.unq.po2.integrador;

public class BilleteraVirtualCrypto extends MetodoDePago {
    private int saldo;
    private IBilleteraVirtualCrypto BilleteraVirtualOCrypto;

    @Override
    protected void validarDatos() {
        BilleteraVirtualOCrypto.saldoSuficienteEnCuenta(saldo);
    }

    @Override
    protected void reservarFondos(double monto) {
        BilleteraVirtualOCrypto.bloqueoDelSaldoHastaConfirmar(monto);
    }

    @Override
    protected void ejecutarTransaccion(double monto) {
        BilleteraVirtualOCrypto.acreditacionEnTiempoReal(monto);
    }

    @Override
    protected void notificarResultado(double monto) {
        BilleteraVirtualOCrypto.notificacionPush(monto);
    }
}