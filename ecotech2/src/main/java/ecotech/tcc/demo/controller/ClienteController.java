package ecotech.tcc.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	

	private Cliente usuarioAtual; //o usuario que esta logado na conta atualmente
	
	
	
	
	
	@GetMapping("/perfil")
	public String telaPerfil(Model model, Cliente cliente) {
		if(usuarioAtual != null) {
			model.addAttribute("cliente", usuarioAtual); //puxa o id do usuario que esta logado na conta atualmente
			return "TelaPerfil";
		}
		return "login";
		
		
	}
	@GetMapping("/editar")
	public String EditarPerfil(Model model) {
		model.addAttribute("cliente", usuarioAtual); //puxa o id do usuario que esta logado na conta atualmente
		return "EditarPerfil";
	}
	@PostMapping("/update")
	public String atualizar(Cliente cliente, BindingResult result) {
		
		usuarioAtual.setNome(cliente.getNome());
		usuarioAtual.setSobrenome(cliente.getSobrenome());
		usuarioAtual.setCpf(cliente.getCpf());
		usuarioAtual.setTelefone(cliente.getTelefone());
		usuarioAtual.setEmail(cliente.getEmail());
		usuarioAtual.setDataNascimento(cliente.getDataNascimento());
		usuarioAtual.setSenha(cliente.getSenha());
		
		clienteRepository.save(usuarioAtual);
		return "redirect:/ecotech/cliente/perfil";
			
	}
	
	@GetMapping("/logout")
	public String logOut() {
		
		usuarioAtual = null;
		return "redirect:/ecotech/cliente/index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	


	@GetMapping("/login")
	public String login(Cliente cliente, Model model) {
		
		model.addAttribute("cliente", cliente);
		return "Login";
	}
		
	@PostMapping("/login")
	public String efetuarLogin(Cliente cliente, Model model) {
	
		String page = "redirect:/ecotech/cliente/login";
		Cliente clientedb = clienteRepository.findByEmail(cliente.getEmail());
		if(clientedb != null && cliente.getSenha().equals(clientedb.getSenha()) && clientedb.isCodStatusUsuario()) {
			page = "redirect:/ecotech/cliente/perfil";
			
			usuarioAtual = clienteRepository.findByEmail(cliente.getEmail());
		}
		
		
		return page;
	}
	
	
		
	@GetMapping("/novo-cliente")
	public String novocliente(Cliente cliente, Model model){
		
		
		
		model.addAttribute("cliente", cliente);
		return "CadastrarCliente";
	
	
	
	}
	
	@PostMapping("/add-cliente")
	public String addCliente(Cliente cliente, Model model) {
		
		cliente.setCodStatusUsuario(true);
		Cliente clientedb = clienteRepository.findByEmail(cliente.getEmail());
		if(clientedb != null && clientedb.isCodStatusUsuario())
		{
			return "redirect:/ecotech/cliente/novo-cliente";
		}
		else {
			Cliente clienteDb = clienteRepository.save(cliente);
			return "redirect:/ecotech/cliente/login";
		}
	
		
	}
	
	
	
	
	@GetMapping("/excluir")
	public String excluir() {
		usuarioAtual.setCodStatusUsuario(false);
		usuarioAtual.setEmail(null); 
		clienteRepository.save(usuarioAtual);
		usuarioAtual = null;
		
		return "redirect:/ecotech/cliente/index";
	}
	
	@GetMapping("/favorito")
	public String Fav() {
		return "ListaFavoritos";
	}
	@GetMapping("/carrinho")
	public String Car() {
		return "Carrinho";
	}
	@GetMapping("/visualizar")
	public String Visu() {
		return "VisualizarProduto";
	}
	@GetMapping("/busca")
	public String Busca() {
		return "busca";
	}
	@GetMapping("/senha")
	public String Senha() {
		return "EsqueciSenha";
	}
	@GetMapping("/senha2")
	public String Senha2() {
		return "EsqueciSenha2";
	}
	@GetMapping("/categoria")
	public String Categ() {
		return "categoria";
	}
	@GetMapping("/finalizar")
	public String fim() {
		return "finalizar";
	}
	
	
}
