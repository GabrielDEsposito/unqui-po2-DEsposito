package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.PaqueteTuristico;

public class PaqueteTuristicoTest {

	private PaqueteTuristico paquete;
	private ItemCatalogo excursionGuiadaMuseo;
	private ItemCatalogo nocheDeHotelCentral;
	private ItemCatalogo trasladoAeropuertoHotel;

	@BeforeEach
	void setUp() {
		paquete = new PaqueteTuristico("Pack Escapada Urbana", "Paquete con descuento", 10.0);

		excursionGuiadaMuseo = mock(ItemCatalogo.class);
		nocheDeHotelCentral = mock(ItemCatalogo.class);
		trasladoAeropuertoHotel = mock(ItemCatalogo.class);
	}

	@Test
	void precioBaseYFinalDePaqueteConMocks() {
		when(nocheDeHotelCentral.getPrecioBase()).thenReturn(15000.0);
		when(excursionGuiadaMuseo.getPrecioBase()).thenReturn(4000.0);
		when(trasladoAeropuertoHotel.getPrecioBase()).thenReturn(2000.0);

		// Sin descuento propio, el precio final de cada servicio es igual al base
		when(nocheDeHotelCentral.getPrecioFinal()).thenReturn(15000.0);
		when(excursionGuiadaMuseo.getPrecioFinal()).thenReturn(4000.0);
		when(trasladoAeropuertoHotel.getPrecioFinal()).thenReturn(2000.0);

		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);
		paquete.agregarItem(trasladoAeropuertoHotel);

		assertEquals(21000.0, paquete.getPrecioBase(), 0.001);   // suma de las partes
		assertEquals(18900.0, paquete.getPrecioFinal(), 0.001);  // 21000 con 10 % de descuento
	}
}
