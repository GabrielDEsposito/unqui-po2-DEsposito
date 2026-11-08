package ar.edu.unq.po2.integrador;

public class PuntoDeEncuentro implements EstadoTraslado{
	private int cuposGuiasDisponibles;

    public PuntoDeEncuentro(int cuposGuiasDisponibles) {
        this.cuposGuiasDisponibles = cuposGuiasDisponibles;
    }

    @Override
    public double calcularCostoTraslado(Reserva reserva) {
        return 0;
    }

    public boolean verificarDisponibilidad() {
        return this.cuposGuiasDisponibles > 0;
    }
}
