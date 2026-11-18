package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.FormatoHTML;
import ar.edu.unq.po2.integrador.VisitorReporte;

class FormatoHTMLTest extends FormatoReporteTest {
    @Override protected VisitorReporte formato() { return new FormatoHTML(); }
    @Override protected String etiqueta() { return "[HTML]"; }
}