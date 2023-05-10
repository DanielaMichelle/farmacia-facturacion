package com.farmacia.core.controlador;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteControlador 
{
	@GetMapping("/reportes")
	public String panelReportes() 
	{
	    return "reportes/panelReportes";
	}
}