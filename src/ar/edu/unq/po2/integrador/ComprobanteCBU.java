package ar.edu.unq.po2.integrador;

public class ComprobanteCBU {
    private String cbu;
    private String alias;
    private int numeroOperacion;

    public ComprobanteCBU(String cbu2, String alias, int numeroOperacion) {
        this.cbu = cbu2;
        this.alias = alias;
        this.numeroOperacion = numeroOperacion;
    }

    public String getCbu() {
        return cbu;
    }

    public String getAlias() {
        return alias;
    }

}
