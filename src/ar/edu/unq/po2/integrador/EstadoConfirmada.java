package ar.edu.unq.po2.integrador;

public class EstadoConfirmada implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException("esta opcion no esta disponible");
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException("esta opcion no esta disponible");
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		reserva.decrementarCupo();
	} 

	@Override
	public void hacerCheckIn(Reserva reserva) {
		reserva.setEstadoActual(new EstadoCheckIn());
		
	}

	@Override
	public void iniciar(Reserva reserva) {
		throw new OperacionInvalidaException("esta opcion no esta disponible");
		
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new OperacionInvalidaException("esta opcion no esta disponible");
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.liberarCupo();
		reserva.setEstadoActual(new EstadoCancelada());
	}





}
