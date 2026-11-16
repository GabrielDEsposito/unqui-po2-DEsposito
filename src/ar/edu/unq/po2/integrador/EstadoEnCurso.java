package ar.edu.unq.po2.integrador;

public class EstadoEnCurso implements EstadoReserva{

	@Override
	public void agregarServicio(Reserva reserva, ItemCatalogo item) {
		 throw new IllegalStateException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void quitarServicio(Reserva reserva, ItemCatalogo item) {
		 throw new IllegalStateException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void confirmada_pagada(Reserva reserva, ItemCatalogo itemm) {
		 throw new IllegalStateException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void en_checkIn(Reserva reserva) {
		 throw new IllegalStateException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void en_curso(Reserva reserva) {
		 throw new IllegalStateException("el viaje/servicio esta en ejecucion.");
		
	}

	@Override
	public void finalizada(Reserva reserva) {
		 reserva.setEstadoActual(new EstadoFinalizada());
		
	}

	@Override
	public void cancelada(Reserva reserva) {
		 reserva.reembolsarServicios();
		 reserva.setEstadoActual(new EstadoCancelada());
		
	}



}
