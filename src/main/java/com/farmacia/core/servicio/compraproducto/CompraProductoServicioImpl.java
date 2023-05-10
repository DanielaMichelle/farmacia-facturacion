package com.farmacia.core.servicio.compraproducto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.CompraProducto;
import com.farmacia.core.modelo.dto.DetalleCompra;
import com.farmacia.core.repositorio.CompraProductoRepositorio;

@Service
public class CompraProductoServicioImpl implements CompraProductoServicio
{
	@Autowired
	CompraProductoRepositorio repositorio;
	@Override
	public List<DetalleCompra> mostrarComprasProductos(Long compraId) 
	{
		List<Object[]> comprasProductos = repositorio.findComprasProductosByCompraId(compraId);
		List<DetalleCompra> detalleCompras = new ArrayList<>();
		for (Object[] compraProducto : comprasProductos) 
		{
	        String nombreProducto = String.valueOf(compraProducto[0]);
	        String precioUnitario = String.valueOf(compraProducto[1]);
	        String cantidad = String.valueOf(compraProducto[2]);
	        String subtotal = String.valueOf(compraProducto[3]);
	        detalleCompras.add(new DetalleCompra(nombreProducto, precioUnitario, cantidad, subtotal));
	        System.out.println(cantidad.getClass());
	    }
		return detalleCompras;
	}
	@Override
	public CompraProducto getCompraProductoPorId(Long id) 
	{
		return repositorio.getById(id);
	}
	@Override
	public CompraProducto guardarCompraProducto(CompraProducto compraproducto) 
	{
		return repositorio.save(compraproducto);
	}
	@Override
	public CompraProducto actualizarCompraProducto(CompraProducto compraproducto) 
	{
		return repositorio.save(compraproducto);
	}
}