package ar.edu.unq.po2.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.EstadoReserva;
import ar.edu.unq.po2.integrador.GeneradorComprobante;
import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.TipoEstado;

public class GeneradorComprobanteTest {

	private EstadoReserva estado(TipoEstado tipo) {
		EstadoReserva e = mock(EstadoReserva.class);
		when(e.tipo()).thenReturn(tipo);
		return e;
	}

	@Test
	void alFinalizarCalculaElMontoDelComprobante() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.costoServicio()).thenReturn(10000.0);

		new GeneradorComprobante().actualizar(reserva, estado(TipoEstado.EN_CURSO), estado(TipoEstado.FINALIZADA));

		verify(reserva).costoServicio();
	}

	@Test
	void conOtrosEstadosNoHaceNada() {
		Reserva reserva = mock(Reserva.class);
		GeneradorComprobante g = new GeneradorComprobante();

		for (TipoEstado t : TipoEstado.values()) {
			if (t != TipoEstado.FINALIZADA) {
				g.actualizar(reserva, estado(TipoEstado.SOLICITUD_PENDIENTE), estado(t));
			}
		}

		verifyNoInteractions(reserva);
	}
}