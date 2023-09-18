package ecotech.tcc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ecotech/cliente")
public class ClienteController {
	
	@GetMapping("/novo-cliente")
	public String novocliente(){
		return "RegistrarCliente";
	}
}
