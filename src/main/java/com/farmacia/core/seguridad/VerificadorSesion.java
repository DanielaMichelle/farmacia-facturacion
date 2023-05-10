package com.farmacia.core.seguridad;
import org.springframework.beans.factory.annotation.Autowired;
import com.farmacia.core.sesion.UserSession;

public class VerificadorSesion 
{
	private UserSession userSession;
	@Autowired
    public VerificadorSesion(UserSession userSession) 
	{
        this.userSession = userSession;
    }
	public String validarRuta(String ruta)
	{
		if (userSession.getNombre() == null)
		{
			return "login";
		}
		else
		{
			return ruta;
		}
	}
	
}
