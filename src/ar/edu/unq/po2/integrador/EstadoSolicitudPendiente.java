package ar.edu.unq.po2.integrador;

public class EstadoSolicitudPendiente implements EstadoReserva {

	@Override
	public TipoEstado tipo() {
		return TipoEstado.SOLICITUD_PENDIENTE;
	}
	
	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		reserva.agregarServicio(item);
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		reserva.quitarServicio(item);
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		reserva.reservarCupos(); 
		reserva.setEstadoActual(new EstadoConfirmada());
		
	}

	@Override
	public void hacerCheckIn(Reserva reserva) {
		throw new OperacionInvalidaException("No hay reserva confirmada aun.");
	}

	@Override
	public void iniciar(Reserva reserva) {
		throw new OperacionInvalidaException("No hay reserva confirmada aun.");
		
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new OperacionInvalidaException("No hay reserva confirmada aun.");
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		 reserva.setEstadoActual(new EstadoCancelada());
		
	}

}
