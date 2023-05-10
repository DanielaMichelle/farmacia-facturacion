package com.farmacia.core.servicio.rol;
import java.util.List;
import com.farmacia.core.modelo.Rol;

public interface RolServicio 
{
	public List<Rol> mostrarRoles();
	public Rol getRolPorId(long id);
}
