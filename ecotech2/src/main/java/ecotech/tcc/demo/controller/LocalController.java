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
@RequestMapping("/ecotech/locais")

//carregar o formulario de cadastro do cliente
public class LocalController {
	
	@Autowired
	private LocalReopository localRepository;
	
	@GetMapping("/todos-locais") 
	public String todos(Model model) {
		
		if(UsuarioController.usuarioAtual != null) {
			model.addAttribute("locais", localRepository.findAll()); //procura por todos os produtos do banco de dados		
		    return "intranet/locais"; //retorna o nome da pagina que deve ser aberta
		}
		return "redirect:/ecotech/cliente/login"; 
	}
	
	@GetMapping("/novo-local")
	public String novoProduto(Local local, Model model) {
		model.addAttribute("locais", local);
		return "intranet/novo-local";
	}
	
	@PostMapping("/add-local")
	public String gravarNovoProduto(Local local, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "intranet/novo-local";
		}
		local.setStatusPonto(true);
		localRepository.save(local);
		return "redirect:/ecotech/locais/todos-locais";
	}
	@GetMapping("/todos")
	public List<Local> listarTodos() {
		return localRepository.findAll();
	}

	
	@GetMapping("/editar-local/{id}")
	public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
		Local local = localRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid local Id:" + id));
	
		model.addAttribute("local", local);
		//model.addAttribute("tipos", EnumTipoLocal.values());

		
		return "intranet/editar-local";
	}

	@PostMapping("/update/{id}")
	public String atualizarLocal(
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id, 
			@ModelAttribute("local") Local local, BindingResult result) {

		if (result.hasErrors()) {
			local.setId((long)id);
			return "intranet/editar-local";
		}
		

		localRepository.save(local);
		return "redirect:/ecotech/locais/todos-locais";
	}
	

	
	
	
}
