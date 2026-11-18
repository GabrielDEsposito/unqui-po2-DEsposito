package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.EstadoReserva;
import ar.edu.unq.po2.integrador.Fidelizacion;
import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.TipoEstado;

public class FidelizacionTest {

	private final PrintStream original = System.out;
	private ByteArrayOutputStream salida;

	@BeforeEach
	void capturarSalida() {
		salida = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salida));
	}

	@AfterEach
	void restaurarSalida() {
		System.setOut(original);
	}

	private EstadoReserva estado(TipoEstado tipo) {
		EstadoReserva e = mock(EstadoReserva.class);
		when(e.tipo()).thenReturn(tipo);
		return e;
	}

	@Test
	void alCancelarEnviaElCuponDel5Porciento() {
		new Fidelizacion().actualizar(mock(Reserva.class), estado(TipoEstado.EN_CHECKIN), estado(TipoEstado.CANCELADA));

		assertTrue(salida.toString().contains("5%"));
	}

	@Test
	void conCualquierOtroEstadoNoEnviaNada() {
		Fidelizacion f = new Fidelizacion();
		for (TipoEstado t : TipoEstado.values()) {
			if (t != TipoEstado.CANCELADA) {
				f.actualizar(mock(Reserva.class), estado(TipoEstado.SOLICITUD_PENDIENTE), estado(t));
			}
		}

		assertEquals("", salida.toString());
	}
}