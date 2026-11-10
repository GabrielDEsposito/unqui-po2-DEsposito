package ar.edu.unq.po2.integrador;

public abstract class MetodoPago {
	
	
	public void RealizarPago() {
		this.ValidarDatos();
		this.ReservarFondos();
		this.EjecutarTransacion();
		this.NotificarResultado();
	}
	
	abstract void ValidarDatos();
	
	abstract void ReservarFondos();
	
	abstract void EjecutarTransacion();
	
	abstract void NotificarResultado();
}
