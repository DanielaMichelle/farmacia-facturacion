package com.farmacia.core.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmacia.core.modelo.Sucursal;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal, Long>
{

}