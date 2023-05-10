package com.farmacia.core.modelo;
import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cantidad")
    private Long cantidad;
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;
    //Constructores, getters y setters
    public Stock() 
    {
    	
    }
    public Stock(Long cantidad, Producto producto) 
    {
    	super();
        this.cantidad = cantidad;
        this.producto = producto;
    }
    public Long getId() 
    {
        return id;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }
    public Long getCantidad() 
    {
        return cantidad;
    }
    public void setCantidad(Long cantidad) 
    {
        this.cantidad = cantidad;
    }
    public Producto getProducto() 
    {
        return producto;
    }
    public void setProducto(Producto producto) 
    {
        this.producto = producto;
    }
}