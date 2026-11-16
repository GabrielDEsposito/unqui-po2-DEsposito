package ar.edu.unq.po2.integrador;

public class OperacionInvalidaException extends RuntimeException {
	public OperacionInvalidaException(String mensaje) {
		super(mensaje);
	}
}
