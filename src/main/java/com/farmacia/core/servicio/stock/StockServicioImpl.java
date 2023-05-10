package com.farmacia.core.servicio.stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.Stock;
import com.farmacia.core.repositorio.StockRepositorio;

@Service
public class StockServicioImpl implements StockServicio
{
	@Autowired
	private StockRepositorio repositorio;
	@Override
	public Long obtenerUltimoStockPorProducto(Long idProducto) 
	{
		Long valor_stock = repositorio.obtenerUltimoStockPorProducto(idProducto);
		if (valor_stock == null)
		{
			valor_stock = Long.valueOf(0);
			return valor_stock;
		}
		else
		{
			return valor_stock;
		}
	}
	@Override
	public Stock guardarStock(Stock stock) 
	{
		return repositorio.save(stock);
	}	
}