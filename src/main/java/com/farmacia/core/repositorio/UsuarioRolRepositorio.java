package com.farmacia.core.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.farmacia.core.modelo.Rol;
import com.farmacia.core.modelo.Usuario;
import com.farmacia.core.modelo.UsuarioRol;

public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRol, Long>
{
	@Modifying
    @Query("UPDATE UsuarioRol ur SET ur.usuario = :usuario WHERE ur.usuario.id = :usuarioId")
    void setServicioUsuario(@Param("usuarioId") Long usuarioId, @Param("usuario") Usuario usuario);
	@Modifying
    @Query("UPDATE UsuarioRol ur SET ur.rol = :rol WHERE ur.rol.id = :rolId")
    void setServicioRol(@Param("rolId") Long rolId, @Param("rol") Rol rol);
}