package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristico extends ItemCatalogo{
	//private List<Catalogo> items;
	private List<ItemCatalogo> items;
	private double descuento;

	
	public PaqueteTuristico(String nombre, String descripcion, double descuento) {
        super(nombre, descripcion);
        this.items = new ArrayList<>();
        this.descuento = descuento;
    }	
	
	public void agregarItem(ItemCatalogo item) {
        this.items.add(item);
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

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

	@Override
	public double getPrecioBase() {
		double sumaPrecios = 0;
		for (ItemCatalogo item : items) {
		    sumaPrecios += item.getPrecioBase();
		}
		return sumaPrecios;
	}


	public double getPrecioFinal() {
		return this.getPrecioBase() * (1 - (this.descuento / 100.0));
	}


	
}
