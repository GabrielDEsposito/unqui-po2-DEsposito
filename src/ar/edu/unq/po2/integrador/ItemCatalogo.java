package ar.edu.unq.po2.integrador;

public abstract class ItemCatalogo {

	private String nombre;
	private String descripcion;
	
	
	public ItemCatalogo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public boolean contiene(ItemCatalogo otro) {
		return this == otro;
	}

	public abstract double getPrecioBase();
	public abstract double getPrecioFinal();
	public abstract boolean perteneceACategoria(Categoria categoria);
	public abstract boolean tieneCupoDisponible();
	public abstract void reservarCupo(int unidades);
	public abstract void liberarCupo(int unidades);
	
}
