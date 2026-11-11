package ar.edu.unq.po2.integrador;

public class CriterioNombre implements CriterioBusqueda{
    private final String texto;

    public CriterioNombre(String texto) {
        this.texto = texto.toLowerCase();
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        return item.getNombre().toLowerCase().contains(texto);
    }
}