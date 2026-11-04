package ar.edu.unq.po2.integrador;

import java.util.List;

public class PaqueteTuristico extends Catalogo{
	private List<Catalogo> items;
	private double descuento;

	
	public PaqueteTuristico(String nombre, String descripcion) {
		super(nombre, descripcion);
	
	}

	@Override
	public double getPrecioBase() {
		double sumaPrecios = 0;
		for (Catalogo item : items) {
		    sumaPrecios += item.getPrecioBase();
		}
		return sumaPrecios * (1 - this.descuento);
	}


	
}
