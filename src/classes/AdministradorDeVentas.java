package classes;

import java.util.*;
import java.util.Map.*;

import classes.Venta.ProductoYCantidad;

public class AdministradorDeVentas {
  private List<Venta> Ventas;

  public AdministradorDeVentas() {
    Ventas = new ArrayList<Venta>();
  }

  public Venta[] GetVentas() {
    return Ventas.toArray(new Venta[Ventas.size()]);
  }

  // 2, crear venta
  public Venta CrearVenta() {
    Venta v = new Venta();
    Ventas.add(v);
    return v;
  }

  // 4, obtiene los top 10 productos más vendidos
  public Producto[] RankingMasVendidos() {
    Producto[] results = new Producto[10];
    HashMap<Producto, Integer> relacionPYC = new HashMap<Producto, Integer>();

    for (Venta venta : Ventas.stream().filter(p -> p.GetVentaRegistrada()).toList()) {
      for (ProductoYCantidad pyc : venta.GetProductoYCantidades()) {
        Producto producto = pyc.GetProducto();
        int cantidad = pyc.GetCantidad();

        Integer cantidadYaExistente = relacionPYC.get(producto);
        if (cantidadYaExistente == null) relacionPYC.put(producto, cantidad);
        else relacionPYC.put(producto, cantidadYaExistente + cantidad);
      }
    }
    
    List<Entry<Producto,Integer>> entries = new ArrayList<Entry<Producto,Integer>>(relacionPYC.entrySet());
    Collections.sort( entries, new Comparator<Entry<Producto,Integer>>() {
        public int compare(Entry<Producto,Integer> a, Map.Entry<Producto,Integer> b) {
            return Integer.compare(b.getValue(), a.getValue());
        }
      }
    );

    for(int i = 0; i < 10; i++) results[i] = entries.get(i).getKey();
    return results;
  }
}
