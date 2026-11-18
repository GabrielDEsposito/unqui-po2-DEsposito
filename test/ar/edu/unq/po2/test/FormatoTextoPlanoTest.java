package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.FormatoTextoPlano;
import ar.edu.unq.po2.integrador.VisitorReporte;

class FormatoTextoPlanoTest extends FormatoReporteTest {
    @Override protected VisitorReporte formato() { return new FormatoTextoPlano(); }
    @Override protected String etiqueta() { return "[TEXTO PLANO]"; }
}