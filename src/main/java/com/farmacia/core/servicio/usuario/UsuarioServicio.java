package com.farmacia.core.servicio.usuario;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.farmacia.core.modelo.Usuario;
import com.farmacia.core.modelo.dto.InfoUsuario;
import com.farmacia.core.modelo.dto.UsuarioRegistroDTO;

public interface UsuarioServicio extends UserDetailsService
{
	public List<InfoUsuario> mostrarUsuarios();
	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	public String recuperarNombrePorEmail(String email);
	public String recuperarEmailPorDni(String dni);
	public String recuperarApellidoPorEmail(String email);
	public String recuperarNombrePerfilPorEmail(String email);
	public Long recuperarIdSucursalPorEmail(String email);
	public String recuperarNombreSucursalPorEmail(String email);
	public Usuario buscarUsuario(String username, String password);
	public Usuario buscarUsuarioPorDni(String username, String password);
	public List<Usuario> listarUsuarios();
}