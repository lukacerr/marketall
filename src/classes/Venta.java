package classes;

import java.util.*;

public class Venta {
  class ProductoYCantidad {
    private Producto Producto;
    private int Cantidad;

    public ProductoYCantidad(Producto producto, int cantidad) {
      Producto = producto;
      Cantidad = cantidad;
    }

    public Producto GetProducto() { return Producto; }
    public int GetCantidad() { return Cantidad; }
    public float GetPrecioTotal() { return Producto.GetPrecioUnitario() * Cantidad; }

    public Boolean AumentarCantidad() { return SumarEnCantidad(1); }
    public Boolean DisminuirCantidad() { return SumarEnCantidad(-1); }
    public Boolean SumarEnCantidad(int cantidad) {
      Cantidad += cantidad;
      return HayStockSuficiente(); 
    }

    public Boolean HayStockSuficiente() {
      // ! ¿Con el stock mínimo o la cantidad? no especifíca
      // return Producto.GetStockMinimo() >= Cantidad;
      return Producto.GetCantidadEnStock() >= Cantidad;
    }

    public int RegistrarVenta() {
      return Producto.DisminuirStock(Cantidad);
    }
  }

  private List<ProductoYCantidad> ProductosYCantidades;
  private Boolean VentaRegistrada;

  public Venta() {
    ProductosYCantidades = new ArrayList<ProductoYCantidad>();
    VentaRegistrada = false;
  }

  public ProductoYCantidad[] GetProductoYCantidades() { return ProductosYCantidades.toArray(new ProductoYCantidad[ProductosYCantidades.size()]); }
  public boolean GetVentaRegistrada() { return VentaRegistrada; }
  
  public float GetPrecioTotal(MediosDePago medioDePago) {
    float valorTotal = 0;
    for (ProductoYCantidad productoYCantidad : ProductosYCantidades) {
      valorTotal += productoYCantidad.GetPrecioTotal();
    }

    switch(medioDePago) {
      // 2Aii, descuento de 10%
      case EFECTIVO: valorTotal = (float)(valorTotal * 0.9); break;
      // 2Aiii, recargas en función a cuotas
      // 2Aiii2, 6% de recarga en 2 cuotas
      case CREDITO2: valorTotal = (float)(valorTotal * 1.06); break;
      // 2Aiii3, 12% de recarga en 3 cuotas
      case CREDITO3: valorTotal = (float)(valorTotal * 1.12); break;
      // 2Aiii4, 20% de recarga en 3 cuotas
      case CREDITO6: valorTotal = (float)(valorTotal * 1.20); break;
      // 2Ai, débito cobra todo
      default: break;
    }

    return valorTotal;
  }

  // 2B, checkea viabilidad de venta (usar antes de registrar venta)
  public Boolean HayStockSuficiente() {
    return ProductosYCantidades.stream().allMatch(pyc -> pyc.HayStockSuficiente());
  }

  // 2, registrar venta
  // 2C, disminuir stock al registrar venta / confirmar compra
  public int RegistrarVenta() {
    int v = 0;
    VentaRegistrada = true;
    for (ProductoYCantidad productoYCantidad : ProductosYCantidades) {
      v += productoYCantidad.RegistrarVenta();
    }

    return v;
  }

  public ProductoYCantidad AgregarProducto(Producto producto) {
    return AgregarProducto(producto, 1);
  }

  public ProductoYCantidad AgregarProducto(Producto producto, int cantidad) {
    ProductoYCantidad v = new ProductoYCantidad(producto, cantidad);
    ProductosYCantidades.add(v);
    return v;
  }

  public Boolean BajarProducto(ProductoYCantidad productoYCantidad) {
    return ProductosYCantidades.remove(productoYCantidad);
  }
}
