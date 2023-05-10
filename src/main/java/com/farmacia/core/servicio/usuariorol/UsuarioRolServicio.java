package com.farmacia.core.servicio.usuariorol;
import com.farmacia.core.modelo.UsuarioRol;

public interface UsuarioRolServicio 
{
	public UsuarioRol getUsuarioRolPorId(Long id);
	public UsuarioRol guardarUsuarioRol(UsuarioRol usuariorol);
	public UsuarioRol actualizarUsuarioRol(UsuarioRol usuariorol);
}
