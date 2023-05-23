package com.farmacia.core.modelo.dto;
import com.farmacia.core.modelo.Sucursal;

public class UsuarioRegistroDTO 
{
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private Sucursal sucursal;
	private String password;
	private String dni;
	//Constructores, getters y setters
	public UsuarioRegistroDTO() 
	{

	}
	public UsuarioRegistroDTO(String nombre, String apellido, String email, Sucursal sucursal, String password) 
	{
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.sucursal = sucursal;
		this.password = password;
	}
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
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
	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) 
	{
		this.sucursal = sucursal;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getDni() 
	{
		return dni;
	}
	public void setDni(String dni) 
	{
		this.dni = dni;
	}
}