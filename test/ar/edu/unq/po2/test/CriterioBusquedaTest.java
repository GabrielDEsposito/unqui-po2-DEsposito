package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Categoria;
import ar.edu.unq.po2.integrador.CriterioAnd;
import ar.edu.unq.po2.integrador.CriterioCategoria;
import ar.edu.unq.po2.integrador.CriterioDisponibilidad;
import ar.edu.unq.po2.integrador.CriterioNombre;
import ar.edu.unq.po2.integrador.CriterioNot;
import ar.edu.unq.po2.integrador.CriterioOr;
import ar.edu.unq.po2.integrador.CriterioPrecioMaximo;
import ar.edu.unq.po2.integrador.ItemCatalogo;

public class CriterioBusquedaTest {

	private ItemCatalogo hotel;

	@BeforeEach
	void setUp() {
		hotel = mock(ItemCatalogo.class);
		when(hotel.getNombre()).thenReturn("Hotel Central");
		when(hotel.getPrecioBase()).thenReturn(15000.0);
		when(hotel.perteneceACategoria(Categoria.ALOJAMIENTO)).thenReturn(true);
		when(hotel.tieneCupoDisponible()).thenReturn(true);
	}

	@Test
	void nombreContieneSinDistinguirMayusculas() {
		assertTrue(new CriterioNombre("HOTEL").cumple(hotel));
		assertTrue(new CriterioNombre("central").cumple(hotel));
		assertFalse(new CriterioNombre("resort").cumple(hotel));
	}

	@Test
	void precioMaximoIncluyeElLimite() {
		assertTrue(new CriterioPrecioMaximo(15000).cumple(hotel));
		assertTrue(new CriterioPrecioMaximo(20000).cumple(hotel));
		assertFalse(new CriterioPrecioMaximo(14999).cumple(hotel));
	}

	@Test
	void categoria() {
		assertTrue(new CriterioCategoria(Categoria.ALOJAMIENTO).cumple(hotel));
		assertFalse(new CriterioCategoria(Categoria.VUELO).cumple(hotel));
	}

	@Test
	void disponibilidad() {
		assertTrue(new CriterioDisponibilidad().cumple(hotel));
		when(hotel.tieneCupoDisponible()).thenReturn(false);
		assertFalse(new CriterioDisponibilidad().cumple(hotel));
	}

	@Test
	void andRequiereTodos() {
		assertTrue(new CriterioAnd(
				new CriterioCategoria(Categoria.ALOJAMIENTO),
				new CriterioPrecioMaximo(20000),
				new CriterioDisponibilidad()).cumple(hotel));
		assertFalse(new CriterioAnd(
				new CriterioCategoria(Categoria.ALOJAMIENTO),
				new CriterioPrecioMaximo(1000)).cumple(hotel));
	}

	@Test
	void orRequiereAlMenosUno() {
		assertTrue(new CriterioOr(
				new CriterioNombre("resort"),
				new CriterioNombre("hotel")).cumple(hotel));
		assertFalse(new CriterioOr(
				new CriterioNombre("resort"),
				new CriterioCategoria(Categoria.VUELO)).cumple(hotel));
	}

	@Test
	void notNiega() {
		assertTrue(new CriterioNot(new CriterioCategoria(Categoria.EXCURSION)).cumple(hotel));
		assertFalse(new CriterioNot(new CriterioCategoria(Categoria.ALOJAMIENTO)).cumple(hotel));
	}

	@Test
	void criteriosAnidados() {
		// (Alojamiento AND disponible) OR nombre contiene "oferta"
		assertTrue(new CriterioOr(
				new CriterioAnd(new CriterioCategoria(Categoria.ALOJAMIENTO), new CriterioDisponibilidad()),
				new CriterioNombre("oferta")).cumple(hotel));

		// NOT(precio <= 10000) AND (Alojamiento OR Excursion)
		assertTrue(new CriterioAnd(
				new CriterioNot(new CriterioPrecioMaximo(10000)),
				new CriterioOr(
						new CriterioCategoria(Categoria.ALOJAMIENTO),
						new CriterioCategoria(Categoria.EXCURSION))).cumple(hotel));
	}
}