package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Categoria;
import ar.edu.unq.po2.integrador.Cliente;

public class CategoriaTest {

	@Test
	void categoriaTieneLosCuatroValoresDelEnunciado() {
		assertEquals(4, Categoria.values().length);
		assertEquals(Categoria.ALQUILER_DE_AUTO, Categoria.valueOf("ALQUILER_DE_AUTO"));
	}
}
