package com.farmacia.core.servicio.sucursal;
import java.util.List;
import com.farmacia.core.modelo.Sucursal;

public interface SucursalServicio 
{
	public List<Sucursal> mostrarSucursales();
	public Sucursal guardarSucursal(Sucursal sucursal);
	public Sucursal getSucursalPorId(long id);
	public Sucursal actualizarSucursal(Sucursal sucursal);
}