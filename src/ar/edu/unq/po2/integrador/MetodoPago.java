package ar.edu.unq.po2.integrador;

public abstract class MetodoPago {
	
	
	public void RealizarPago(double monto) {
		this.ValidarDatos();
		this.ReservarFondos(monto);
		this.EjecutarTransacion(monto);
		this.NotificarResultado();
	}
	
	abstract void ValidarDatos();
	
	abstract void ReservarFondos(double monto);
	
	abstract void EjecutarTransacion(monto);
	
	abstract void NotificarResultado();
}
