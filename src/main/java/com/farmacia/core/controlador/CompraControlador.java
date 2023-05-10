package com.farmacia.core.controlador;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.farmacia.core.modelo.Compra;
import com.farmacia.core.modelo.CompraProducto;
import com.farmacia.core.modelo.Producto;
import com.farmacia.core.modelo.Stock;
import com.farmacia.core.modelo.Sucursal;
import com.farmacia.core.seguridad.VerificadorSesion;
import com.farmacia.core.servicio.compra.CompraServicio;
import com.farmacia.core.servicio.compraproducto.CompraProductoServicio;
import com.farmacia.core.servicio.producto.ProductoServicio;
import com.farmacia.core.servicio.stock.StockServicio;
import com.farmacia.core.servicio.sucursal.SucursalServicio;
import com.farmacia.core.sesion.UserSession;

@Controller
public class CompraControlador 
{
	@Autowired
	private UserSession userSession;
	private VerificadorSesion verificadorSesion;
	@Autowired
	private CompraServicio servicio;
	@Autowired
	private CompraProductoServicio servicioCompraProducto;
	@Autowired
	private SucursalServicio servicioSucursal;
	@Autowired
	private ProductoServicio servicioProducto;
	@Autowired
	private StockServicio servicioStock;
	@GetMapping("/compras")
	public String panelCompras(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		Long id_sucursal = userSession.getIdSucursal();
		model.addAttribute("compras", servicio.mostrarCompras(id_sucursal));
	    return verificadorSesion.validarRuta("compras/panelCompras");
	}
	@GetMapping("/compras/nuevaCompra")
	public String nuevaCompra(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		Long id_sucursal = userSession.getIdSucursal();
		Sucursal sucursal = servicioSucursal.getSucursalPorId(id_sucursal);
		Compra compra = new Compra(sucursal);
		Compra compraGuardada = servicio.guardarCompra(compra);
		Long idCompra = compraGuardada.getId();
		model.addAttribute("compras", servicio.mostrarUltimaCompra(idCompra));
		return verificadorSesion.validarRuta("compras/panelCompras");
	}
	@GetMapping("/mostrarCompras/{id}")
	public String mostrarCompras(@PathVariable("id") Long idCompra, Model model) 
	{
		model.addAttribute("detallesCompra", servicioCompraProducto.mostrarComprasProductos(idCompra));
		return verificadorSesion.validarRuta("compras/panelComprasProductos");
	}
	@GetMapping("/compras/formularioDetalle/{id}")
	public String formularioDetalle(@PathVariable long id, Model model)
	{
		verificadorSesion = new VerificadorSesion(userSession);
		Long id_sucursal = userSession.getIdSucursal();
		CompraProducto compraproducto = new CompraProducto();
		model.addAttribute("productos", servicioProducto.mostrarProductos(id_sucursal));
		model.addAttribute("compraproducto", compraproducto);
		return verificadorSesion.validarRuta("compras/nuevoDetalle");
	}
	@PostMapping("/guardarDetalle/{id}")
	public String guardarDetalle( @PathVariable("id") Long compraId, 
	                              @ModelAttribute("compraproducto") CompraProducto compraproducto,
	                              @RequestParam("productoSeleccionado") Long productoId,
	                              HttpServletRequest request, Model model) 
	{
		Compra compra = servicio.getCompraPorId(compraId);
		Producto producto = servicioProducto.getProductoPorId(productoId);
		double subtotal = producto.getPrecio().doubleValue() * compraproducto.getCantidad();
		compraproducto.setCompra(compra);
		compraproducto.setProducto(producto);
		compraproducto.setSubtotal(subtotal);
		model.addAttribute("compras", servicio.mostrarUltimaCompra(compraId));
		Long stockActual = servicioStock.obtenerUltimoStockPorProducto(productoId);
		if (stockActual < compraproducto.getCantidad())
		{
			verificadorSesion = new VerificadorSesion(userSession);
			Long id_sucursal = userSession.getIdSucursal();
			model.addAttribute("productos", servicioProducto.mostrarProductos(id_sucursal));
			model.addAttribute("compraproducto", compraproducto);
			model.addAttribute("errorStock", "No puedes sobrepasar el Stock Actual: " + stockActual.toString());
			return verificadorSesion.validarRuta("compras/nuevoDetalle");
		}
		else
		{
			servicioCompraProducto.guardarCompraProducto(compraproducto);
			//Agregamos el Nuevo Stock
			Stock stock = new Stock();
			long nueva_cantidad = stockActual.longValue() - compraproducto.getCantidad();
			stock.setCantidad(nueva_cantidad);
			stock.setProducto(producto);
			stock.setProducto(producto);
			servicioStock.guardarStock(stock);
			return verificadorSesion.validarRuta("compras/panelCompras");
		}
	}
}
