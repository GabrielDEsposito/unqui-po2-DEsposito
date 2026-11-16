package ar.edu.unq.po2.integrador;

public class EstadoEnCurso implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		 throw new OperacionInvalidaException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		 throw new OperacionInvalidaException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		 throw new OperacionInvalidaException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void hacerCheckIn(Reserva reserva) {
		 throw new OperacionInvalidaException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void iniciar(Reserva reserva) {
		 throw new OperacionInvalidaException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void finalizar(Reserva reserva) {
		 reserva.setEstadoActual(new EstadoFinalizada());
	}

	@Override
	public void cancelar(Reserva reserva) {
		 reserva.reembolsarServicios();
		 reserva.setEstadoActual(new EstadoCancelada());
	}
}
