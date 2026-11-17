package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reserva implements Visitable {
	private final Cliente cliente;
	private List<ItemCatalogo> items = new ArrayList<>();
	private List<Voucher> vouchers = new ArrayList<>();
	//private float tazas;
	private EstadoReserva estadoActual;
	private EstadoTraslado trasladoActual;
	private List<ObservadorReserva> observadores = new ArrayList<>();
	private MetodoPago metodoPago;
	private String fecha;
	
	public Reserva(Cliente cliente) {
		this.cliente = cliente;
		this.items = new ArrayList<>();
	    this.vouchers = new ArrayList<>();
	    this.observadores = new ArrayList<>();
		this.estadoActual = new EstadoSolicitudPendiente();
		
	}
	
	public String getEmailCliente() {
		return this.cliente.getEmail();
	}
	
	public List<Voucher> getVouchers(){
		return vouchers;
	}
	
	public void setMetodoPago(MetodoPago metodo) {
		this.metodoPago = metodo;
	}
	
	public MetodoPago getMetodoPago() {
		return metodoPago;
	}
	
	public EstadoReserva getEstadoActual() {
		return estadoActual;
	}
	
	public void setEstadoActual(EstadoReserva nuevo) {
		EstadoReserva anterior = this.estadoActual;
		this.estadoActual = nuevo;
		this.notificarObservadores(anterior, nuevo);
	}
	
	public void agregarServicio(ItemCatalogo item) {
		this.items.add(item); //estadoActual.agregarServicio(this, item);
	}

	public void quitarServicio(ItemCatalogo item) {
		this.items.remove(item); 
	}
	public void incorporarServicio(ItemCatalogo item) {
		items.add(item);
	}

	public void retirarServicio(ItemCatalogo item) {
		items.remove(item);
	}
	public void agregarObservador(ObservadorReserva observador) {
		observadores.add(observador);
	}
	
	public void quitarObservador(ObservadorReserva observador) {
		observadores.remove(observador);
	}
	
	private void notificarObservadores(EstadoReserva anterior, EstadoReserva nuevo) {
		for(ObservadorReserva obs: observadores) {
			obs.actualizar(this, anterior, nuevo);
		}
	}
	
	public void reservarCupos() {
		for (ItemCatalogo item : items) {
			if (!item.tieneCupoDisponible()) {
				throw new CupoInsuficienteException("Sin cupo para: " + item.getNombre());
			}
		}
		for (ItemCatalogo item : items) {
			item.reservarCupo(1);
		}
	}

	public void liberarCupos() {
		for (ItemCatalogo item : items) {
			item.liberarCupo(1);
		}
	}
	
	public double costoServicio() {
		float totalServicios = 0;
	    for (ItemCatalogo item : items) { 
	        totalServicios += item.getPrecioFinal();
	    }
	    return totalServicios;
	}
	
	
	public void generarVoucherReembolso(double monto) {
	    Voucher voucher = new Voucher(monto);
	    this.vouchers.add(voucher);
	}
	
	public void procesarReembolsoCompleto() {
	    double totalReembolso = this.costoServicio() + this.costoTraslado(); //asd
	    this.generarVoucherReembolso(totalReembolso);
	    
	}
	
	public void reembolsarServicios() {
		double totalReembolso = this.costoServicio();
	    this.generarVoucherReembolso(totalReembolso);
	}	
	
	
	
	private double costoTraslado() {
		return this.getEstrategiaDeTraslado().calcularCostoTraslado(this);
		 
	}
	
	public EstadoTraslado getEstrategiaDeTraslado() {
		return trasladoActual;
	}
	
	public void setEstrategiaDeTraslado(EstadoTraslado trasladoActual) {
		this.trasladoActual = trasladoActual;
	}
	
	public List<ItemCatalogo> getItems(){
		return Collections.unmodifiableList(items);
		
	}

	public void confirmar() {
		estadoActual.confirmar(this);
	}

	public void hacerCheckIn() {
		estadoActual.hacerCheckIn(this);
	}

	public void iniciar() {
		estadoActual.iniciar(this);
	}

	public void finalizar() {
		estadoActual.finalizar(this);
	}

	public void cancelar() {
		estadoActual.cancelar(this);
	}
	
    @Override
    public void aceptar(ReporteVisitor visitor) { 
		visitor.visitarReserva(this); 
	}

	public String getFecha() { return fecha; }
	public void setFecha(String fecha) { this.fecha = fecha; }

	public boolean estaEnPeriodo(String desde, String hasta) {
    return fecha != null && fecha.compareTo(desde) >= 0 && fecha.compareTo(hasta) <= 0;
	}
}
