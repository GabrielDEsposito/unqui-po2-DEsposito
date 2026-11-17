package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Catalogo;
import ar.edu.unq.po2.integrador.CriterioNombre;
import ar.edu.unq.po2.integrador.CriterioPrecioMaximo;
import ar.edu.unq.po2.integrador.CriterioAnd;
import ar.edu.unq.po2.integrador.ItemCatalogo;

public class CatalogoTest {

	private ItemCatalogo item(String nombre, double precio) {
		ItemCatalogo i = mock(ItemCatalogo.class);
		when(i.getNombre()).thenReturn(nombre);
		when(i.getPrecioBase()).thenReturn(precio);
		return i;
	}

	@Test
	void buscarDevuelveSoloLosQueCumplen() {
		Catalogo catalogo = new Catalogo();
		ItemCatalogo hotel = item("Hotel Central", 15000);
		ItemCatalogo museo = item("Excursión Museo", 4000);
		catalogo.agregar(hotel);
		catalogo.agregar(museo);

		List<ItemCatalogo> resultado = catalogo.buscar(
				new CriterioAnd(new CriterioNombre("hotel"), new CriterioPrecioMaximo(20000)));

		assertEquals(List.of(hotel), resultado);
	}

	@Test
	void buscarSinCoincidenciasDevuelveListaVacia() {
		Catalogo catalogo = new Catalogo();
		catalogo.agregar(item("Hotel", 100));

		assertTrue(catalogo.buscar(new CriterioNombre("resort")).isEmpty());
	}

	@Test
	void catalogoVacioDevuelveListaVacia() {
		assertTrue(new Catalogo().buscar(new CriterioNombre("x")).isEmpty());
	}
}