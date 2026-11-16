package ar.edu.unq.po2.integrador;

public class Cupo {
	private int total;
	private int reservados;
	
	public Cupo(int total) {
		if (total < 0) throw new IllegalArgumentException("El cupo no puede ser negativo");
		this.total = total;
	}
	
	public boolean hayDisponible() {
		return reservados < total;
	}
	
	public int disponibles() {
		return total - reservados;
	}
	
	public int getTotal() {
		return total;
	}
	
	public int setTotal(int total) {
		return this.total = total;
	}
	
	public int getReservados() {
		return reservados;
	}
	
	public int setReservados(int reservados) {
		return this.reservados = reservados;
	}
	
	
	public void reservar(int unidades) {
        if (unidades > disponibles()) throw new CupoInsuficienteException("Se piden " + unidades + " unidades y solo hay " + disponibles());
        reservados += unidades;
    }

    public void liberar(int unidades) {
        reservados = Math.max(0, reservados - unidades);
    }
}
//asd