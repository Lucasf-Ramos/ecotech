package ecotech.tcc.demo.controller;


import java.util.Base64;
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

import ecotech.tcc.demo.model.GrupoResiduos;
import ecotech.tcc.demo.model.Local;
import ecotech.tcc.demo.model.Usuario;
import ecotech.tcc.demo.repository.GrupoResiduosReopository;
import ecotech.tcc.demo.repository.LocalReopository;


@Controller
@RequestMapping("/ecotech/locais")

//carregar o formulario de cadastro do cliente
public class LocalController {
	
	@Autowired
	private LocalReopository localRepository;
	@Autowired
	private GrupoResiduosReopository grupoRepository;
	String foto;
	

	

	@GetMapping("/todos-locais") 
	public String todos(Model model) {
		
		
		if(UsuarioController.usuarioAtual != null) {
			
			model.addAttribute("locais", localRepository.findAll()); //procura por todos os produtos do banco de dados		
		    return "intranet/locais"; //retorna o nome da pagina que deve ser aberta
		}
		return "redirect:/ecotech/user/login"; 
	}
	
	@PostMapping("/todos-locais/filter") 
	public String filtrar(Model model, @RequestParam("status") int status) {
	    if (UsuarioController.usuarioAtual != null) {
	        if (status > 1) {
	        	model.addAttribute("title", "");
	            return "redirect:/ecotech/locais/todos-locais";
	            
	        } else {
	        	
	        	String txt = status==0?"Todos locais inativos": "todos locais ativos";
	        	model.addAttribute("title", txt);
	        	
	            model.addAttribute("locais", localRepository.findByStatus(status == 0 ? false : true)); // Procura por todos os produtos do banco de dados
	            return "intranet/locais";
	        }
	    }
	    return "redirect:/ecotech/user/login"; 
	}
	@PostMapping("/todos-locais/pesquisa") 
	public String search(Model model, @RequestParam("name") String name) {
	    if (UsuarioController.usuarioAtual != null) {
	    		model.addAttribute("title", "você pesquisou por " + name);
	            model.addAttribute("locais", localRepository.findByNomeContaining(name)); 
	            return "intranet/locais";
	        
	    }
	    return "redirect:/ecotech/user/login"; 
	}

	
	
	@GetMapping("/editar-local/{id}")
	public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
		if (UsuarioController.usuarioAtual != null) {
		Local local = localRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid local Id:" + id));
		String resid = local.getGrupoResiduos().getNome();

		List<GrupoResiduos> grupoResiduosList = grupoRepository.findAll();
		List<String> cidades = localRepository.findAllUniqueCities();

		if (local.getFoto() != null) {
			foto = Base64.getEncoder().encodeToString(local.getFoto());
			model.addAttribute("foto", foto);
		}
		else {
			
			foto = "";
			model.addAttribute("foto", null);
		}
		
		model.addAttribute("local", local);
		model.addAttribute("residuo", resid);
		model.addAttribute("grupoResiduosList", grupoResiduosList);
		model.addAttribute("cidades", cidades);
		
		//model.addAttribute("tipos", EnumTipoLocal.values());

		
		return "intranet/editar-local";}
		 return "redirect:/ecotech/user/login"; 
		
	}

	@PostMapping("/update/{id}")
	public String atualizarLocal(
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id, 
			@ModelAttribute("local") Local local, BindingResult result) {

		if (result.hasErrors()) {
			local.setId((long)id);
			return "intranet/editar-local";
		}
		
		
		local.setSenha(localRepository.findById((long)id).orElseThrow(() -> new IllegalArgumentException("Invalid local Id:" + id)).getSenha());
		localRepository.save(local);
		return "redirect:/ecotech/locais/todos-locais";
	}
	

	
	
	
}
