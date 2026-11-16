package ar.edu.unq.po2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.EstadoCancelada;
import ar.edu.unq.po2.integrador.EstadoConfirmada;
import ar.edu.unq.po2.integrador.EstadoTraslado;
import ar.edu.unq.po2.integrador.ItemCatalogo;

import ar.edu.unq.po2.integrador.Reserva;
import ar.edu.unq.po2.integrador.TrasladoEstandar;
import ar.edu.unq.po2.integrador.Voucher;

public class ReservaTest {
	
	private Reserva reserva;
	private EstadoCancelada reservaCancelada;
    private ItemCatalogo ExcursiónGuiadaMuseo;
    private EstadoConfirmada estadoConfirmada;
    private EstadoTraslado trasladoExpress;
    
    
    @BeforeEach
    void setUp() {
    	reserva = new Reserva();
    	reserva.setCuposDisponibles(5);
    	
    	
        ExcursiónGuiadaMuseo = mock(ItemCatalogo.class);
        trasladoExpress = mock(EstadoTraslado.class);
    }

    @Test
    void testAgregarYQuitarServicio() {
    	reserva.agregarServicio(ExcursiónGuiadaMuseo);
    	
    	assertEquals(ExcursiónGuiadaMuseo, reserva.getItems().get(0));
    }
    
    @Test
    void testElclienteConfirmaReservaYseRestaCupo() {
    	estadoConfirmada = new EstadoConfirmada();
		estadoConfirmada.confirmada_pagada(reserva, ExcursiónGuiadaMuseo);
		assertEquals(4, reserva.getCuposDisponibles());
    }
    
    @Test
    void clienteCancelaYSeLiberaCupoYSeReembolsaDinero() {
    	reserva.setEstrategiaDeTraslado(trasladoExpress);
    	reservaCancelada = new EstadoCancelada();
    	when(trasladoExpress.calcularCostoTraslado(reserva)).thenReturn(5000.0);
    	when(ExcursiónGuiadaMuseo.getPrecioBase()).thenReturn(5000.0);
    	reserva.agregarServicio(ExcursiónGuiadaMuseo);
    	reserva.liberarCupo();
    	reserva.procesarReembolsoCompleto();
    	Voucher voucherGenerado = reserva.getVouchers().get(0);
    	assertEquals(6, reserva.getCuposDisponibles());
        assertEquals(10000.0, voucherGenerado.getMonto(), 0.01);
    }

	
    
    
}
