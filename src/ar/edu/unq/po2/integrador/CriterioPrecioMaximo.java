package ar.edu.unq.po2.integrador;

public class CriterioPrecioMaximo implements CriterioBusqueda {
    private final double maximo;

    public CriterioPrecioMaximo(double maximo) {
        this.maximo = maximo;
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        return item.getPrecioBase() <= maximo;
    }
}
