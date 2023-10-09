package ecotech.tcc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ecotech")
public class IndexController {

	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/perfil")
	public String telaPerfil() {
		return "TelaPerfil";
	}
	
	@GetMapping("/excluir")
	public String ExcluirPerfil() {
		return "ExcluirPerfil";
	}
	@GetMapping("/editar")
	public String EditarPerfil() {
		return "EditarPerfil";
	}
	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "CadastrarCliente";
	}
	
}
