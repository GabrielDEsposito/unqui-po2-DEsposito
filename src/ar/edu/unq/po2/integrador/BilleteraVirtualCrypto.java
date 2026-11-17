package ar.edu.unq.po2.integrador;

public class BilleteraVirtualCrypto extends MetodoPago {
    private int saldo;
    private IBilleteraVirtualCrypto BilleteraVirtualOCrypto;

    @Override
	public void ValidarDatos() {
        BilleteraVirtualOCrypto.saldoSuficienteEnCuenta(saldo);
    }

    @Override
	public void ReservarFondos(double monto) {
        BilleteraVirtualOCrypto.bloqueoDelSaldoHastaConfirmar(monto);
    }

    @Override
	public void EjecutarTransacion(double monto) {
        BilleteraVirtualOCrypto.acreditacionEnTiempoReal(monto);
    }

    @Override
	public void NotificarResultado(double monto) {
        BilleteraVirtualOCrypto.notificacionPush(monto);
    }
}