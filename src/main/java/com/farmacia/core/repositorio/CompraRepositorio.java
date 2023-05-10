package com.farmacia.core.repositorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farmacia.core.modelo.Compra;
import com.farmacia.core.modelo.Sucursal;

@Repository
public interface CompraRepositorio extends JpaRepository<Compra, Long>
{
	@Query("SELECT c FROM Compra c WHERE c.sucursal.id = :sucursalId")
	List<Compra> findBySucursal(@Param("sucursalId") Long sucursalId);
	//Recuperar la ultima Compra insertada
	@Query("SELECT c FROM Compra c WHERE c.id = :idCompra")
	List<Compra> findByIdCompra(Long idCompra);
	@Modifying
	@Query("UPDATE Compra c SET c.sucursal = :sucursal WHERE c.id = :id")
	void setCompraSucursal(@Param("id") Long id, @Param("sucursal") Sucursal sucursal);
}