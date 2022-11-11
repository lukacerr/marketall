package classes;

import java.util.*;

public class Venta {
  class ProductoYCantidad {
    Producto Producto;
    int Cantidad;

    public ProductoYCantidad(Producto producto, int cantidad) {
      Producto = producto;
      Cantidad = cantidad;
    }

    public float GetPrecioTotal() { return Producto.GetPrecioUnitario() * Cantidad; };

    public Boolean HayStockSuficiente() {
      // ! ¿Con el stock mínimo o la cantidad? no especifíca
      // return Producto.GetStockMinimo() >= Cantidad;
      return Producto.GetCantidadEnStock() >= Cantidad;
    }
  }

  private List<ProductoYCantidad> ProductosYCantidades;
  private Boolean ventaRegistrada;

  public Venta() {
    ProductosYCantidades = new ArrayList<ProductoYCantidad>();
    ventaRegistrada = false;
  }

  public boolean GetVentaRegistrada() {
    return ventaRegistrada;
  }
  
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

  // TODO:
  // 2, registrar venta
  // 2C, disminuir stock al registrar venta / confirmar compra
  public void RegistrarVenta(float precioTotal) {
  }

  // registrar venta sin cálculo de pago previo
  public void RegistrarVenta(MediosDePago medioDePago) {
    RegistrarVenta(GetPrecioTotal(medioDePago));
  }
}
