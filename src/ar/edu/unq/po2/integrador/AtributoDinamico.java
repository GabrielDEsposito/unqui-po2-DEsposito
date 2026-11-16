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

    public boolean validarValor(String valor) {
    if (valor == null || valor.isBlank()) {
        return !obligatorio;             
    }
    return estrategia.esConsistente(valor); 
}
}