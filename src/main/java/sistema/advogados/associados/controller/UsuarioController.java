package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.service.UsuarioService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	/*
	 * @RequestMapping(value="/obtertextosdeajuda", method=RequestMethod.GET)
	public ModelAndView obterTextosDeAjuda(ModelAndView model,
			                    @PageableDefault(size = 20) Pageable pageable,
			                    @RequestParam(value="nome", required=false) String nome) {
		
		try {
			
			Page<Ajuda> textosDeAjuda = ajudaService.obterTextosDeAjuda(pageable, nome);
			
			model.addObject("nome", nome);
			
			model.addObject("page", pageable.getPageNumber());
			
			model.addObject("size", pageable.getPageSize());
			
			model.addObject("textosDeAjudaPaginados", textosDeAjuda);
			
			model.setViewName("fragments/listagem-texto-ajuda :: listar-texto-ajuda");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	 */
	
	@RequestMapping(value="/obterusuario", method=RequestMethod.GET)
	public ModelAndView obterUsuario(ModelAndView model, @RequestParam(value="id") Long id) {
		
		try {
			
			Usuario usuario = usuarioService.obterUsuario(id);
			
			model.addObject("usuario", usuario);
			
			model.setViewName("edicao-usuario :: editar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
