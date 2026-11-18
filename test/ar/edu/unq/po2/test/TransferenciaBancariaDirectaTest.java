package ar.edu.unq.po2.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ITransferenciaBancariaDirecta;
import ar.edu.unq.po2.integrador.TransferenciaBancariaDirecta;

public class TransferenciaBancariaDirectaTest {

	private static final String CBU = "0170099220000067797370";
	private static final String ALIAS = "juan.mp";

	private TransferenciaBancariaDirecta transferencia;
	private ITransferenciaBancariaDirecta servicioTransferencia;

	@BeforeEach
	void setUp() {
		servicioTransferencia = mock(ITransferenciaBancariaDirecta.class);
		transferencia = new TransferenciaBancariaDirecta(CBU, ALIAS, servicioTransferencia);
	}

	@Test
	void validarDatosConCbuYAliasValidosNoLanzaExcepcion() {
		when(servicioTransferencia.validarCbuYAlias(CBU, ALIAS)).thenReturn(true);

		transferencia.ValidarDatos();

		verify(servicioTransferencia).validarCbuYAlias(CBU, ALIAS);
	}

	@Test
	void validarDatosConCbuOAliasInvalidosLanzaExcepcion() {
		when(servicioTransferencia.validarCbuYAlias(CBU, ALIAS)).thenReturn(false);

		try {
			transferencia.ValidarDatos();
			fail("Deberia haber lanzado IllegalStateException");
		} catch (IllegalStateException e) {
			assertEquals("Datos de CBU o alias inválidos", e.getMessage());
		}
	}

	@Test
	void reservarFondosNoRequiereNingunaOperacionDelServicio() {
		transferencia.ReservarFondos(5000.0);

		verifyNoInteractions(servicioTransferencia);
	}

	@Test
	void ejecutarTransaccionDelegaEnElServicioConElMonto() {
		transferencia.EjecutarTransaccion(5000.0);

		verify(servicioTransferencia).transferenciaDirectaOProgramada(5000.0);
	}

	@Test
	void notificarResultadoAgregaUnComprobante() {
		assertEquals(0, transferencia.getComprobantes().size());

		transferencia.NotificarResultado(5000.0);

		assertEquals(1, transferencia.getComprobantes().size());
	}
}