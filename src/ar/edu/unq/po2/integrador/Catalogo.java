package ar.edu.unq.po2.integrador;

public abstract class Catalogo {

	private String nombre;
	private String descripcion;
	
	
	public Catalogo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	
	public String getNombre() {
		return nombre;
	}



	public abstract double getPrecioBase();



	public String getDescripcion() {
		return descripcion;
	}

	
	
	
}
