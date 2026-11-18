package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Categoria;
import ar.edu.unq.po2.integrador.Cliente;

public class ClienteTest {

	@Test
	void guardaNombreYEmail() {
		Cliente c = new Cliente("Ana", "ana@mail.com");
		assertEquals("Ana", c.getNombre());
		assertEquals("ana@mail.com", c.getEmail());
	}
}
