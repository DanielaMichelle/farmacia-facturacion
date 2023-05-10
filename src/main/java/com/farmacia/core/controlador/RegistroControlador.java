package com.farmacia.core.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.farmacia.core.modelo.Usuario;
import com.farmacia.core.servicio.usuario.UsuarioServicio;
import com.farmacia.core.sesion.UserSession;

@Controller
public class RegistroControlador 
{
	private Usuario visitante;
	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private UserSession userSession;
	@GetMapping("/login")
	public String iniciarSesion(Model model) 
	{
		return "login";
	}
	@PostMapping("/iniciar_sesion")
	  public String iniciarSesion(@RequestParam("username") String username,
	                              @RequestParam("password") String password,
	                              Model model) 
	{
	    // Aquí iría la lógica para autenticar al usuario y redirigirlo a la página adecuada
	    // Por ejemplo, podríamos verificar si el usuario y la contraseña coinciden con una cuenta existente
	    // Si son correctos, redirigir al usuario a la página de inicio
		Usuario usuarioIngresado = usuarioServicio.buscarUsuario(username, password);
		if (usuarioIngresado != null)
		{
			this.setVisitante(usuarioIngresado);
			String email = username;
			String nombre = usuarioServicio.recuperarNombrePorEmail(email);
			userSession.setNombre(nombre);
			String apellido = usuarioServicio.recuperarApellidoPorEmail(email);
			userSession.setApellido(apellido);
			String nombrePerfil = usuarioServicio.recuperarNombrePerfilPorEmail(email);
			userSession.setNombreRol(nombrePerfil);
			Long idSucursal = usuarioServicio.recuperarIdSucursalPorEmail(email);
			userSession.setIdSucursal(idSucursal);
			String nombreSucursal = usuarioServicio.recuperarNombreSucursalPorEmail(email);
			userSession.setNombreSucursal(nombreSucursal);
			String mensaje_bienvenida = "Hola "
					+ nombre + " " + apellido + ". "
					+ "Tu Perfil es: " + nombrePerfil + ". "
					+ "Tu Sucursal es: " + nombreSucursal  + ".";
			model.addAttribute("mensaje_bienvenida", mensaje_bienvenida);
			return "index";
		}
		// Si no son correctos, mostrar un mensaje de error en la página de inicio de sesión
		else
		{
			// Agregar un mensaje de error al modelo
			model.addAttribute("error", "Credenciales inválidas!!");
			model.addAttribute("sugerencia", "Asegúrate de ingresar bien el correo y contraseña.");
			return "login";
		}
	}
	@GetMapping("/logout")
	public String cerrarSesion(Model model) 
	{
		this.setVisitante(null);
		userSession.eliminarSesion();
		return "login";
	}
	@GetMapping("/")
	public String verPaginaDeInicio() 
	{
		
		if (this.getVisitante() == null)
		{
			return "login";
		}
		else
		{
			return "index";
		}
	}
	public Usuario getVisitante() 
	{
		return visitante;
	}
	public void setVisitante(Usuario visitante) 
	{
		this.visitante = visitante;
	}
	
}