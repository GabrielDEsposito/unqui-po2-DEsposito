package ar.edu.unq.po2.integrador;

public abstract class MetodoPago {
	
	private String codigoTransaccion;
    private List<String> codigosRegistrados = new ArrayList<>();
	private static int contadorTransacciones = 0;
	
	public void RealizarPago(double monto) {
		this.codigoTransaccion = ++contadorTransacciones;
		this.ValidarDatos();
		this.ReservarFondos(monto);
		this.EjecutarTransacion(monto);
		this.NotificarResultado();
	}
	
	protected abstract void ValidarDatos();
	
	protected abstract void ReservarFondos(double monto);
	
	protected abstract void EjecutarTransacion(double monto);
	
	protected void notificarResultado(double monto) {
        this.codigosRegistrados.add(this.codigoTransaccion);
    }

	protected String getCodigoTransaccion() {
        return codigoTransaccion;
    }
}
