package ar.edu.unq.po2.integrador;

public interface ItarjetaInternacional {
    boolean validarDatosTarjeta(int numero, String cvv, String vencimiento);
    void preAutorizacionDelBanco(double monto);
    void transferenciaInmediata(double monto);
}
