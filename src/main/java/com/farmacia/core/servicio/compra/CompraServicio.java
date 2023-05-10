package com.farmacia.core.servicio.compra;
import java.util.List;
import com.farmacia.core.modelo.Compra;

public interface CompraServicio 
{
	public List<Compra> mostrarCompras(Long sucursalId);
	public List<Compra> mostrarUltimaCompra(Long Id);
	public Compra getCompraPorId(Long id);
	public Compra guardarCompra(Compra compra);
}
