package ar.edu.unq.po2.integrador;

public class EstadoCheckIn implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, Catalogo item) {
		throw new IllegalStateException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, Catalogo item) {
		throw new IllegalStateException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void confirmada_pagada(Reserva reserva, Catalogo item) {
		throw new IllegalStateException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void en_checkIn(Reserva reserva) {
		throw new IllegalStateException("la reserva se encuentra en estado de check-in.");
		
		
	}

	@Override
	public void en_curso(Reserva reserva) {
		throw new IllegalStateException("la reserva se encuentra en estado de check-in.");
		
	}

	@Override
	public void finalizada(Reserva reserva) {
		 reserva.setEstadoActual(new EstadoFinalizada());
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.liberarCupo();
        reserva.procesarReembolsoCompleto();
        reserva.setEstadoActual(new EstadoCancelada());
		
	}

	


	
}
