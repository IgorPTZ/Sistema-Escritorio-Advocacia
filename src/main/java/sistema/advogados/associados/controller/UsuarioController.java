package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.service.RoleService;
import sistema.advogados.associados.service.UsuarioService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {
	
	private String loginDoUsuarioLogado;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value="/obter-usuarios", method=RequestMethod.GET)
	public ModelAndView obterUsuarios(ModelAndView model, @PageableDefault(size = 20) Pageable pageable, @RequestParam(value="login") String loginDoUsuarioLogado) {
		
		try {
			
			this.loginDoUsuarioLogado = loginDoUsuarioLogado;
			
			Page<Usuario> clientes = usuarioService.obterUsuariosPaginados(pageable);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
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
	public ModelAndView obterUsuarioPorId(ModelAndView model, @RequestParam(value="id") Long id, @RequestParam(value="login") String loginDoUsuarioLogado) {
		
		try {
			
			this.loginDoUsuarioLogado = loginDoUsuarioLogado;
			
			Usuario usuario = usuarioService.obterUsuario(id);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
			model.addObject("usuario", usuario);
			
			model.addObject("perfis", roleService.obterPerfis());
			
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
			
			this.loginDoUsuarioLogado = login;
			
			Usuario usuario = usuarioService.obterUsuarioPorLogin(login);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
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
	public ModelAndView carregarNovoUsuario(ModelAndView model, @RequestParam(value="login") String loginDoUsuarioLogado) {
		
		try {
			this.loginDoUsuarioLogado = loginDoUsuarioLogado;
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
			model.addObject("usuario", new Usuario());
			
			model.addObject("perfis", roleService.obterPerfis());
			
			model.setViewName("inserir-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-usuario", method=RequestMethod.POST) 
	public ModelAndView inserirUsuario(ModelAndView model, @ModelAttribute Usuario usuario) {
		
		try {
				
			usuarioService.inserirUsuario(usuario);
			
			Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
			model.addObject("page", 0L);
			
			model.addObject("size", 20L);
			
			model.addObject("usuariosPaginados", usuarios);
			
			model.setViewName("listar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-usuario", method=RequestMethod.POST) 
	public ModelAndView editarUsuario(ModelAndView model, @ModelAttribute Usuario usuario) {
		
		try {
				
			usuarioService.editarUsuario(usuario);
			
			Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
			model.addObject("page", 0L);
			
			model.addObject("size", 20L);
			
			model.addObject("usuariosPaginados", usuarios);
			
			model.setViewName("listar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/excluir-usuario", method=RequestMethod.GET) 
	public ModelAndView desativarUsuario(ModelAndView model, @RequestParam(value="id") Long id, @RequestParam(value="login") String loginDoUsuarioLogado) {
		
		try {
				
			usuarioService.excluirUsuario(id);
			
			Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
			model.addObject("page", 0L);
			
			model.addObject("size", 20L);
			
			model.addObject("usuariosPaginados", usuarios);
			
			model.setViewName("listar-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
