package ar.edu.unq.po2.integrador;

public class ServicioIndividual extends ItemCatalogo{
	
	private final int reservaId;
	private final String proveedor;
	private final Categoria categoria;
	private final double precioBase;
	private final double descuentoPromocional;
	private final Cupo cupo;
	
	public ServicioIndividual(int reservaId, String nombre, String descripcion, String proveedor, Categoria categoria, double precioBase, double descuentoPromocional, Cupo cupo) {
		super(nombre, descripcion);
		if (descuentoPromocional < 0 || descuentoPromocional > 100) {
			throw new IllegalArgumentException("El descuento debe estar entre 0 y 100");
		}
		this.reservaId = reservaId;
		this.proveedor = proveedor;
		this.categoria = categoria;
		this.precioBase = precioBase;
		this.descuentoPromocional = descuentoPromocional;
		this.cupo = cupo;
	}
	
	public int getReservaId() {
		return reservaId;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public Cupo getCupo() {
		return cupo;
	}
	public String getProveedor() {
		return proveedor;
	}
	
	@Override
	public double getPrecioFinal() {
		return precioBase * (1 - descuentoPromocional / 100);
	}
	@Override
	public double getPrecioBase() {
		return precioBase;
	}
	@Override
	public boolean perteneceACategoria(Categoria categoria) {
		return this.categoria == categoria;
	}
	@Override
	public void reservarCupo(int unidades) {
		cupo.reservar(unidades);
	}
	@Override
	public void liberarCupo(int unidades) {
		cupo.liberar(unidades);
	}
	@Override
	public boolean tieneCupoDisponible() {
		return cupo.hayDisponible();
	}
}
