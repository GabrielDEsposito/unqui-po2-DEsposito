package ar.edu.unq.po2.test;


import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.EstadoCancelada;
import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.OperacionInvalidaException;
import ar.edu.unq.po2.integrador.Reserva;

public class EstadoCanceladaTest {
	private EstadoCancelada estado;
	private Reserva reserva;
	private ItemCatalogo item;

	@BeforeEach
	void setUp() {
		estado = new EstadoCancelada();
		reserva = mock(Reserva.class);
		item = mock(ItemCatalogo.class);
	}

	@Test
	void testAgregarServicioLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.agregarServicio(reserva, item));
		verifyNoInteractions(reserva);
	}

	@Test
	void testQuitarServicioLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.quitarServicio(reserva, item));
		verifyNoInteractions(reserva);
	}

	@Test
	void testConfirmarLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.confirmar(reserva));
		verifyNoInteractions(reserva);
	}

	@Test
	void testHacerCheckInLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.hacerCheckIn(reserva));
		verifyNoInteractions(reserva);
	}

	@Test
	void testIniciarLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.iniciar(reserva));
		verifyNoInteractions(reserva);
	}

	@Test
	void testFinalizarLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.finalizar(reserva));
		verifyNoInteractions(reserva);
	}

	@Test
	void testCancelarLanzaExcepcion() {
		assertThrows(OperacionInvalidaException.class, () -> estado.cancelar(reserva));
		verifyNoInteractions(reserva);
	}
}
