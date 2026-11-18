package ar.edu.unq.po2.test;

import ar.edu.unq.po2.integrador.FormatoCSV;
import ar.edu.unq.po2.integrador.VisitorReporte;

class FormatoCSVTest extends FormatoReporteTest {
    @Override protected VisitorReporte formato() { return new FormatoCSV(); }
    @Override protected String etiqueta() { return "[CSV]"; }
}