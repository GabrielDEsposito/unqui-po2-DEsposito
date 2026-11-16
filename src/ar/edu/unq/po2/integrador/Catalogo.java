package ar.edu.unq.po2.integrador;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<ItemCatalogo> items = new ArrayList<>();

    public void agregar(ItemCatalogo item) {
        items.add(item);
    }

    public List<ItemCatalogo> buscar(CriterioBusqueda criterio) {
        return items.stream().filter(criterio::cumple).toList();
    }
}
