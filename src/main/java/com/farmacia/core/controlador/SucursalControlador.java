package com.farmacia.core.controlador;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.farmacia.core.modelo.Sucursal;
import com.farmacia.core.seguridad.VerificadorSesion;
import com.farmacia.core.servicio.sucursal.SucursalServicio;
import com.farmacia.core.sesion.UserSession;

@Controller
public class SucursalControlador 
{
	@Autowired
	private SucursalServicio servicio;
	@Autowired
	private UserSession userSession;
	private VerificadorSesion verificadorSesion;
	@GetMapping("/sucursales")
	public String panelSucursales(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		model.addAttribute("sucursales", servicio.mostrarSucursales());
		return verificadorSesion.validarRuta("sucursales/panelSucursales");
	}
	@GetMapping("/sucursales/nuevaSucursal")
	public String formularioRegistroSucursal(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		if (userSession.getNombreRol().equals("Gerente"))
		{
			Sucursal sucursal = new Sucursal();
			model.addAttribute("sucursal", sucursal);
			return verificadorSesion.validarRuta("sucursales/nuevaSucursal");
		}
		else
		{
			model.addAttribute("errorPermiso", "No tienes permiso para"
					+ " esta Acción."
					+ "\n Porque tu Rol es: "
					+ userSession.getNombreRol());
			model.addAttribute("sucursales", servicio.mostrarSucursales());
			return verificadorSesion.validarRuta("sucursales/panelSucursales");
		}
	}
	@PostMapping("/guardarSucursal")
	public String guardarSucursal(@ModelAttribute("sucursal") Sucursal sucursal, HttpServletRequest request, Model model) 
	{
	    servicio.guardarSucursal(sucursal);
	    return "redirect:/sucursales";
	}
	@GetMapping("/sucursales/formularioEditarSucursal/{id}")
	public String formularioEditarSucursal(@PathVariable long id, Model model)
	{
		verificadorSesion = new VerificadorSesion(userSession);
		if (userSession.getNombreRol().equals("Gerente"))
		{
			model.addAttribute("sucursal", servicio.getSucursalPorId(id));
			return verificadorSesion.validarRuta("sucursales/editarSucursal");
		}
		else
		{
			model.addAttribute("errorPermiso", "No tienes permiso para"
					+ " esta Acción."
					+ "\n Porque tu Rol es: "
					+ userSession.getNombreRol());
			model.addAttribute("sucursales", servicio.mostrarSucursales());
			return verificadorSesion.validarRuta("sucursales/panelSucursales");
		}
	}
	@PostMapping("/sucursales/{id}")
	public String actualizarSucursal(@PathVariable long id, @ModelAttribute("sucursal") Sucursal sucursal, Model model) 
	{
	    Sucursal sucursalExistente = servicio.getSucursalPorId(id);
	    sucursalExistente.setId(id);
	    sucursalExistente.setNombre(sucursal.getNombre());
	    sucursalExistente.setDireccion(sucursal.getDireccion());
	    sucursalExistente.setTelefono(sucursal.getTelefono());
	    sucursalExistente.setCorreo_electronico(sucursal.getCorreo_electronico());
	    servicio.actualizarSucursal(sucursalExistente);
	    return "redirect:/sucursales";
	}

}