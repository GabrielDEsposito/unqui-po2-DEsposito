package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.TrasladoVip;

public class TrasladoVipTest {

	private TrasladoVip trasladoVip;
	private Reserva reserva;

	@BeforeEach
	void setUp() {
	
		trasladoVip = new TrasladoVip(0.2, 500.0);
		reserva = mock(Reserva.class);
	}

	@Test
	void elCostoDelTrasladoEsElPorcentajeDelCostoDeLosServiciosMasElCargoBase() {
		when(reserva.costoServicio()).thenReturn(10000.0);

		// 10000 * 0.2 + 500 = 2500
		assertEquals(2500.0, trasladoVip.calcularCostoTraslado(reserva), 0.001);
	}

	@Test
	void sinServiciosElCostoDelTrasladoEsSoloElCargoBase() {
		when(reserva.costoServicio()).thenReturn(0.0);

		assertEquals(500.0, trasladoVip.calcularCostoTraslado(reserva), 0.001);
	}

	@Test
	void conPorcentajeCeroElCostoDelTrasladoEsSoloElCargoBase() {
		TrasladoVip trasladoSinPorcentaje = new TrasladoVip(0.0, 500.0);
		when(reserva.costoServicio()).thenReturn(10000.0);

		assertEquals(500.0, trasladoSinPorcentaje.calcularCostoTraslado(reserva), 0.001);
	}

	@Test
	void sinCargoBaseElCostoDelTrasladoEsSoloElPorcentaje() {
		TrasladoVip trasladoSinCargoBase = new TrasladoVip(0.2, 0.0);
		when(reserva.costoServicio()).thenReturn(10000.0);

		assertEquals(2000.0, trasladoSinCargoBase.calcularCostoTraslado(reserva), 0.001);
	}

	@Test
	void elCostoDelTrasladoSeCalculaConElCostoDeLosServiciosDeLaReserva() {
		when(reserva.costoServicio()).thenReturn(10000.0);

		trasladoVip.calcularCostoTraslado(reserva);

		verify(reserva).costoServicio();
	}
}