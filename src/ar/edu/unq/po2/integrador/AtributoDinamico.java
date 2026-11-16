package ar.edu.unq.po2.integrador;

public class AtributoDinamico {
    private String nombre;
    private boolean obligatorio;
    private EstrategiaValidacion estrategiaValidacion;

    public AtributoDinamico(String nombre, boolean obligatorio, EstrategiaValidacion estrategia) {
        this.nombre = nombre;
        this.obligatorio = obligatorio;
        this.estrategiaValidacion = estrategia;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esObligatorio() {
        return obligatorio;
    }

    public boolean esConsistente(String valor) {
        return estrategiaValidacion.esConsistente(valor);
    }
}