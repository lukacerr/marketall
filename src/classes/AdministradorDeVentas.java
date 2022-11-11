package classes;

import java.util.*;

public class AdministradorDeVentas {
  private List<Venta> Ventas;

  public AdministradorDeVentas() {
    Ventas = new ArrayList<Venta>();
  }

  public Venta[] GetVentas() {
    return Ventas.toArray(new Venta[Ventas.size()]);
  }

  // TODO
  // 4, obtiene los top 10 productos más vendidos
  public Producto[] RankingMasVendidos() {
    // List<Venta> ventasRegistradas = Ventas.stream().filter(p -> p.GetVentaRegistrada()).toList();
    return new Producto[0];
  }
}
