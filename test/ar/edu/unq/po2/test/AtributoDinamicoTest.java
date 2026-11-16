package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioIndividualTest {

    private Cupo cupo;
    private ServicioIndividual hotel;

    @BeforeEach
    void setUp() {
        cupo = mock(Cupo.class);
        hotel = new ServicioIndividual(1, "Noche de Hotel Central", "Hotel 4 estrellas",
                "Hoteles SA", Categoria.ALOJAMIENTO, 15000, 0, cupo);
    }

    @Test
    void sinDescuentoPrecioFinalEsIgualAlBase() {
        assertEquals(15000, hotel.getPrecioBase(), 0.001);
        assertEquals(15000, hotel.getPrecioFinal(), 0.001);
    }

}