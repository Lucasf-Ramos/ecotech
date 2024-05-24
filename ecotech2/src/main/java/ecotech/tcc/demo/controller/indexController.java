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


import ecotech.tcc.demo.model.Usuario;
import ecotech.tcc.demo.repository.UsuarioReopository;

@Controller
@RequestMapping("/ecotech")

//carregar o formulario de cadastro do cliente
public class indexController {
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
