package com.farmacia.core.servicio.rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.Rol;
import com.farmacia.core.repositorio.RolRepositorio;

@Service
public class RolServicioImpl implements RolServicio
{
	@Autowired
	private RolRepositorio repositorio;
	@Override
	public List<Rol> mostrarRoles() 
	{
		return repositorio.findAll();
	}
	@Override
	public Rol getRolPorId(long id) 
	{
		return repositorio.getById(id);
	}
}