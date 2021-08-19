package sistema.advogados.associados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.util.PesquisaDeClientes;

@Controller
public class IndexController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getLogin(Model model){
		
		return "login";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView getIndex(ModelAndView model) {
		
		model.addObject("page", 0L);
		
		model.addObject("size", 20L);
		
		model.addObject("pesquisaDeClientes", new PesquisaDeClientes()); 
		
		model.setViewName("index");
		
		return model;
	}
}
