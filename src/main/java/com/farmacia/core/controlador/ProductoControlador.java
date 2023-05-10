package com.farmacia.core.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.farmacia.core.modelo.Producto;
import com.farmacia.core.modelo.Stock;
import com.farmacia.core.modelo.Sucursal;
import com.farmacia.core.seguridad.VerificadorSesion;
import com.farmacia.core.servicio.producto.ProductoServicio;
import com.farmacia.core.servicio.stock.StockServicio;
import com.farmacia.core.servicio.sucursal.SucursalServicio;
import com.farmacia.core.sesion.UserSession;

@Controller
public class ProductoControlador 
{
	@Autowired
	private UserSession userSession;
	@Autowired
	private ProductoServicio servicio;
	@Autowired
	private SucursalServicio servicioSucursal;
	@Autowired
	private StockServicio servicioStock;
	private VerificadorSesion verificadorSesion;
	@GetMapping("/productos")
	public String panelProductos(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		Long id_sucursal = userSession.getIdSucursal();
		model.addAttribute("productos", servicio.mostrarProductos(id_sucursal));
	    return verificadorSesion.validarRuta("productos/panelProductos");
	}
	@GetMapping("/productos/nuevoProducto")
	public String formularioRegistroProducto(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		Long id_sucursal = userSession.getIdSucursal();
		if (userSession.getNombreRol().equals("Gerente"))
		{
			
			Producto producto = new Producto();
			model.addAttribute("producto", producto);
			model.addAttribute("id_sucursal", id_sucursal);
			return verificadorSesion.validarRuta("productos/nuevoProducto");
		}
		else
		{
			model.addAttribute("errorPermiso", "No tienes permiso para"
					+ " esta Acción."
					+ "\n Porque tu Rol es: "
					+ userSession.getNombreRol());
			model.addAttribute("productos", servicio.mostrarProductos(id_sucursal));
			return verificadorSesion.validarRuta("productos/panelProductos");
		}
	}
	@PostMapping("/guardarProducto")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) 
	{
		Long sucursalId = userSession.getIdSucursal();
		Sucursal sucursal = servicioSucursal.getSucursalPorId(sucursalId);
		producto.setSucursal(sucursal);
	    servicio.guardarProducto(producto);
	    return "redirect:/productos";
	}
	@GetMapping("/productos/formularioEditarProducto/{id}")
	public String formularioEditarProducto(@PathVariable long id, Model model)
	{
		Long sucursalId = userSession.getIdSucursal();
		verificadorSesion = new VerificadorSesion(userSession);
		if (userSession.getNombreRol().equals("Gerente"))
		{
			model.addAttribute("producto", servicio.getProductoPorId(id));
	    		return verificadorSesion.validarRuta("productos/editarProducto");
		}
		else
		{
			model.addAttribute("errorPermiso", "No tienes permiso para"
					+ " esta Acción."
					+ "\n Porque tu Rol es: "
					+ userSession.getNombreRol());
			model.addAttribute("productos", servicio.mostrarProductos(sucursalId));
			return verificadorSesion.validarRuta("productos/panelProductos");
		}
	}
	@PostMapping("/productos/{id}")
	public String actualizarProducto(@PathVariable long id, @ModelAttribute("producto") Producto producto, Model model) 
	{
		Long sucursalId = userSession.getIdSucursal();
		Sucursal sucursal = servicioSucursal.getSucursalPorId(sucursalId);
		Producto productoExistente = servicio.getProductoPorId(id);
		productoExistente.setId(id);
		productoExistente.setNombre(producto.getNombre());
		productoExistente.setPrecio(producto.getPrecio());
		productoExistente.setSucursal(sucursal);
		servicio.actualizarProducto(productoExistente);
		return "redirect:/productos";
	}
	@GetMapping("/productos/formularioStock/{id}")
	public String formularioStock(@PathVariable long id, Model model)
	{
	    verificadorSesion = new VerificadorSesion(userSession);
	    Long stockActual = servicioStock.obtenerUltimoStockPorProducto(id);
	    Producto productoActual = servicio.getProductoPorId(id);
	    Stock stock = new Stock();
	    String nombreProducto = productoActual.getNombre();
	    model.addAttribute("stockActual", stockActual.toString());
	    model.addAttribute("nombreProducto", nombreProducto);
	    model.addAttribute("producto", productoActual);
	    model.addAttribute("stock", stock);
	    return verificadorSesion.validarRuta("productos/verStock");
	}
	@PostMapping("/guardarStock/{id}")
	public String guardarStock(@PathVariable("id") Long id, @ModelAttribute("stock") Stock stock)
	{
		Producto producto = servicio.getProductoPorId(id);
		stock.setProducto(producto);
		servicioStock.guardarStock(stock);
	    return "redirect:/productos";
	}
}