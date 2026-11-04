package ar.edu.unq.po2.integrador;

public interface EstadoReserva {
	void agregarServicio(Reserva reserva, Catalogo item);
	void quitarServicio(Reserva reserva, Catalogo item);
	void confirmada_pagada(Reserva reserva, Catalogo item);
	void en_checkIn(Reserva reserva);
	void en_curso(Reserva reserva);
	void finalizada(Reserva reserva);
	void cancelada(Reserva reserva);
	
}
