package ar.edu.unq.po2.integrador;

public interface EstadoReserva {
	void agregarServicio(Reserva reserva, ItemCatalogo item);
	void quitarServicio(Reserva reserva, ItemCatalogo item);
	void confirmada_pagada(Reserva reserva, ItemCatalogo item);
	void en_checkIn(Reserva reserva);
	void en_curso(Reserva reserva);
	void finalizada(Reserva reserva);
	void cancelada(Reserva reserva);
	
}
