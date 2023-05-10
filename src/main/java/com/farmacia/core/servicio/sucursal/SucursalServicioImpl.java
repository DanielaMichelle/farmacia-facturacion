package com.farmacia.core.servicio.sucursal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.Sucursal;
import com.farmacia.core.repositorio.SucursalRepositorio;

@Service
public class SucursalServicioImpl implements SucursalServicio
{
	@Autowired
	private SucursalRepositorio repositorio;
	@Override
	public List<Sucursal> mostrarSucursales() 
	{
		return repositorio.findAll();
	}
	@Override
	public Sucursal guardarSucursal(Sucursal sucursal) 
	{
		return repositorio.save(sucursal);
	}
	@Override
	public Sucursal getSucursalPorId(long id) 
	{
		return repositorio.findById(id).get();
	}
	@Override
	public Sucursal actualizarSucursal(Sucursal sucursal) 
	{
		return repositorio.save(sucursal);
	}
}