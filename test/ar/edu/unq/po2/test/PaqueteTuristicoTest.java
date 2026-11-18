package ar.edu.unq.po2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Categoria;
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
	
	@Test
	void elPaqueteConoceSuDescuento() {
		assertEquals(10.0, paquete.getDescuento(), 0.001);
	}
	
	@Test
	void seAgreganYQuitanItemsDelPaquete() {
		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);

		assertEquals(2, paquete.getItems().size());
		assertEquals(nocheDeHotelCentral, paquete.getItems().get(0));

		paquete.quitarItem(nocheDeHotelCentral);

		assertEquals(1, paquete.getItems().size());
		assertFalse(paquete.getItems().contains(nocheDeHotelCentral));
	}
	
	@Test
	void elPaqueteNoPerteneceACategoriaSiNingunItemPertenece() {
		Categoria categoria = Categoria.values()[0];
		when(nocheDeHotelCentral.perteneceACategoria(categoria)).thenReturn(false);
		when(excursionGuiadaMuseo.perteneceACategoria(categoria)).thenReturn(false);

		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);

		assertFalse(paquete.perteneceACategoria(categoria));
	}
	
	@Test
	void unPaqueteVacioNoPerteneceANingunaCategoria() {
		assertFalse(paquete.perteneceACategoria(Categoria.values()[0]));
	}

	@Test
	void unPaqueteVacioNoTieneCupoDisponible() {
		assertFalse(paquete.tieneCupoDisponible());
	}
	
	@Test
	void elPaqueteTieneCupoSiTodosSusItemsTienenCupo() {
		when(nocheDeHotelCentral.tieneCupoDisponible()).thenReturn(true);
		when(excursionGuiadaMuseo.tieneCupoDisponible()).thenReturn(true);
		when(trasladoAeropuertoHotel.tieneCupoDisponible()).thenReturn(true);

		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);
		paquete.agregarItem(trasladoAeropuertoHotel);

		assertTrue(paquete.tieneCupoDisponible());
	}
	
	@Test
	void elPaqueteNoTieneCupoSiAlgunItemNoTieneCupo() {
		when(nocheDeHotelCentral.tieneCupoDisponible()).thenReturn(true);
		when(excursionGuiadaMuseo.tieneCupoDisponible()).thenReturn(false);

		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);

		assertFalse(paquete.tieneCupoDisponible());
	}

	@Test
	void unPaqueteSeContieneASiMismo() {
		assertTrue(paquete.contiene(paquete));
	}
	
	@Test
	void elPaqueteContieneUnItemSiAlgunoDeSusItemsLoContiene() {
		when(nocheDeHotelCentral.contiene(trasladoAeropuertoHotel)).thenReturn(false);
		when(excursionGuiadaMuseo.contiene(trasladoAeropuertoHotel)).thenReturn(true);

		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);

		assertTrue(paquete.contiene(trasladoAeropuertoHotel));
	}

	@Test
	void elPaqueteNoContieneUnItemQueNingunoDeSusItemsContiene() {
		when(nocheDeHotelCentral.contiene(trasladoAeropuertoHotel)).thenReturn(false);
		when(excursionGuiadaMuseo.contiene(trasladoAeropuertoHotel)).thenReturn(false);

		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);

		assertFalse(paquete.contiene(trasladoAeropuertoHotel));
	}
	
	@Test
	void reservarCupoDelPaqueteLoReservaEnCadaItem() {
		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);
		paquete.agregarItem(trasladoAeropuertoHotel);

		paquete.reservarCupo(2);

		verify(nocheDeHotelCentral).reservarCupo(2);
		verify(excursionGuiadaMuseo).reservarCupo(2);
		verify(trasladoAeropuertoHotel).reservarCupo(2);
	}

	@Test
	void liberarCupoDelPaqueteLoLiberaEnCadaItem() {
		paquete.agregarItem(nocheDeHotelCentral);
		paquete.agregarItem(excursionGuiadaMuseo);
		paquete.agregarItem(trasladoAeropuertoHotel);

		paquete.liberarCupo(2);

		verify(nocheDeHotelCentral).liberarCupo(2);
		verify(excursionGuiadaMuseo).liberarCupo(2);
		verify(trasladoAeropuertoHotel).liberarCupo(2);
	}
}
