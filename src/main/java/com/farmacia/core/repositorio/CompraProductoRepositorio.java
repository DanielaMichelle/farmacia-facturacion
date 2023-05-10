package com.farmacia.core.repositorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farmacia.core.modelo.Compra;
import com.farmacia.core.modelo.CompraProducto;
import com.farmacia.core.modelo.Producto;

@Repository
public interface CompraProductoRepositorio extends JpaRepository<CompraProducto, Long> 
{
	@Query(value = "SELECT productos.nombre, productos.precio, compras_productos.cantidad, compras_productos.subtotal, compras.id "
	          + "FROM compras "
	          + "INNER JOIN compras_productos ON compras.id = compras_productos.compra_id "
	          + "INNER JOIN productos ON compras_productos.producto_id = productos.id "
	          + "WHERE compras.id = :compraId", nativeQuery = true)
	List<Object[]> findComprasProductosByCompraId(@Param("compraId") Long compraId);
	@Modifying
    @Query("UPDATE CompraProducto cp SET cp.compra = :compra WHERE cp.compra.id = :compraId")
    void setServicioCompra(@Param("compraId") Long compraId, @Param("compra") Compra compra);
	@Modifying
    @Query("UPDATE CompraProducto cp SET cp.producto = :producto WHERE cp.producto.id = :productoId")
    void setServicioProducto(@Param("productoId") Long productoId, @Param("producto") Producto producto);
}