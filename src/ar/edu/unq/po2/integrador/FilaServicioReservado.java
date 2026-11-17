package ar.edu.unq.po2.integrador;

public class FilaServicioReservado {
    private final String nombre;
    private final int unidadesVendidas;
    private final double precioPromedioCobrado;

    public FilaServicioReservado(String nombre, int unidadesVendidas, double precioPromedioCobrado) {
        this.nombre = nombre;
        this.unidadesVendidas = unidadesVendidas;
        this.precioPromedioCobrado = precioPromedioCobrado;
    }

    public String getNombre() { 
        return nombre; 
    }

    public int getUnidadesVendidas() { 
        return unidadesVendidas; 
    }
    
    public double getPrecioPromedioCobrado() { 
        return precioPromedioCobrado; 
    }
}