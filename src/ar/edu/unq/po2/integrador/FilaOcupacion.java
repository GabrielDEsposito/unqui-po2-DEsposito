package ar.edu.unq.po2.integrador;

public class FilaOcupacion {
    private final Categoria categoria;
    private final String periodo;
    private final int totales;
    private final int reservados;

    public FilaOcupacion(Categoria categoria, String periodo, int totales, int reservados) {
        this.categoria = categoria;
        this.periodo = periodo;
        this.totales = totales;
        this.reservados = reservados;
    }

    public Categoria getCategoria() { 
        return categoria; 
    }

    public String getPeriodo() { 
        return periodo; 
    }

    public int getTotales() { 
        return totales; 
    }

    public int getReservados() { 
        return reservados; 
    }

    public int getLibres() { 
        return totales - reservados; 
    }
}
