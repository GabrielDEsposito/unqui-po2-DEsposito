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

	public abstract double getPrecioBase();
		
}
