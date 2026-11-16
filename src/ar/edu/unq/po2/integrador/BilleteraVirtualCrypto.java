package ar.edu.unq.po2.integrador;

public class BilleteraVirtualCrypto extends MetodoPago {
    private int saldo;
    private IBilleteraVirtualCrypto BilleteraVirtualOCrypto;

    @Override
    protected void ValidarDatos() {
        BilleteraVirtualOCrypto.saldoSuficienteEnCuenta(saldo);
    }

    @Override
    protected void ReservarFondos(double monto) {
        BilleteraVirtualOCrypto.bloqueoDelSaldoHastaConfirmar(monto);
    }

    @Override
    protected void EjecutarTransacion(double monto) {
        BilleteraVirtualOCrypto.acreditacionEnTiempoReal(monto);
    }

    @Override
    protected void NotificarResultado(double monto) {
        BilleteraVirtualOCrypto.notificacionPush(monto);
    }
}