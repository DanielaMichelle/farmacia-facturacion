package com.farmacia.core.modelo;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	private String email;
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sucursal_id", referencedColumnName = "id")
	private Sucursal sucursal;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Collection<Rol> roles;
	//Constructores, getters y setters
	public Usuario() 
	{
		this.nombre = "";
		this.apellido = "";
		this.email = "";
		this.password = "";
		this.sucursal = null;
		this.roles = new ArrayList<Rol>();
	}
	public Usuario(String nombre, String apellido, String email, Sucursal sucursal, String password) 
	{
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
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
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) 
	{
		this.sucursal = sucursal;
	}
	public Collection<Rol> getRoles() 
	{
		return roles;
	}
	public void setRoles(Collection<Rol> roles) 
	{
		this.roles = roles;
	}
	public String getNombrePrimerRol()
	{
		String nombreRol = "Genérico";
		// Obtener la colección de roles del usuario
		Collection<Rol> roles = this.getRoles();

		// Verificar que la colección no esté vacía
		if (!roles.isEmpty()) 
		{
		    // Obtener el primer elemento de la colección
		    Rol primerRol = roles.iterator().next();
		    // Obtener el valor del atributo nombre del primer rol
		    nombreRol = primerRol.getNombre();
		}
		return nombreRol;
	}
}