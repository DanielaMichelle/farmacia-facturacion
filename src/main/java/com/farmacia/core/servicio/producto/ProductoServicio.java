package com.farmacia.core.servicio.producto;
import java.util.List;
import com.farmacia.core.modelo.Producto;

public interface ProductoServicio 
{
	public List<Producto> mostrarProductos(Long sucursalId);
	public Producto getProductoPorId(Long id);
	public Producto guardarProducto(Producto producto);
	public Producto actualizarProducto(Producto producto);
}
