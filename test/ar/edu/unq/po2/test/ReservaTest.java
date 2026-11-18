package ar.edu.unq.po2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.integrador.Cupo;
import ar.edu.unq.po2.integrador.CupoInsuficienteException;
import ar.edu.unq.po2.integrador.EstadoCancelada;
import ar.edu.unq.po2.integrador.EstadoConfirmada;
import ar.edu.unq.po2.integrador.EstadoReserva;
import ar.edu.unq.po2.integrador.EstadoTraslado;
import ar.edu.unq.po2.integrador.ItemCatalogo;
import ar.edu.unq.po2.integrador.MetodoPago;
import ar.edu.unq.po2.integrador.ObservadorReserva;
import ar.edu.unq.po2.integrador.ReporteVisitor;
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
    private EstadoReserva estadoMock; 
    private ObservadorReserva observadorMail;   // mock(ObservadorReserva.class)
    private ObservadorReserva observadorPush;   // mock(ObservadorReserva.class)
    
    @BeforeEach
    void setUp() {
    	cliente = new Cliente("Juan", "juan123@gmail.com");
    	reserva = new Reserva(cliente);
    	cupo = new Cupo(6);
    	cupo.setReservados(4);
    	
    	estadoMock = mock(EstadoReserva.class);
    	observadorMail = mock(ObservadorReserva.class);
    	observadorPush = mock(ObservadorReserva.class);
    	
    	ExcursiónGuiadaMuseo =  mock(ItemCatalogo.class);
        
    	trasladoExpress = mock(EstadoTraslado.class);
        
    }

    @Test
    void testAgregarServicio() {
    	reserva.agregarServicio(ExcursiónGuiadaMuseo);
    	
    	assertEquals(ExcursiónGuiadaMuseo, reserva.getItems().get(0));
    }
    
    @Test
    void testQuitarServicio() {
    	reserva.agregarServicio(ExcursiónGuiadaMuseo);
    	reserva.quitarServicio(ExcursiónGuiadaMuseo);
    	
    	assertEquals(0, reserva.getItems().size());
    }
    
    
    
    
    @Test
    void testElclienteConfirmaReservaYseRestaCupo() {
       	 	when(ExcursiónGuiadaMuseo.tieneCupoDisponible()).thenReturn(true);
    	    reserva.agregarServicio(ExcursiónGuiadaMuseo);

    	    reserva.confirmar();

    	    verify(ExcursiónGuiadaMuseo).reservarCupo(1);
    }
    
    @Test
    void unObservadorEsNotificadoCuandoCambiaElEstado() {
        reserva.agregarObservador(observadorMail);
        EstadoReserva estadoAnterior = reserva.getEstadoActual();

        reserva.setEstadoActual(estadoMock);

        verify(observadorMail).actualizar(reserva, estadoAnterior, estadoMock);
    }

    @Test
    void todosLosObservadoresSonNotificadosCuandoCambiaElEstado() {
        reserva.agregarObservador(observadorMail);
        reserva.agregarObservador(observadorPush);
        EstadoReserva estadoAnterior = reserva.getEstadoActual();

        reserva.setEstadoActual(estadoMock);

        verify(observadorMail).actualizar(reserva, estadoAnterior, estadoMock);
        verify(observadorPush).actualizar(reserva, estadoAnterior, estadoMock);
    }

    @Test
    void unObservadorQuitadoYaNoEsNotificado() {
        reserva.agregarObservador(observadorMail);
        reserva.quitarObservador(observadorMail);

        reserva.setEstadoActual(estadoMock);

        verifyNoInteractions(observadorMail);
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
    	   
    } //este test hace el reembolso completo servicios + traslado
    
    @Test
    void testReembolsarServiciosGeneraVoucherSoloConElCostoDeLosServicios() {
        when(ExcursiónGuiadaMuseo.getPrecioFinal()).thenReturn(5000.0);
        reserva.agregarServicio(ExcursiónGuiadaMuseo);

        reserva.reembolsarServicios();

        assertEquals(1, reserva.getVouchers().size());
        assertEquals(5000.0, reserva.getVouchers().get(0).getMonto(), 0.01);
    }//este test hace el reembolso solamente de servicios
    
    
    @Test
    void getClienteDevuelveElClienteDeLaReserva() {
        Cliente cliente = mock(Cliente.class);
        Reserva reserva = new Reserva(cliente);

        assertSame(cliente, reserva.getCliente());
    }
    
    @Test
    void setMetodoPagoGuardaElMetodoDePago() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        MetodoPago metodo = mock(MetodoPago.class);

        reserva.setMetodoPago(metodo);

        assertSame(metodo, reserva.getMetodoPago());
    }
    
    @Test
    void reservarCuposSinCupoLanzaCupoInsuficienteException() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        ItemCatalogo sinCupo = mock(ItemCatalogo.class);
        when(sinCupo.tieneCupoDisponible()).thenReturn(false);
        when(sinCupo.getNombre()).thenReturn("Hotel Sol");
        reserva.agregarServicio(sinCupo);

        try {
            reserva.reservarCupos();
            fail("Debería haber lanzado CupoInsuficienteException");
        } catch (CupoInsuficienteException e) {
            assertTrue(e.getMessage().contains("Hotel Sol"));
        }
    }
    
    @Test
    void registrarVoucherLoAgregaALosVouchersDeLaReserva() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        Voucher voucher = mock(Voucher.class);

        reserva.registrarVoucher(voucher);

        assertEquals(1, reserva.getVouchers().size());
        assertSame(voucher, reserva.getVouchers().get(0));
    }

    @Test
    void hacerCheckInDelegaEnElEstadoActual() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        EstadoReserva estado = mock(EstadoReserva.class);
        reserva.setEstadoActual(estado);

        reserva.hacerCheckIn();

        verify(estado).hacerCheckIn(reserva);
    }
    
    @Test
    void iniciarDelegaEnElEstadoActual() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        EstadoReserva estado = mock(EstadoReserva.class);
        reserva.setEstadoActual(estado);

        reserva.iniciar();

        verify(estado).iniciar(reserva);
    }

    @Test
    void finalizarDelegaEnElEstadoActual() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        EstadoReserva estado = mock(EstadoReserva.class);
        reserva.setEstadoActual(estado);

        reserva.finalizar();

        verify(estado).finalizar(reserva);
    }

    @Test
    void cancelarDelegaEnElEstadoActual() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        EstadoReserva estado = mock(EstadoReserva.class);
        reserva.setEstadoActual(estado);

        reserva.cancelar();

        verify(estado).cancelar(reserva);
    }

    @Test
    void aceptarLeDiceAlVisitorQueVisiteLaReserva() {
        Reserva reserva = new Reserva(mock(Cliente.class));
        ReporteVisitor visitor = mock(ReporteVisitor.class);

        reserva.aceptar(visitor);

        verify(visitor).visitarReserva(reserva);
    }
    
    @Test
    void setFechaGuardaLaFecha() {
        Reserva reserva = new Reserva(mock(Cliente.class));

        reserva.setFecha("2026-06-15");

        assertEquals("2026-06-15", reserva.getFecha());
    }

    @Test
    void sinFechaNoEstaEnPeriodo() {
        Reserva reserva = new Reserva(mock(Cliente.class));

        assertFalse(reserva.estaEnPeriodo("2026-01-01", "2026-12-31"));
    }

}


