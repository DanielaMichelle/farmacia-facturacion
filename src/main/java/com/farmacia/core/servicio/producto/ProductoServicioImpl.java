package com.farmacia.core.servicio.producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.Producto;
import com.farmacia.core.repositorio.ProductoRepositorio;

@Service
public class ProductoServicioImpl implements ProductoServicio
{
	@Autowired
	private ProductoRepositorio repositorio;
	@Override
	public List<Producto> mostrarProductos(Long sucursalId) 
	{
		return repositorio.findBySucursalId(sucursalId);
	}
	@Override
	public Producto getProductoPorId(Long id) 
	{
		return repositorio.findById(id).get();
	}
	@Override
	public Producto guardarProducto(Producto producto) 
	{
		return repositorio.save(producto);
	}
	@Override
	public Producto actualizarProducto(Producto producto) 
	{
		return repositorio.save(producto);
	}
}