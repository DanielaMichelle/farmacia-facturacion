package com.farmacia.core.modelo.dto;

public class InfoUsuario 
{
	private String nombre;
	private String apellido;
	private String email;
	private String rolNombre;
	private String sucursalNombre;
	public InfoUsuario(String nombre, String apellido, String email, String rolNombre, String sucursalNombre) 
	{
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.rolNombre = rolNombre;
		this.sucursalNombre = sucursalNombre;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getApellido() 
	{
		return apellido;
	}
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getRolNombre() 
	{
		return rolNombre;
	}
	public void setRolNombre(String rolNombre) 
	{
		this.rolNombre = rolNombre;
	}
	public String getSucursalNombre() 
	{
		return sucursalNombre;
	}
	public void setSucursalNombre(String sucursalNombre) 
	{
		this.sucursalNombre = sucursalNombre;
	}
}