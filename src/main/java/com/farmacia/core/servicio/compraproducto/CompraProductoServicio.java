package com.farmacia.core.servicio.compraproducto;
import java.util.List;
import com.farmacia.core.modelo.CompraProducto;
import com.farmacia.core.modelo.dto.DetalleCompra;

public interface CompraProductoServicio 
{
	public List<DetalleCompra> mostrarComprasProductos(Long compraId);
	public CompraProducto getCompraProductoPorId(Long id);
	public CompraProducto guardarCompraProducto(CompraProducto compraproducto);
	public CompraProducto actualizarCompraProducto(CompraProducto compraproducto);
}
