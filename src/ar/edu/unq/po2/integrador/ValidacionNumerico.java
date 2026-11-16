package ar.edu.unq.po2.integrador;

public class ValidacionNumerico implements EstrategiaValidacion {
    public boolean esConsistente(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}