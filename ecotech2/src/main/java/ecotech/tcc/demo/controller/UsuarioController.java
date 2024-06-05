package ecotech.tcc.demo.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import ecotech.tcc.demo.model.Usuario;
import ecotech.tcc.demo.repository.UsuarioReopository;

@Controller
@RequestMapping("/ecotech/cliente")

//carregar o formulario de cadastro do cliente
public class UsuarioController {
	
	@Autowired
	private UsuarioReopository clienteRepository;
	public static Usuario usuarioAtual; //o usuario que esta logado na conta atualmente
	public boolean LoginErrado;
	
	@GetMapping("/perfil")
	public String telaPerfil(Model model, Usuario cliente) {
		if(usuarioAtual != null) {
			model.addAttribute("cliente", usuarioAtual); //puxa o id do usuario que esta logado na conta atualmente
			return "intranet/perfil/TelaPerfil";
		}
		return "intranet/perfil/login";
		
	}
	@GetMapping("/editar")
	public String EditarPerfil(Model model) {
		model.addAttribute("cliente", usuarioAtual); //puxa o id do usuario que esta logado na conta atualmente
		return "intranet/perfil/EditarPerfil";
	}
	@PostMapping("/update")
	public String atualizar(Usuario cliente, BindingResult result) {
		
		usuarioAtual.setNome(cliente.getNome());
		usuarioAtual.setEmail(cliente.getEmail());
		usuarioAtual.setSenha(cliente.getSenha());
		
		clienteRepository.save(usuarioAtual);
		return "redirect:/ecotech/cliente/perfil";
			
	}
	
	@GetMapping("/logout")
	public String logOut() {
		
		usuarioAtual = null;
		return "redirect:/ecotech/cliente/index";
	}
	

	@GetMapping("/login")
	public String login(Usuario cliente, Model model) {
		
		
		model.addAttribute("problem", LoginErrado);
		model.addAttribute("cliente", cliente);
		return "intranet/perfil/Login";
	}
		
	@PostMapping("/login")
	public String efetuarLogin(Usuario cliente, Model model) {
	
		String page = "redirect:/ecotech/cliente/login";
		Usuario clientedb = clienteRepository.findByEmail(cliente.getEmail());
		if(clientedb != null && cliente.getSenha().equals(clientedb.getSenha()) && clientedb.isStatusUsuario()) {
			page = "redirect:/ecotech/cliente/perfil";
			
			usuarioAtual = clienteRepository.findByEmail(cliente.getEmail());
			LoginErrado = false;
		}
		else {
			LoginErrado = true;
			return page;
		}
		
		
		return page;
	}
	
	
		
	@GetMapping("/novo-cliente")
	public String novocliente(Usuario cliente, Model model){
		
		
		
		model.addAttribute("cliente", cliente);
		return "intranet/perfil/CadastrarCliente";
	
	
	
	}
	
	@PostMapping("/add-cliente")
	public String addCliente(Usuario cliente, Model model) {
		
		cliente.setStatusUsuario(true); 
		Usuario clientedb = clienteRepository.findByEmail(cliente.getEmail());
		if(clientedb != null && clientedb.isStatusUsuario())
		{
			return "redirect:/ecotech/cliente/novo-cliente";
		}
		else {
			
			cliente.setDataCadastro(LocalDateTime.now());
			Usuario clienteDb = clienteRepository.save(cliente);
			return "redirect:/ecotech/cliente/login";
		}
	
		
	}
	
	
	
	
	@GetMapping("/excluir")
	public String excluir() {
		usuarioAtual.setStatusUsuario(false);
		usuarioAtual.setEmail(null); 
		clienteRepository.save(usuarioAtual);
		usuarioAtual = null;
		
		return "redirect:/ecotech/index";
	}
	

	@GetMapping("/index")
	public String index() {
		LoginErrado = false;
		return "redirect:/ecotech/index";
		
	}

	
}
