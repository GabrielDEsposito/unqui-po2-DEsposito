package ar.edu.unq.po2.integrador;

public class EstadoSolicitudPendiente implements EstadoReserva {

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		reserva.agregarServicio(item);
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		reserva.quitarServicio(item);
		
	}

	@Override
	public void confirmada_pagada(Reserva reserva, ItemCatalogo item) {
		 reserva.setEstadoActual(new EstadoConfirmada());
		
	}

	@Override
	public void en_checkIn(Reserva reserva) {
		throw new IllegalStateException("No hay reserva confirmada aun.");
	}

	@Override
	public void en_curso(Reserva reserva) {
		throw new IllegalStateException("No hay reserva confirmada aun.");
		
	}

	@Override
	public void finalizada(Reserva reserva) {
		throw new IllegalStateException("No hay reserva confirmada aun.");
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		 reserva.setEstadoActual(new EstadoCancelada());
		
	}



	

}
