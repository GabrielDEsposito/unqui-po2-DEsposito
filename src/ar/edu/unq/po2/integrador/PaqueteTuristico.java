package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristico extends ItemCatalogo{

	private final List<ItemCatalogo> items = new ArrayList<>();
	private final double descuento;

	
	public PaqueteTuristico(String nombre, String descripcion, double descuento) {
        super(nombre, descripcion);
        if (descuento <0 || descuento > 100) {
        	throw new IllegalArgumentException("El descuento debe estar entre 0 y 100");
        }
        this.descuento = descuento;
    }	
	
	public void agregarItem(ItemCatalogo item) {
        if (item.contiene(this)) {
        	throw new IllegalArgumentException("Un paquete no puede contenerse a si mismoi");
        }
        items.add(item);
    }

    public void quitarItem(ItemCatalogo item) {
        this.items.remove(item);
    }
    
    public List<ItemCatalogo> getItems() {
        return this.items;
    }

    public double getDescuento() {
        return this.descuento;
    }

    @Override
    public double getPrecioFinal() {
    	double suma = 0;
    	for (ItemCatalogo item : items) {
    		suma += item.getPrecioFinal();
    	}
    	return suma * (1 - descuento / 100.0);
    }
	@Override
	public double getPrecioBase() {
		double sumaPrecios = 0;
		for (ItemCatalogo item : items) {
			sumaPrecios += item.getPrecioBase();
		}
		return sumaPrecios;
	}
	@Override
	public boolean perteneceACategoria(Categoria categoria) {
		for (ItemCatalogo item : items) {
			if (item.perteneceACategoria(categoria)) return true;
		}
		return false;
	}
	@Override
	public boolean tieneCupoDisponible() {
		if (items.isEmpty()) return false;
		for (ItemCatalogo item : items) {
			if (!item.tieneCupoDisponible()) return false;
		}
		return true;
	}
	@Override
	public boolean contiene(ItemCatalogo otro) {
		if (this == otro) return true;
		for (ItemCatalogo item : items) {
			if(item.contiene(otro)) return true;
		}
		return false;
	}
	@Override
	public void reservarCupo(int unidades) {
		for (ItemCatalogo item : items) {
			item.reservarCupo(unidades);
		}
	}
	@Override
	public void liberarCupo(int unidades) {
		for (ItemCatalogo item : items) {
			item.liberarCupo(unidades);
		}
	}
}
