package ar.edu.unq.po2.integrador;

public class EstadoCancelada implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, Catalogo item) {
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, Catalogo item) {
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}

	@Override
	public void confirmada_pagada(Reserva reserva, Catalogo item) {
		reserva.liberarCupo();
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}

	@Override
	public void en_checkIn(Reserva reserva) {
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}

	@Override
	public void en_curso(Reserva reserva) {
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}

	@Override
	public void finalizada(Reserva reserva) {
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		throw new IllegalStateException(" se completó la estadía/viaje");
		
	}



}
