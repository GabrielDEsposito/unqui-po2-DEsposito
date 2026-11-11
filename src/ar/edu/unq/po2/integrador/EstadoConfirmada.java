package ar.edu.unq.po2.integrador;

public class EstadoConfirmada implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		throw new IllegalStateException("esta opcion no esta disponible");
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		throw new IllegalStateException("esta opcion no esta disponible");
		
	}

	@Override
	public void confirmada_pagada(Reserva reserva, ItemCatalogo item) {
		reserva.decrementarCupo();
		
	} 

	@Override
	public void en_checkIn(Reserva reserva) {
		reserva.setEstadoActual(new EstadoCheckIn());
		
	}

	@Override
	public void en_curso(Reserva reserva) {
		throw new IllegalStateException("esta opcion no esta disponible");
		
	}

	@Override
	public void finalizada(Reserva reserva) {
		throw new IllegalStateException("esta opcion no esta disponible");
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		reserva.setEstadoActual(new EstadoCancelada());
		
	}





}
