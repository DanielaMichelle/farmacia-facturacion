package com.farmacia.core.sesion;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession 
{
    private String nombre;
    private String apellido;
    private String nombreRol;
    private String nombreSucursal;
    private Long idSucursal;
    // Métodos getter y setter
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
	public String getNombreRol() 
	{
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) 
	{
		this.nombreRol = nombreRol;
	}
	public String getNombreSucursal() 
	{
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) 
	{
		this.nombreSucursal = nombreSucursal;
	}
	public Long getIdSucursal() 
	{
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	public void eliminarSesion()
	{
		this.nombre = null;
		this.apellido = null;
		this.nombreRol = null;
		this.nombreSucursal = null;
		this.idSucursal = null;
	}
}