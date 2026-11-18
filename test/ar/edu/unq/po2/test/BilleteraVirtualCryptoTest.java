package ar.edu.unq.po2.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.BilleteraVirtualCrypto;
import ar.edu.unq.po2.integrador.IBilleteraVirtualCrypto;

public class BilleteraVirtualCryptoTest {

	private static final int SALDO = 100000;

	private BilleteraVirtualCrypto billetera;
	private IBilleteraVirtualCrypto servicioBilletera;

	@BeforeEach
	void setUp() {
		servicioBilletera = mock(IBilleteraVirtualCrypto.class);
		billetera = new BilleteraVirtualCrypto(SALDO, servicioBilletera);
	}

	@Test
	void validarDatosConsultaSiElSaldoEsSuficienteEnLaCuenta() {
		billetera.ValidarDatos();

		verify(servicioBilletera).saldoSuficienteEnCuenta(SALDO);
	}

	@Test
	void reservarFondosBloqueaElSaldoHastaConfirmar() {
		billetera.ReservarFondos(5000.0);

		verify(servicioBilletera).bloqueoDelSaldoHastaConfirmar(5000.0);
	}

	@Test
	void ejecutarTransaccionAcreditaEnTiempoReal() {
		billetera.EjecutarTransaccion(5000.0);

		verify(servicioBilletera).acreditacionEnTiempoReal(5000.0);
	}

	@Test
	void notificarResultadoEnviaUnaNotificacionPush() {
		billetera.NotificarResultado(5000.0);

		verify(servicioBilletera).notificacionPush(5000.0);
	}
}