package com.farmacia.core.servicio.compra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.Compra;
import com.farmacia.core.repositorio.CompraRepositorio;

@Service
public class CompraServicioImpl implements CompraServicio
{
	@Autowired
	private CompraRepositorio repositorio;
	@Override
	public List<Compra> mostrarCompras(Long sucursalId) 
	{
		return repositorio.findBySucursal(sucursalId);
	}
	@Override
	public Compra getCompraPorId(Long id) 
	{
		return repositorio.getById(id);
	}
	@Override
	public Compra guardarCompra(Compra compra) 
	{
		return repositorio.save(compra);
	}
	@Override
	public List<Compra> mostrarUltimaCompra(Long Id) 
	{
		return repositorio.findByIdCompra(Id);
	}
}