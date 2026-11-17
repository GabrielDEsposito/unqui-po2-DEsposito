package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public abstract class MetodoPago {
	
	private int codigoTransaccion;
    private List<Integer> codigosRegistrados = new ArrayList<>();
	private static int contadorTransacciones = 0;
	
	public void RealizarPago(double monto) {
		this.codigoTransaccion = ++contadorTransacciones;
		this.ValidarDatos();
		this.ReservarFondos(monto);
		this.EjecutarTransacion(monto);
		this.NotificarResultado(monto);
	}
	
	public abstract void ValidarDatos();
	
	public abstract void ReservarFondos(double monto);
	
	public abstract void EjecutarTransacion(double monto);
	
	public void NotificarResultado(double monto) {
        this.codigosRegistrados.add(this.codigoTransaccion);
    }

	public int getCodigoTransaccion() {
        return codigoTransaccion;
    }
	
	
}
