package com.farmacia.core.modelo.dto;

public class DetalleCompra 
{
	private String nombreProducto;
    private String precioUnitario;
    private String cantidad;
    private String subtotal;
    public DetalleCompra(String nombreProducto, String precioUnitario, String cantidad, String subtotal) 
    {
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
	public String getNombreProducto() 
	{
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) 
	{
		this.nombreProducto = nombreProducto;
	}
	public String getPrecioUnitario() 
	{
		return precioUnitario;
	}
	public void setPrecioUnitario(String precioUnitario) 
	{
		this.precioUnitario = precioUnitario;
	}
	public String getCantidad() 
	{
		return cantidad;
	}
	public void setCantidad(String cantidad) 
	{
		this.cantidad = cantidad;
	}
	public String getSubtotal() 
	{
		return subtotal;
	}
	public void setSubtotal(String subtotal) 
	{
		this.subtotal = subtotal;
	}
}