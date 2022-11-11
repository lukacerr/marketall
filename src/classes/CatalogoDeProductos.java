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

  // 1A, cargar producto
  public Boolean CargarProducto(Producto producto) {
    return Productos.add(producto);
  }

  // 1A, bajar producto
  public Boolean BajarProducto(Producto producto) {
    return Productos.remove(producto);
  }
}
