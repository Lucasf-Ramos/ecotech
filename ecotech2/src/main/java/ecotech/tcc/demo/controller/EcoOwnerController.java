package ecotech.tcc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecotech.tcc.demo.model.Local;
import ecotech.tcc.demo.model.Usuario;
import ecotech.tcc.demo.repository.LocalReopository;

@Controller
@RequestMapping("/ecotech/ecoponto")

//carregar o formulario de cadastro do cliente
public class EcoOwnerController {

	@Autowired
	private LocalReopository localRepository;
	private Local localAtual;

	@GetMapping("/perfil")
	public String telaPerfil(Model model, Usuario cliente) {
		if (localAtual != null) {
			model.addAttribute("local", localAtual); // puxa o id do usuario que esta logado na conta atualmente
			return " ";
		}
		return " ";

	}

	@GetMapping("/novo-local") //cria a conta
	public String novoProduto(Local local, Model model) {
		model.addAttribute("locais", local);
		return "intranet/novo-local";
	}

	@PostMapping("/add-local")
	public String gravarNovoProduto(Local local, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "intranet/novo-local";
		}
		local.setStatusPonto(false);
		localRepository.save(local);
		return " ";
	}
	@GetMapping("/login")
	public String login(Local local, Model model) {
		
		model.addAttribute("cliente", local);
		return "intranet/perfil/Login";
	}
	@PostMapping("/login")
	public String efetuarLogin(Local local, Model model) {
	
		String page = "";
		Local localdb = localRepository.findByEmail(local.getEmail());
		if(localdb != null && localdb.getSenha().equals(localdb.getSenha()) && localdb.isStatusPonto()) {
			page = "";
			
			localAtual = localRepository.findByEmail(local.getEmail());
		}
		
		
		return page;
	}
	
	@GetMapping("/editar-local")
	public String showUpdateForm(ModelMap model) {
		Local local = localAtual;

		model.addAttribute("local", local);

		return "intranet/editar-local";
	}

	@PostMapping("/update")
	public String atualizarLocal(Local local, BindingResult result) {

		local = localAtual;

		if (result.hasErrors()) {
			local.setId((long) localAtual.getId());
			return " ";
		}

		localRepository.save(local);
		return " ";
	}

}
