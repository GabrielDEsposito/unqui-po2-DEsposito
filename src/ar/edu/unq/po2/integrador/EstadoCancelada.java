package ar.edu.unq.po2.integrador;

public class EstadoCancelada implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

	@Override
	public void confirmar(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

	@Override
	public void hacerCheckIn(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

	@Override
	public void iniciar(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		throw new OperacionInvalidaException("la reserva está cancelada”");
		
	}

}
