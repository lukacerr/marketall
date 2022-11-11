package classes;

import java.util.*;

public class CatalogoDeProductos {
  private List<Producto> Productos;

  public CatalogoDeProductos() {
    Productos = new ArrayList<Producto>();
  }

  // 1A, listado de productos
  public Producto[] GetProductos() {
    return Productos.toArray(new Producto[Productos.size()]);
  }

  public Producto GetProductos(int Id) {
    return Productos.stream().filter(p -> p.GetId() == Id).findFirst().orElse(null);
  }

  // 1A, crear y cargar producto
  public Producto CargarProducto(String codigo, String descripcion, float precioUnitario, int cantidadEnStock, int stockMinimo) {
    Producto v = new Producto(codigo, descripcion, precioUnitario, cantidadEnStock, stockMinimo);
    Productos.add(v);
    return v;
  }

  // 1A, bajar producto
  public Boolean BajarProducto(Producto producto) {
    return Productos.remove(producto);
  }
}
