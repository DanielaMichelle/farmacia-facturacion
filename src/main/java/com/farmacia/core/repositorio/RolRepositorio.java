package com.farmacia.core.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.farmacia.core.modelo.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>
{

}
