package com.farmacia.core.servicio.usuariorol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.UsuarioRol;
import com.farmacia.core.repositorio.UsuarioRolRepositorio;

@Service
public class UsuarioRolServicioImpl implements UsuarioRolServicio
{
	@Autowired
	UsuarioRolRepositorio repositorio;
	@Override
	public UsuarioRol getUsuarioRolPorId(Long id) 
	{
		return repositorio.getById(id);
	}
	@Override
	public UsuarioRol guardarUsuarioRol(UsuarioRol usuariorol) 
	{
		return repositorio.save(usuariorol);
	}
	@Override
	public UsuarioRol actualizarUsuarioRol(UsuarioRol usuariorol) 
	{
		return repositorio.save(usuariorol);
	}
}