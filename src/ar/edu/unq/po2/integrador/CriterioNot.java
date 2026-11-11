package ar.edu.unq.po2.integrador;

public class CriterioNot implements CriterioBusqueda {
    private final CriterioBusqueda criterio;

    public CriterioNot(CriterioBusqueda criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        return !criterio.cumple(item);
    }
}
