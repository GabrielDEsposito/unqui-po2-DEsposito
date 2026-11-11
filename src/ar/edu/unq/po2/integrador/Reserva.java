package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
	private List<ItemCatalogo> items = new ArrayList<>();
	private List<Voucher> vouchers = new ArrayList<>();
	private int cuposDisponibles;
	//private float tazas;
	private EstadoReserva estadoActual;
	private EstadoTraslado trasladoActual;
	private List<ObservadorReserva> observadores = new ArrayList<>();
	
	
	
	
	public List<Voucher> getVouchers(){
		return vouchers;
	}
	
	public void setEstadoActual(EstadoReserva estadoActual) {
		this.estadoActual = estadoActual;
		this.notificarObservadores(estadoActual);
	}
	
	public void agregarServicio(ItemCatalogo item) {
		 items.add(item);
	}
	
	public void quitarServicio(ItemCatalogo item) {
		 items.remove(item);
	} 
	
	public void agregarObservador(ObservadorReserva observador) {
		observadores.add(observador);
	}
	
	public void quitarObservador(ObservadorReserva observador) {
		observadores.remove(observador);
	}
	
	public void notificarObservadores(EstadoReserva estadoActual) {
		for(ObservadorReserva obs: observadores) {
			obs.actualizar(this, estadoActual);
		}
	}
	
	public void decrementarCupo() {
        if (this.cuposDisponibles > 0) {
            this.cuposDisponibles--;
        } else {
            throw new IllegalStateException("No hay cupos disponibles para decrementar.");
        }
    }
	
	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	
	public void setCuposDisponibles(int numero) {
		 this.cuposDisponibles = numero;
	}
	
	public void liberarCupo() {
		this.cuposDisponibles++;
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

	
	

	

	

	
}
