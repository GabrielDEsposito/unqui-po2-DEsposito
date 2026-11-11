package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.PaqueteTuristico;

public class PaqueteTuristicoTest {

    private PaqueteTuristico paquete;
    private ItemCatalogo ExcursiónGuiadaMuseo;
    private ItemCatalogo NocheDeHotelCentral;
    private ItemCatalogo TrasladoAeropuertoHotel;

    @BeforeEach
    void setUp() {
        
        paquete = new PaqueteTuristico("Pack Escapada Urbana", "Paquete con descuento", 10.0);

        
        ExcursiónGuiadaMuseo = mock(ItemCatalogo.class);
        NocheDeHotelCentral = mock(ItemCatalogo.class);
        TrasladoAeropuertoHotel = mock(ItemCatalogo.class);
    }

    @Test
    void testPrecioBaseYFinalDePaqueteConMocks() {
        //Definimos el comportamiento simulado (Stubbing) de los precios base
        when(ExcursiónGuiadaMuseo.getPrecioBase()).thenReturn(15000.0);
        when(NocheDeHotelCentral.getPrecioBase()).thenReturn(4000.0);
        when(TrasladoAeropuertoHotel.getPrecioBase()).thenReturn(2000.0);

       
        paquete.agregarItem(ExcursiónGuiadaMuseo);
        paquete.agregarItem(NocheDeHotelCentral);
        paquete.agregarItem(TrasladoAeropuertoHotel);

        //Ejecución y Verificación del Precio Base (Suma de las partes: 15000 + 4000 + 2000 = 21000)
        assertEquals(21000.0, paquete.getPrecioBase());

        // Verificación del Precio Final (Aplicando el 10% de descuento: 21000 - 10% = 18000)
        assertEquals(18900.0, paquete.getPrecioFinal());

        // Verificación de interacciones (Comprobamos que Mockito efectivamente llamó a los métodos)
        //verify(ExcursiónGuiadaMuseo, times(2)).getPrecioBase();
        //verify(NocheDeHotelCentral, times(2)).getPrecioBase();
        //verify(TrasladoAeropuertoHotel, times(2)).getPrecioBase();
    }
}
