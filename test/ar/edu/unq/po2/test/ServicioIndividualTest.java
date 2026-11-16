package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Categoria;
import ar.edu.unq.po2.integrador.Cupo;
import ar.edu.unq.po2.integrador.ServicioIndividual;

public class ServicioIndividualTest {

	@Test
	void servicioIndividualRespondeConSusDatos() {
		ServicioIndividual hotel = new ServicioIndividual(1, "Hotel Central", "Centro", "OperadorX",
				Categoria.ALOJAMIENTO, 15000, 10, new Cupo(5));

		assertTrue(hotel.perteneceACategoria(Categoria.ALOJAMIENTO));
		assertFalse(hotel.perteneceACategoria(Categoria.VUELO));
		assertTrue(hotel.tieneCupoDisponible());
		assertEquals(13500.0, hotel.getPrecioFinal(), 0.001);
	}

	@Test
	void descuentoFueraDeRangoLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> new ServicioIndividual(1, "Hotel", "desc", "Op",
				Categoria.ALOJAMIENTO, 1000, 101, new Cupo(5)));
		assertThrows(IllegalArgumentException.class, () -> new ServicioIndividual(1, "Hotel", "desc", "Op",
				Categoria.ALOJAMIENTO, 1000, -1, new Cupo(5)));
	}

	@Test
	void getters() {
		Cupo cupo = new Cupo(5);
		ServicioIndividual s = new ServicioIndividual(7, "Hotel", "desc", "OperadorX",
				Categoria.ALOJAMIENTO, 1000, 0, cupo);

		assertEquals(7, s.getReservaId());
		assertEquals("OperadorX", s.getProveedor());
		assertEquals(Categoria.ALOJAMIENTO, s.getCategoria());
		assertSame(cupo, s.getCupo());
		assertEquals(1000.0, s.getPrecioBase(), 0.001);
		assertEquals(1000.0, s.getPrecioFinal(), 0.001);
	}

	@Test
	void sinCupoNoHayDisponibilidad() {
		ServicioIndividual s = new ServicioIndividual(1, "Hotel", "desc", "Op",
				Categoria.ALOJAMIENTO, 1000, 0, new Cupo(0));
		assertFalse(s.tieneCupoDisponible());
	}

	@Test
	void descuentosEnLosLimitesSonValidos() {
		ServicioIndividual gratis = new ServicioIndividual(1, "H", "d", "Op",
				Categoria.ALOJAMIENTO, 1000, 100, new Cupo(1));
		ServicioIndividual sinDescuento = new ServicioIndividual(2, "H", "d", "Op",
				Categoria.ALOJAMIENTO, 1000, 0, new Cupo(1));

		assertEquals(0.0, gratis.getPrecioFinal(), 0.001);
		assertEquals(1000.0, sinDescuento.getPrecioFinal(), 0.001);
	}
}