package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
	private List<Catalogo> items = new ArrayList<>();
	private List<Voucher> vouchers = new ArrayList<>();
	private int cuposDisponibles;
	//private float tazas;
	private EstadoReserva estadoActual;
	private EstadoTraslado trasladoActual;
	
	
	
	public void setEstadoActual(EstadoReserva estadoActual) {
		this.estadoActual = estadoActual;
	}
	
	public void agregarServicio(Catalogo item) {
		 items.add(item);
	}
	
	public void quitarServicio(Catalogo item) {
		 items.remove(item);
	} 
	
	
	public void decrementarCupo() {
        if (this.cuposDisponibles > 0) {
            this.cuposDisponibles--;
        } else {
            throw new IllegalStateException("No hay cupos disponibles para decrementar.");
        }
    }
	
	
	public void liberarCupo() {
		this.cuposDisponibles++;
	}
	
	
	public float costoServicio() {
		float totalServicios = 0;
	    for (Catalogo item : items) {
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

	
	

	

	

	
}
