package ar.edu.unq.po2.integrador;

public class TrasladoEstandar implements EstadoTraslado {
	private int cantidadPasajeros;
    private String direccionDestino;
    private PlataformaTransporte plataformaTransporte;
	
    public TrasladoEstandar(int cantidadPasajeros, String direccionDestino,
            PlataformaTransporte plataformaTransporte) {
    		this.cantidadPasajeros = cantidadPasajeros;
    		this.direccionDestino = direccionDestino;
    		this.plataformaTransporte = plataformaTransporte;
    }
    
    public TrasladoEstandar(int cantidadPasajeros, String direccionDestino) {
        this.cantidadPasajeros = cantidadPasajeros;
        this.direccionDestino = direccionDestino;
    }

    @Override
    public double calcularCostoTraslado(Reserva reserva) {
        return plataformaTransporte.estimarTraslado(this.cantidadPasajeros, this.direccionDestino); //libreria
    }

}
