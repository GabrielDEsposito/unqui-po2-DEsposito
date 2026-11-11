package ar.edu.unq.po2.integrador;

import java.util.List;

public class CriterioOr implements CriterioBusqueda {
    private final List<CriterioBusqueda> criterios;

    public CriterioOr(CriterioBusqueda criterios) {
        this.criterios = List.of(criterios);
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        return criterios.stream().anyMatch(c -> c.cumple(item));
    }
}