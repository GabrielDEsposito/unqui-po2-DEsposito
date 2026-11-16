package ar.edu.unq.po2.integrador;

public class EstadoFinalizada implements EstadoReserva{

	@Override
	public TipoEstado tipo() {
		return TipoEstado.FINALIZADA;
	}
	
	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

	@Override
	public void hacerCheckIn(Reserva reserva) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

	@Override
	public void iniciar(Reserva reserva) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		throw new OperacionInvalidaException(" se completó la estadía/viaje");
		
	}

}
