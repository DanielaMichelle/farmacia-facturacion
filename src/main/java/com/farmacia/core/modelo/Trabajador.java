package com.farmacia.core.modelo;
import javax.persistence.*;

@Entity
@Table(name = "trabajadores")
public class Trabajador 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String direccion;
	private String telefono;
	@Column(name = "correo_electronico")
	private String correoElectronico;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sucursal_id", referencedColumnName = "id")
	private Sucursal sucursal;
	//Constructores, getters y setters
	public Trabajador()
	{
		
	}
	public Trabajador(Long id, String nombre, String direccion, String telefono, String correoElectronico,
			Sucursal sucursal) 
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.sucursal = sucursal;
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
	public String getDireccion() 
	{
		return direccion;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	public String getCorreoElectronico() 
	{
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) 
	{
		this.correoElectronico = correoElectronico;
	}
	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) 
	{
		this.sucursal = sucursal;
	}
}