package ar.edu.unq.po2.integrador;

public class CriterioCategoria implements CriterioBusqueda {
    private final Categoria categoria;

    public CriterioCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        // return item.perteneceACategoria(categoria); | Faltaria implementar el tema de categorias en ItemCatalogo
    }
}
