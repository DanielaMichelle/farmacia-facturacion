package com.farmacia.core.servicio.usuario;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;
import com.farmacia.core.modelo.Usuario;
import com.farmacia.core.modelo.dto.InfoUsuario;
import com.farmacia.core.modelo.dto.UsuarioRegistroDTO;
import com.farmacia.core.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio 
{
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) 
	{
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}
	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) 
	{
		Usuario usuario = new Usuario(
				registroDTO.getNombre(), 
				registroDTO.getApellido(),
				registroDTO.getEmail(),
				registroDTO.getSucursal(),
				passwordEncoder.encode(registroDTO.getPassword()));
		return usuarioRepositorio.save(usuario);
	}
	@Override
	public List<Usuario> listarUsuarios() 
	{
		return usuarioRepositorio.findAll();
	}
	@Override
	public String recuperarNombrePorEmail(String email) 
	{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario == null) 
		{
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return usuario.getNombre();
	}
	@Override
	public String recuperarApellidoPorEmail(String email) 
	{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario == null) 
		{
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return usuario.getApellido();
	}
	@Override
	public String recuperarNombrePerfilPorEmail(String email) 
	{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario == null) 
		{
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return usuario.getNombrePrimerRol();
	}
	@Override
	public Long recuperarIdSucursalPorEmail(String email) 
	{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario == null) 
		{
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return usuario.getSucursal().getId();
	}
	@Override
	public String recuperarNombreSucursalPorEmail(String email) 
	{
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario == null) 
		{
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return usuario.getSucursal().getNombre();
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Usuario buscarUsuario(String username, String password) 
	{
		Usuario usuario = usuarioRepositorio.findByEmailAndPassword(username, password);
		if(usuario == null) 
		{
			return null;
		}
		return usuario;
	}
	@Override
	public List<InfoUsuario> mostrarUsuarios() 
	{
		List<Object[]> queryUsuarios = usuarioRepositorio.findUsuarios();
		List<InfoUsuario> infoUsuarios = new ArrayList<>();
		for (Object[] infoUsuario : queryUsuarios) 
		{
		    String nombre = String.valueOf(infoUsuario[0]);
		    String apellido = String.valueOf(infoUsuario[1]);
		    String email = String.valueOf(infoUsuario[2]);
		    String rolNombre = String.valueOf(infoUsuario[3]);
		    String sucursalNombre = String.valueOf(infoUsuario[4]);
		    infoUsuarios.add(new InfoUsuario(nombre, apellido, email, rolNombre, sucursalNombre));
		}
		return infoUsuarios;
	}
}