package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
	private List<ItemCatalogo> items = new ArrayList<>();
	private List<Voucher> vouchers = new ArrayList<>();
	//private float tazas;
	private EstadoReserva estadoActual;
	private EstadoTraslado trasladoActual;
	private List<ObservadorReserva> observadores = new ArrayList<>();
	
	public Reserva() {
		this.estadoActual = new EstadoSolicitudPendiente();
	}
	
	public List<Voucher> getVouchers(){
		return vouchers;
	}
	
	public void setEstadoActual(EstadoReserva nuevo) {
		EstadoReserva anterior = this.estadoActual;
		this.estadoActual = nuevo;
		this.notificarObservadores(anterior, nuevo);
	}
	
	public void agregarServicio(ItemCatalogo item) {
		estadoActual.agregarServicio(this, item);
	}

	public void quitarServicio(ItemCatalogo item) {
		estadoActual.quitarServicio(this, item);
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
	
	public void notificarObservadores(EstadoReserva anterior, EstadoReserva nuevo) {
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
	
	public float costoServicio() {
		float totalServicios = 0;
	    for (ItemCatalogo item : items) { //antes estaba Catalogo item
	        totalServicios += item.getPrecioBase();
	    }
	    return totalServicios;
	}
	
	
	public void generarVoucherReembolso(double monto) {
	    Voucher voucher = new Voucher(monto);
	    this.vouchers.add(voucher);
	}
	
	public void procesarReembolsoCompleto() {
	    double totalReembolso = this.costoServicio() + this.costoTraslado(); //metodo privado costoTraslado
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
		return items;
		
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
	
}
