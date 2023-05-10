package com.farmacia.core.repositorio;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmacia.core.modelo.Producto;
import com.farmacia.core.modelo.Stock;

@Repository
public interface StockRepositorio extends JpaRepository<Stock, Long> {
    
    @Query(value = "SELECT IFNULL(cantidad, 0) FROM stock WHERE producto_id = :idProducto ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Long obtenerUltimoStockPorProducto(@Param("idProducto") Long idProducto);
    
    @Transactional
    @Modifying
    @Query("UPDATE Stock s SET s.producto = :producto WHERE s.id = :id")
    void setServicioProducto(@Param("id") Long id, @Param("producto") Producto producto);  
}