package ar.edu.unq.po2.integrador;

public class TarjetaCreditoInternacional extends MetodoPago {
    private int NumeroTarjeta;
    private String cvv;
    private String fechaVencimiento;
    private ItarjetaInternacional tarjetaInternacional;

    void ValidarDatos() {
        tarjetaInternacional.validarDatosTarjeta(NumeroTarjeta, cvv, fechaVencimiento);
    }


    void ReservarFondos() {
    }


    void EjecutarTransacion() {
        tarjeta
    }


    void NotificarResultado() {
        System.out.println("Notificando resultado de la tarjeta internacional");
    }

}