package ar.edu.unq.po2.integrador;

public class RegistroVenta {
    private final ItemCatalogo item;
    private int unidades = 0;
    private double recaudado = 0;

    public RegistroVenta(ItemCatalogo item) { this.item = item; }

    public void agregarVenta(double precio) {
        unidades++;
        recaudado += precio;
    }

    public ItemCatalogo getItem() { return item; }
    public int getUnidades() { return unidades; }
    public double getPrecioPromedio() { return recaudado / unidades; }
}
