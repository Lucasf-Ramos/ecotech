package ecotech.tcc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ecotech.tcc.demo.model.Cliente;
import ecotech.tcc.demo.repository.ClienteReopository;

@Controller
@RequestMapping("/ecotech/cliente")

//carregar o formulario de cadastro do cliente
public class ClienteController {
	
	@Autowired
	private ClienteReopository clienteRepository;
	
	@GetMapping("/novo-cliente")
	public String novocliente(Cliente cliente, Model model){
		
		model.addAttribute("cliente", cliente);
		return "CadastrarCliente";
	}
	
	@PostMapping("/add-cliente")
	public String addCliente(Cliente cliente, Model model) {
		
		cliente.setCodStatusUsuario(true);
		
		Cliente clienteDb = clienteRepository.save(cliente);
		return "redirect:/ecotech/cliente/index";
	}
	
	
	
	
	
}
