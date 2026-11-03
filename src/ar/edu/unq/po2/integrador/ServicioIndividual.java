package ar.edu.unq.po2.integrador;

public class ServicioIndividual extends Catalogo{
	
	private int reservaId;
	private String proveedor;
	private Categoria categoria;
	private double precioBase;
	private double precioFinal;
	
	public ServicioIndividual(String nombre, String descripcion) {
		super(nombre, descripcion);
		
	}
	
	

	@Override
	public double getPrecioBase() {
		return precioBase;
	}
	
	public double getPrecioFinal() {
        return precioFinal;
    }



	public int getReservaId() {
		return reservaId;
	}



	public String getProveedor() {
		return proveedor;
	}



	public Categoria getCategoria() {
		return categoria;
	}






	

}
