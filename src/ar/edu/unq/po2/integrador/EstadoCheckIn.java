package ar.edu.unq.po2.integrador;

public class EstadoCheckIn implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void hacerCheckIn(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva se encuentra en estado de check-in.");
		
		
	}

	@Override
	public void iniciar(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void finalizar(Reserva reserva) {
		 reserva.setEstadoActual(new EstadoFinalizada());
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.liberarCupos();
        reserva.procesarReembolsoCompleto();
        reserva.setEstadoActual(new EstadoCancelada());
	}
}
//asd