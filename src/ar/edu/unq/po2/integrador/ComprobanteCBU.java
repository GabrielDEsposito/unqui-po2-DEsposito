package ar.edu.unq.po2.integrador;

public class ComprobanteCBU {
    private String cbu;
    private String alias;
    private int numeroOperacion;

    public ComprobanteCBU(String cbu, String alias, int numeroOperacion) {
        this.cbu = cbu;
        this.alias = alias;
        this.numeroOperacion = numeroOperacion;
    }

    public int getCbu() {
        return cbu;
    }

    public String getAlias() {
        return alias;
    }

}
