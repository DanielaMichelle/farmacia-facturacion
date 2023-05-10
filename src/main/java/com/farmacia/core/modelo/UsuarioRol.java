package com.farmacia.core.modelo;
import javax.persistence.*;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "usuarios_roles")
public class UsuarioRol 
{
	@Id
	@GeneratedValue
	@Column(name = "usuario_rol_id")
	private Long usuarioRolId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @NotNull
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    @NotNull
    private Rol rol;
    // Constructor, getters y setters
	public UsuarioRol() 
	{
		
	}
	public Usuario getUsuario() 
	{
		return usuario;
	}
	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}
	public Rol getRol() 
	{
		return rol;
	}
	public void setRol(Rol rol) 
	{
		this.rol = rol;
	}
}