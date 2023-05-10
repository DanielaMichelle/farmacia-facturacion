package com.farmacia.core.repositorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farmacia.core.modelo.Producto;
import com.farmacia.core.modelo.Sucursal;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>
{
	@Query("SELECT p FROM Producto p WHERE p.sucursal.id = :sucursalId")
	List<Producto> findBySucursalId(@Param("sucursalId") Long sucursalId);
	@Modifying
    @Query("UPDATE Producto p SET p.sucursal = :sucursal WHERE p.id = :id")
    void setServicioSucursal(@Param("id") Long id, @Param("sucursal") Sucursal sucursal);
}