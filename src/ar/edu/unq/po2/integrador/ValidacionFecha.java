package ar.edu.unq.po2.integrador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidacionFecha implements EstrategiaValidacion {
     public boolean esConsistente(String valor) {
        try {
            LocalDate.parse(valor);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}