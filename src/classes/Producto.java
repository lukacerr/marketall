package classes;

public class Producto {
  private static int _index = 0;
  private	int Id;
  
  // 1, contenido per producto
	private String Codigo;
	private String Descripcion;
	private float PrecioUnitario;
	private int CantidadEnStock;
	private int StockMinimo;

	public Producto(String codigo, String descripcion, float precioUnitario, int cantidadEnStock, int stockMinimo) {
		Id = ++_index;
		Codigo = codigo;
		Descripcion = descripcion;
    PrecioUnitario = precioUnitario;
    CantidadEnStock = cantidadEnStock;
    StockMinimo = stockMinimo;
	}

  public int GetId() { return Id; }
  public String getCodigo() { return Codigo; }
  public String getDescripcion() { return Descripcion; }
  public float GetPrecioUnitario() { return PrecioUnitario; }
  public int GetCantidadEnStock() { return CantidadEnStock; }
  public int GetStockMinimo() { return StockMinimo; }

  // 1A, modificar producto
  public void ModificarProducto(String codigo, String descripcion, float precioUnitario, int cantidadEnStock, int stockMinimo) {
    Codigo = codigo;
    Descripcion = descripcion;
    PrecioUnitario = precioUnitario;
    CantidadEnStock = cantidadEnStock;
    StockMinimo = stockMinimo;
  }

  // 3, saber si un producto llegó al stock mínimo
  public Boolean CantidadDeStockAlMinimo() { return StockMinimo >= CantidadEnStock; }
}
