package ar.edu.unq.po2.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Cliente;
import ar.edu.unq.po2.integrador.EstadoReserva;
import ar.edu.unq.po2.integrador.MailSender;
import ar.edu.unq.po2.integrador.NotificadorMail;
import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.TipoEstado;

public class NotificadorMailTest {

	@Test
	void enviaMailSoloEnLosEstadosHabilitados() {
		MailSender mail = mock(MailSender.class);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getCliente()).thenReturn(new Cliente("Ana", "ana@mail.com"));
		NotificadorMail n = new NotificadorMail(mail);

		for (TipoEstado t : TipoEstado.values()) {
			EstadoReserva nuevo = mock(EstadoReserva.class);
			when(nuevo.tipo()).thenReturn(t);
			n.actualizar(reserva, mock(EstadoReserva.class), nuevo);
		}

		verify(mail, times(3)).enviarMail(eq("ana@mail.com"), anyString(), anyString(), any());
	}
}