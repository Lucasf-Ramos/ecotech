package ecotech.tcc.demo.controller;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ecotech/cliente")
public class ClienteController {
	
	@GetMapping("/novo-cliente")
	public String novocliente(Client cliente, Model model){
		
		model.addAttribute("cliente", cliente);
		return "CadastrarCliente";
	}
}
