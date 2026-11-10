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
        System.out.println("Reservando fondos de la tarjeta internacional");
        
    }


    void EjecutarTransacion() {
        System.out.println("Ejecutando transaccion de la tarjeta internacional");
        
    }


    void NotificarResultado() {
        System.out.println("Notificando resultado de la tarjeta internacional");
    }

}