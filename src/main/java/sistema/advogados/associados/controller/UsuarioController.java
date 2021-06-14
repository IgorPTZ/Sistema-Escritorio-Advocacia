package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.service.UsuarioService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value="/obter-usuarios", method=RequestMethod.GET)
	public ModelAndView obterUsuarios(ModelAndView model, @PageableDefault(size = 20) Pageable pageable) {
		
		try {
			
			Page<Usuario> clientes = usuarioService.obterUsuariosPaginados(pageable);
			
			model.addObject("page", pageable.getPageNumber());
			
			model.addObject("size", pageable.getPageSize());
			
			model.addObject("usuariosPaginados", clientes);
			
			model.setViewName("listar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/obter-usuario-por-id", method=RequestMethod.GET)
	public ModelAndView obterUsuarioPorId(ModelAndView model, @RequestParam(value="id") Long id) {
		
		try {
			
			Usuario usuario = usuarioService.obterUsuario(id);
			
			model.addObject("usuario", usuario);
			
			model.setViewName("editar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/obter-usuario-por-login", method=RequestMethod.GET)
	public ModelAndView obterUsuarioPorLogin(ModelAndView model, @RequestParam(value="login") String login) {
		
		try {
			
			Usuario usuario = usuarioService.obterUsuarioPorLogin(login);
			
			model.addObject("usuario", usuario);
			
			model.setViewName("editar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/carregar-novo-usuario", method=RequestMethod.GET)
	public ModelAndView carregarNovoUsuario(ModelAndView model) {
		
		try {
			
			model.addObject("usuario", new Usuario());
			
			model.setViewName("editar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-usuario", method=RequestMethod.POST)
	public String inserirUsuario(ModelAndView model, 
			                     @ModelAttribute Usuario usuario) {
		
		try {
			
			usuarioService.inserirUsuario(usuario);
			
			return "index";
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-usuario", method=RequestMethod.POST)
	public String editarUsuario(ModelAndView model, @ModelAttribute Usuario usuario) {
		
		try {
			
			usuarioService.editarUsuario(usuario);
			
			return "index";
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
