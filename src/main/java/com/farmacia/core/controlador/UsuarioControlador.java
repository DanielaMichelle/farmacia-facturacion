package com.farmacia.core.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.farmacia.core.modelo.Rol;
import com.farmacia.core.modelo.Sucursal;
import com.farmacia.core.modelo.Usuario;
import com.farmacia.core.modelo.UsuarioRol;
import com.farmacia.core.modelo.dto.UsuarioRegistroDTO;
import com.farmacia.core.seguridad.VerificadorSesion;
import com.farmacia.core.servicio.rol.RolServicio;
import com.farmacia.core.servicio.sucursal.SucursalServicio;
import com.farmacia.core.servicio.usuario.UsuarioServicio;
import com.farmacia.core.servicio.usuariorol.UsuarioRolServicio;
import com.farmacia.core.sesion.UserSession;

@Controller
public class UsuarioControlador 
{
	@Autowired
	private UserSession userSession;
	@Autowired
	private UsuarioServicio servicio;
	@Autowired
	private SucursalServicio servicioSucursal;
	@Autowired
	private RolServicio servicioRol;
	@Autowired
	private UsuarioRolServicio servicioUsuarioRol;
	private VerificadorSesion verificadorSesion;
	@GetMapping("/usuarios")
	public String panelUsuario(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		model.addAttribute("usuarios", servicio.mostrarUsuarios());
	    return verificadorSesion.validarRuta("usuarios/panelUsuarios");
	}
	@GetMapping("/usuarios/nuevoUsuario")
	public String formularioRegistroUsuario(Model model) 
	{
		verificadorSesion = new VerificadorSesion(userSession);
		if (userSession.getNombreRol().equals("Gerente"))
		{
			Usuario usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			model.addAttribute("sucursales", servicioSucursal.mostrarSucursales());
			model.addAttribute("roles", servicioRol.mostrarRoles());
			return verificadorSesion.validarRuta("usuarios/nuevoUsuario");
		}
		else
		{
			model.addAttribute("errorPermiso", "No tienes permiso para"
					+ " esta Acción."
					+ "\n Porque tu Rol es: "
					+ userSession.getNombreRol());
			model.addAttribute("usuarios", servicio.mostrarUsuarios());
	    		return verificadorSesion.validarRuta("usuarios/panelUsuarios");
		}
	}
	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO,
	                                @RequestParam("inputSucursalSeleccionado") Long idSucursal,
	                                @RequestParam("inputRolSeleccionado") Long idRol,
	                                Model model)  
	{
	    Sucursal sucursal = servicioSucursal.getSucursalPorId(idSucursal);
	    registroDTO.setSucursal(sucursal);
	    Usuario usuarioActual = servicio.guardar(registroDTO);
	    Rol rol = servicioRol.getRolPorId(idRol);
	    UsuarioRol usuariorol = new UsuarioRol();
	    usuariorol.setUsuario(usuarioActual);
	    usuariorol.setRol(rol);
	    servicioUsuarioRol.actualizarUsuarioRol(usuariorol);
	    model.addAttribute("usuarios", servicio.mostrarUsuarios());
		return verificadorSesion.validarRuta("usuarios/panelUsuarios");
	}

}
