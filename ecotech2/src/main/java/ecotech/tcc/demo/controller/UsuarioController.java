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
@RequestMapping("/ecotech/user")

//carregar o formulario de cadastro do user
public class UsuarioController {
	
	@Autowired
	private UsuarioReopository userRepository;
	public static Usuario usuarioAtual; //o usuario que esta logado na conta atualmente
	public boolean LoginErrado;
	
	

	
	@GetMapping("/perfil")
	public String telaPerfil(Model model, Usuario user) {
		if(usuarioAtual != null) {
			model.addAttribute("user", usuarioAtual); //puxa o id do usuario que esta logado na conta atualmente
			return "intranet/perfil/TelaPerfil";
		}
	
		return "redirect:/ecotech/user/login";
		
	}
	@GetMapping("/editar")
	public String EditarPerfil(Model model) {
		model.addAttribute("user", usuarioAtual); //puxa o id do usuario que esta logado na conta atualmente
		return "intranet/perfil/EditarPerfil";
	}
	@PostMapping("/update")
	public String atualizar(Usuario user, BindingResult result) {
		
		usuarioAtual.setNome(user.getNome());
		usuarioAtual.setEmail(user.getEmail());
		usuarioAtual.setSenha(user.getSenha());
		
		userRepository.save(usuarioAtual);
		return "redirect:/ecotech/user/perfil";
			
	}
	
	@GetMapping("/logout")
	public String logOut() {
		
		usuarioAtual = null;
		return "redirect:/ecotech/user/index";
	}
	

	@GetMapping("/login")
	public String login(Usuario user, Model model) {
		
		if(usuarioAtual!= null) {
			return "redirect:/ecotech/user/perfil";
		}
	
		model.addAttribute("usuario", user);
		model.addAttribute("problem", LoginErrado);
		return "intranet/perfil/Login";
	}
		
	@PostMapping("/login")
	public String efetuarLogin(Usuario user, Model model) {
	
		String page = "redirect:/ecotech/user/login";
		Usuario userdb = userRepository.findByEmail(user.getEmail());
		if(userdb != null && user.getSenha().equals(userdb.getSenha()) && userdb.isStatusUsuario()) {
			page = "redirect:/ecotech/user/perfil";
			
			usuarioAtual = userRepository.findByEmail(user.getEmail());
			LoginErrado = false;
		}
		else {
			LoginErrado = true;
			return page;
		}
		
		
		return page;
	}
	
	
		
	@GetMapping("/novo-user")
	public String novouser(Usuario user, Model model){
		
		if(usuarioAtual == null || !usuarioAtual.getNivelAcesso().equals("ADMIN")) {
			
			return "redirect:/ecotech/user/perfil";
		}
		
		model.addAttribute("user", user);
		return "intranet/perfil/CadastrarFuncionario";
	
	
	
	}
	
	@PostMapping("/add-user")
	public String adduser(Usuario user, Model model) {
		if(usuarioAtual == null || !usuarioAtual.getNivelAcesso().equals("ADMIN")) {
			return "intranet/perfil/login";
		}
		user.setStatusUsuario(true); 
		Usuario userdb = userRepository.findByEmail(user.getEmail());
		if(userdb != null && userdb.isStatusUsuario())
		{
			model.addAttribute("user", user);
			model.addAttribute("erroMsg", "Já existe um funcionário com esse email");
			return "intranet/perfil/CadastrarFuncionario";
		}
		else {
			
			user.setDataCadastro(LocalDateTime.now());
			Usuario userDb = userRepository.save(user);
			
			return "redirect:/ecotech/user/perfil";
		}
	
		
	}
	
	
	
	
	@GetMapping("/excluir")
	public String excluir() {
		usuarioAtual.setStatusUsuario(false);
		usuarioAtual.setEmail(""); 
		userRepository.save(usuarioAtual);
		usuarioAtual = null;
		
		return "redirect:/ecotech/index";
	}
	

	@GetMapping("/index")
	public String index() {
		LoginErrado = false;
		return "redirect:/ecotech/index";
		
	}

	
}
