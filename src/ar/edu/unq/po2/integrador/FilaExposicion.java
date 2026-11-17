package ar.edu.unq.po2.integrador;

public class FilaExposicion {
    private final String proveedor;
    private final double porcentajeCatalogo;

    public FilaExposicion(String proveedor, double porcentajeCatalogo) {
        this.proveedor = proveedor;
        this.porcentajeCatalogo = porcentajeCatalogo;
    }

    public String getProveedor() { 
        return proveedor; 
    }

    public double getPorcentajeCatalogo() { 
        return porcentajeCatalogo; 
    }
}