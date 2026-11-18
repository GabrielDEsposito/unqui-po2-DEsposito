package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import ar.edu.unq.po2.integrador.*;

public class EstadosReservaTest {

	private Reserva reserva;
	private ItemCatalogo item;

	@BeforeEach
	void setUp() {
		reserva = mock(Reserva.class);
		item = mock(ItemCatalogo.class);
	}

	private void assertInvalida(org.junit.jupiter.api.function.Executable op) {
		assertThrows(OperacionInvalidaException.class, op);
		verifyNoMoreInteractions(reserva);
	}

	// ---------- SOLICITUD PENDIENTE ----------
	@Test
	void pendienteAgregaYQuitaServicios() {
		EstadoReserva e = new EstadoSolicitudPendiente();
		e.agregarServicio(reserva, item);
		e.quitarServicio(reserva, item);
		verify(reserva).agregarServicio(item);
		verify(reserva).quitarServicio(item);
	}

	@Test
	void pendienteConfirmaReservandoCuposYLuegoCambiaDeEstado() {
		new EstadoSolicitudPendiente().confirmar(reserva);
		InOrder orden = inOrder(reserva);
		orden.verify(reserva).reservarCupos();
		orden.verify(reserva).setEstadoActual(isA(EstadoConfirmada.class));
	}

	@Test
	void pendienteCancelaSinTocarCupos() {
		new EstadoSolicitudPendiente().cancelar(reserva);
		verify(reserva).setEstadoActual(isA(EstadoCancelada.class));
		verifyNoMoreInteractions(reserva);
	}

	@Test
	void pendienteRechazaCheckInIniciarYFinalizar() {
		EstadoReserva e = new EstadoSolicitudPendiente();
		assertInvalida(() -> e.hacerCheckIn(reserva));
		assertInvalida(() -> e.iniciar(reserva));
		assertInvalida(() -> e.finalizar(reserva));
	}

	// ---------- CONFIRMADA ----------
	@Test
	void confirmadaHaceCheckIn() {
		new EstadoConfirmada().hacerCheckIn(reserva);
		verify(reserva).setEstadoActual(isA(EstadoCheckIn.class));
	}

	@Test
	void confirmadaCancelaLiberandoCupos() {
		new EstadoConfirmada().cancelar(reserva);
		InOrder orden = inOrder(reserva);
		orden.verify(reserva).liberarCupos();
		orden.verify(reserva).setEstadoActual(isA(EstadoCancelada.class));
	}

	@Test
	void confirmadaRechazaLasDemasOperaciones() {
		EstadoReserva e = new EstadoConfirmada();
		assertInvalida(() -> e.agregarServicio(reserva, item));
		assertInvalida(() -> e.quitarServicio(reserva, item));
		assertInvalida(() -> e.confirmar(reserva));
		assertInvalida(() -> e.iniciar(reserva));
		assertInvalida(() -> e.finalizar(reserva));
	}

	// ---------- CHECK-IN ----------
	@Test
	void checkInInicia() {
		new EstadoCheckIn().iniciar(reserva);
		verify(reserva).setEstadoActual(isA(EstadoEnCurso.class));
	}

	@Test
	void checkInCancelaLiberandoCuposYReembolsandoTodo() {
		new EstadoCheckIn().cancelar(reserva);
		InOrder orden = inOrder(reserva);
		orden.verify(reserva).liberarCupos();
		orden.verify(reserva).procesarReembolsoCompleto();
		orden.verify(reserva).setEstadoActual(isA(EstadoCancelada.class));
	}

	@Test
	void checkInRechazaLasDemasOperaciones() {
		EstadoReserva e = new EstadoCheckIn();
		assertInvalida(() -> e.agregarServicio(reserva, item));
		assertInvalida(() -> e.quitarServicio(reserva, item));
		assertInvalida(() -> e.confirmar(reserva));
		assertInvalida(() -> e.hacerCheckIn(reserva));
		assertInvalida(() -> e.finalizar(reserva));
	}

	// ---------- EN CURSO ----------
	@Test
	void enCursoFinaliza() {
		new EstadoEnCurso().finalizar(reserva);
		verify(reserva).setEstadoActual(isA(EstadoFinalizada.class));
	}

	@Test
	void enCursoCancelaReembolsandoSoloServiciosYSinLiberarCupos() {
		new EstadoEnCurso().cancelar(reserva);
		InOrder orden = inOrder(reserva);
		orden.verify(reserva).reembolsarServicios();
		orden.verify(reserva).setEstadoActual(isA(EstadoCancelada.class));
		verifyNoMoreInteractions(reserva);
	}

	@Test
	void enCursoRechazaLasDemasOperaciones() {
		EstadoReserva e = new EstadoEnCurso();
		assertInvalida(() -> e.agregarServicio(reserva, item));
		assertInvalida(() -> e.quitarServicio(reserva, item));
		assertInvalida(() -> e.confirmar(reserva));
		assertInvalida(() -> e.hacerCheckIn(reserva));
		assertInvalida(() -> e.iniciar(reserva));
	}

	// ---------- TERMINALES ----------
	@Test
	void finalizadaRechazaTodo() {
		EstadoReserva e = new EstadoFinalizada();
		assertInvalida(() -> e.agregarServicio(reserva, item));
		assertInvalida(() -> e.quitarServicio(reserva, item));
		assertInvalida(() -> e.confirmar(reserva));
		assertInvalida(() -> e.hacerCheckIn(reserva));
		assertInvalida(() -> e.iniciar(reserva));
		assertInvalida(() -> e.finalizar(reserva));
		assertInvalida(() -> e.cancelar(reserva));
	}

	@Test
	void canceladaRechazaTodo() {
		EstadoReserva e = new EstadoCancelada();
		assertInvalida(() -> e.agregarServicio(reserva, item));
		assertInvalida(() -> e.quitarServicio(reserva, item));
		assertInvalida(() -> e.confirmar(reserva));
		assertInvalida(() -> e.hacerCheckIn(reserva));
		assertInvalida(() -> e.iniciar(reserva));
		assertInvalida(() -> e.finalizar(reserva));
		assertInvalida(() -> e.cancelar(reserva));
	}

	// ---------- tipo() ----------
	@Test
	void cadaEstadoDiceQueTipoEs() {
		assertEquals(TipoEstado.SOLICITUD_PENDIENTE, new EstadoSolicitudPendiente().tipo());
		assertEquals(TipoEstado.CONFIRMADA, new EstadoConfirmada().tipo());
		assertEquals(TipoEstado.EN_CHECKIN, new EstadoCheckIn().tipo());
		assertEquals(TipoEstado.EN_CURSO, new EstadoEnCurso().tipo());
		assertEquals(TipoEstado.FINALIZADA, new EstadoFinalizada().tipo());
		assertEquals(TipoEstado.CANCELADA, new EstadoCancelada().tipo());
	}

	@Test
	void tipoEstadoYExcepcionDeDominio() {
		assertEquals(6, TipoEstado.values().length);
		assertEquals("x", new OperacionInvalidaException("x").getMessage());
	}
}