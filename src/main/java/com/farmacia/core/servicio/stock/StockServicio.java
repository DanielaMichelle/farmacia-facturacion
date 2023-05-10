package com.farmacia.core.servicio.stock;
import com.farmacia.core.modelo.Stock;

public interface StockServicio 
{
    Long obtenerUltimoStockPorProducto(Long idProducto);
    public Stock guardarStock(Stock stock);
}