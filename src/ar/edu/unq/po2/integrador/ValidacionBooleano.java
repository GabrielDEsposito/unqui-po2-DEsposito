package ar.edu.unq.po2.integrador;

public class ValidacionBooleano implements EstrategiaValidacion {
    public boolean esConsistente(String valor) {
        return valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("false");
    }
}