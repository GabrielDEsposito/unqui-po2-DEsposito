package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Cupo;
import ar.edu.unq.po2.integrador.EstadoCancelada;
import ar.edu.unq.po2.integrador.EstadoConfirmada;
import ar.edu.unq.po2.integrador.EstadoTraslado;
import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.Voucher;
import ar.edu.unq.po2.integrador.Cliente;

public class ReservaTest {
	
	private Cliente cliente;
	private Reserva reserva;
	private Cupo cupo;
	private EstadoCancelada reservaCancelada;
    private ItemCatalogo ExcursiónGuiadaMuseo;
    private EstadoConfirmada estadoConfirmada;
    private EstadoTraslado trasladoExpress;
    
    
    @BeforeEach
    void setUp() {
    	cliente = new Cliente("Juan", "juan123@gmail.com");
    	reserva = new Reserva(cliente);
    	cupo = new Cupo(6);
    	cupo.setReservados(4);
    	
    	
    	ExcursiónGuiadaMuseo =  mock(ItemCatalogo.class);
        
    	trasladoExpress = mock(EstadoTraslado.class);
        
    }

    @Test
    void testAgregarYQuitarServicio() {
    	reserva.agregarServicio(ExcursiónGuiadaMuseo);
    	
    	assertEquals(ExcursiónGuiadaMuseo, reserva.getItems().get(0));
    }
    
    @Test
    void testElclienteConfirmaReservaYseRestaCupo() {
    	
    	 	when(ExcursiónGuiadaMuseo.tieneCupoDisponible()).thenReturn(true);
    	    reserva.agregarServicio(ExcursiónGuiadaMuseo);

    	    reserva.confirmar();

    	    verify(ExcursiónGuiadaMuseo).reservarCupo(1);
    	
    	
 
    }
   

    @Test
    void clienteCancelaYSeLiberaCupoYSeReembolsaDinero() {
    	 reserva.setEstrategiaDeTraslado(trasladoExpress);
    	    when(trasladoExpress.calcularCostoTraslado(reserva)).thenReturn(5000.0);
    	    when(ExcursiónGuiadaMuseo.getPrecioFinal()).thenReturn(5000.0);
    	 
    	    reserva.agregarServicio(ExcursiónGuiadaMuseo);

    	    reserva.liberarCupos();
    	    reserva.procesarReembolsoCompleto();

    	    verify(ExcursiónGuiadaMuseo).liberarCupo(1);
    	    Voucher voucherGenerado = reserva.getVouchers().get(0);

    	    assertEquals(10000.0, voucherGenerado.getMonto(), 0.01);
    	   
    }
    
   //funciona
}