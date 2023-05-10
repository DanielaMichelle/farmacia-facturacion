package com.farmacia.core.modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sucursales")
public class Sucursal 
{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  @Column(name = "nombre", nullable = false)
	  private String nombre;
	  @Column(name = "direccion")
	  private String direccion;
	  @Column(name = "telefono")
	  private String telefono;
	  @Column(name = "correo_electronico")
	  private String correo_electronico;
	  //Constructores, getters y setters
	  public Sucursal()
	  {
		  
	  }
	  public Sucursal(String nombre, String direccion, String telefono, String correo_electronico) 
	  {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo_electronico = correo_electronico;
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
	  public String getCorreo_electronico() 
	  {
		return correo_electronico;
	  }
	  public void setCorreo_electronico(String correo_electronico) 
	  {
		this.correo_electronico = correo_electronico;
	  }
}