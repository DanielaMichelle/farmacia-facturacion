package com.farmacia.core.repositorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.farmacia.core.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>
{
	@Query(value = "SELECT u.nombre, u.apellido, u.email, u.dni, r.nombre AS rol_nombre, s.nombre AS sucursal_nombre " +
	        "FROM usuarios u " +
	        "INNER JOIN sucursales s ON u.sucursal_id = s.id " +
	        "INNER JOIN usuarios_roles ur ON u.id = ur.usuario_id " +
	        "INNER JOIN roles r ON ur.rol_id = r.id", nativeQuery = true)
	List<Object[]> findUsuarios();
	public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public Usuario findByEmail(String email);
	public Usuario findByDni(String dni);
	public default Usuario findByEmailAndPassword(String email, String password) 
	{
        Usuario usuario = findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) 
        {
            return usuario;
        } 
        else 
        {
            return null;
        }
    }
	public default Usuario findByDniAndPassword(String dni, String password) 
	{
	    Usuario usuario = findByDni(dni);
	    if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) 
	    {
	        return usuario;
	    } 
	    else 
	    {
	        return null;
	    }
	}

}