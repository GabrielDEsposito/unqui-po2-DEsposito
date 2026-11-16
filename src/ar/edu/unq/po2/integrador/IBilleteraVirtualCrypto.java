package ar.edu.unq.po2.integrador;

public interface IBilleteraVirtualCrypto {
    boolean saldoSuficienteEnCuenta(double monto);
    void bloqueoDelSaldoHastaConfirmar(double monto);
    void acreditacionEnTiempoReal(double monto);
    void notificacionPush(double monto);
}