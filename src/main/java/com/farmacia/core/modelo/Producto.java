package com.farmacia.core.modelo;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Producto
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private BigDecimal precio;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sucursal_id")
	private Sucursal sucursal;
    //Constructores, getters y setters
    public Producto() 
    {
    	
    }
    public Producto(String nombre, BigDecimal precio, Long sucursalId) 
    {
        this.nombre = nombre;
        this.precio = precio;
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
    public BigDecimal getPrecio()
    {
        return precio;
    }
    public void setPrecio(BigDecimal precio) 
    {
        this.precio = precio;
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