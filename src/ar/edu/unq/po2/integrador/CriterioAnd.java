package ar.edu.unq.po2.integrador;

import java.util.List;

public class CriterioAnd implements CriterioBusqueda {
    private final List<CriterioBusqueda> criterios;

    public CriterioAnd(CriterioBusqueda criterios) {
        this.criterios = List.of(criterios);
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        return criterios.stream().allMatch(c -> c.cumple(item));
    }
}