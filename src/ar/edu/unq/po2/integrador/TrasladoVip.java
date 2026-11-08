package ar.edu.unq.po2.integrador;

public class TrasladoVip implements EstadoTraslado {
    private double porcentaje;
    private double cargoBase;

    public TrasladoVip(double porcentaje, double cargoBase) {
        this.porcentaje = porcentaje;
        this.cargoBase = cargoBase;
    }

    @Override
    public double calcularCostoTraslado(Reserva reserva) { //preguntar profe por el parametro de float precio
        double precioTotal = reserva.costoServicio(); //verificar si no hay que hacer un metodo llamado reserva.calcularPrecioFinal 
        return (precioTotal * this.porcentaje) + this.cargoBase;
    }
}