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
	public boolean LoginErrado = false;
	String errorMsg;

	@GetMapping("/perfil")
	public String telaPerfil(Model model, Local local) {
		if (localAtual != null) {
			model.addAttribute("locais", localAtual); // puxa o id do usuario que esta logado na conta atualmente
			return "donoEcoponto/perfilDono";
		}
		return "donoEcoponto/login";

	}

	@GetMapping("/novo-local") // cria a conta
	public String novoProduto(Local local, Model model) {
		model.addAttribute("locais", local);
		return "intranet/novo-local";
	}

	@PostMapping("/add-local")
	public String gravarNovoProduto(Local local, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "donoEcoponto/novolocal";
		}
		local.setStatusPonto(false);
		localRepository.save(local);
		return "donoEcoponto/login";
	}

	@GetMapping("/login")
	public String login(Local local, Model model) {
		LoginErrado = true;
	
		model.addAttribute("locais", local);
		model.addAttribute("problem", LoginErrado);
		model.addAttribute("erro", errorMsg);
		return "donoEcoponto/login";
	}

	@PostMapping("/login")
	public String efetuarLogin(Local local, Model model) {

		String page = "redirect:/ecotech/ecoponto/login";
		Local localdb = localRepository.findByEmail(local.getEmail());

		if (localdb != null && localdb.getSenha().equals(local.getSenha()) && localdb.isStatusPonto()) {
			page = "redirect:/ecotech/ecoponto/perfil";

			localAtual = localRepository.findByEmail(local.getEmail());
			LoginErrado = false;
		
		} 
		else if (localdb != null && localdb.getSenha().equals(local.getSenha()) && !localdb.isStatusPonto()) {
			errorMsg = "Seu Ecoponto não está ativo no momento. \n Caso ainda tenha dúvida, entre em contato conosco";
		}
		else {
			LoginErrado = true;
			errorMsg = "Login ou senha errados";
		}

		return page;
	}

	@GetMapping("/editar")
	public String showUpdateForm(ModelMap model) {

		Local local = localAtual;
		model.addAttribute("local", local);

		return "donoEcoponto/editar";
	}

	@PostMapping("/update")
	public String atualizarLocal(Local local, BindingResult result) {

		// localAtual = local;

		if (result.hasErrors()) {
			local.setId((long) localAtual.getId());
			return "donoEcoponto/editar";
		}

		local.setStatusPonto(localAtual.isStatusPonto());
		localRepository.save(local);

		localAtual = local;
		return "redirect:/ecotech/ecoponto/perfil";
	}

	@GetMapping("/logout")
	public String logOut() {

		localAtual = null;
		return "redirect:/ecotech/cliente/index";
	}

}
