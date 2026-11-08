package ar.edu.unq.po2.integrador;

public class TrasladoEstandar implements EstadoTraslado {

	private int cantidadPasajeros;
    private String direccionDestino;
	
    public TrasladoEstandar(int cantidadPasajeros, String direccionDestino) {
        this.cantidadPasajeros = cantidadPasajeros;
        this.direccionDestino = direccionDestino;
    }

    @Override
    public double calcularCostoTraslado(Reserva reserva) {
        return PlataformaTransporte.estimarTraslado(this.cantidadPasajeros, this.direccionDestino); //libreria
    }
	
	
	
	

}
