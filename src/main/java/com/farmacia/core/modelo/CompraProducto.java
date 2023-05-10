package com.farmacia.core.modelo;
import javax.persistence.*;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "compras_productos")
public class CompraProducto
{
	@Id
	@GeneratedValue
	@Column(name = "compra_producto_id")
	private Long compraProductoId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "compra_id", nullable = false)
	@NotNull
	private Compra compra;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id", nullable = false)
	@NotNull
	private Producto producto;
	@Column(name = "cantidad", nullable = false)
	@NotNull
	private Long cantidad;
	@Column(name = "subtotal")
	private Double subtotal;
	//Constructores, getters y setters
	public CompraProducto() 
	{
		this.cantidad = Long.valueOf(1);
	}
	public Compra getCompra() 
	{
	    return compra;
	}
	public void setCompra(Compra compra) 
	{
	    this.compra = compra;
	}
	public Producto getProducto() 
	{
	    return producto;
	}
	public void setProducto(Producto producto)
	{
	    this.producto = producto;
	}
	public long getCantidad() 
	{
	    return cantidad;
	}
	public void setCantidad(long cantidad) 
	{
	    this.cantidad = cantidad;
	}
	public Double getSubtotal() 
	{
		return subtotal;
	}
	public void setSubtotal(Double subtotal) 
	{
		this.subtotal = subtotal;
	}
}