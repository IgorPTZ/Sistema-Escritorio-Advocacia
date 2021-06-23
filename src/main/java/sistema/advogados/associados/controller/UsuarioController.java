package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
			
			model.setViewName("usuario/listar-usuario");
			
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
			
			Usuario usuario = usuarioService.obterUsuarioPorId(id);
			
			model.addObject("login", this.loginDoUsuarioLogado);
			
			model.addObject("usuario", usuario);
			
			model.addObject("perfis", roleService.obterPerfis());
			
			model.setViewName("usuario/editar-usuario");
			
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
			
			model.setViewName("usuario/alterar-senha-usuario");
			
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
			
			model.setViewName("usuario/inserir-usuario");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-usuario", method=RequestMethod.POST) 
	public ModelAndView inserirUsuario(ModelMap model, @ModelAttribute Usuario usuario) {
		
		try {
			
			if(usuarioService.obterUsuarioPorLogin(usuario.getLogin()) != null) {
				
				model.addAttribute("login", this.loginDoUsuarioLogado);
				
				model.addAttribute("usuario", usuario);
				
				model.addAttribute("perfis", roleService.obterPerfis());
				
				model.addAttribute("mensagem","O login inserido ja foi cadastro no sistema. Por favor, escolha outro login.");
			}
			else {
				usuarioService.inserirUsuario(usuario);
				
				Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
				
				model.addAttribute("login", this.loginDoUsuarioLogado);
				
				model.addAttribute("page", 0L);
				
				model.addAttribute("size", 20L);
				
				model.addAttribute("usuariosPaginados", usuarios);
			}
			
			return new ModelAndView("redirect:/obter-usuarios", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-usuario", method=RequestMethod.POST) 
	public ModelAndView editarUsuario(ModelMap model, @ModelAttribute Usuario parametros) {
		
		try {
			
			Usuario usuario = usuarioService.obterUsuarioPorId(parametros.getId());
			
			if(usuario != null && (parametros.getLogin().equals(usuario.getLogin()))){
				
				usuarioService.editarUsuario(parametros);
				
				Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
				
				model.addAttribute("usuariosPaginados", usuarios);
			}
			else if(usuario != null && (parametros.getLogin().equals(usuario.getLogin()) == false)) {
				
				if(usuarioService.obterUsuarioPorLogin(parametros.getLogin()) != null) {
					
					model.addAttribute("usuario", parametros);
					
					model.addAttribute("perfis", roleService.obterPerfis());
					
					model.addAttribute("mensagem","O login inserido ja foi cadastro no sistema. Por favor, escolha outro login.");
				}
				else {
					
					usuarioService.editarUsuario(parametros);
					
					Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
					
					model.addAttribute("usuariosPaginados", usuarios);
				}
			}
			
			model.addAttribute("login", this.loginDoUsuarioLogado);

			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/obter-usuarios", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/excluir-usuario", method=RequestMethod.GET) 
	public ModelAndView desativarUsuario(ModelMap model, @RequestParam(value="id") Long id, @RequestParam(value="login") String loginDoUsuarioLogado) {
		
		try {
				
			usuarioService.excluirUsuario(id);
			
			Page<Usuario> usuarios = usuarioService.obterUsuariosPaginados(0L, 20L);
			
			model.addAttribute("login", this.loginDoUsuarioLogado);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			model.addAttribute("usuariosPaginados", usuarios);
			
			return new ModelAndView("redirect:/obter-usuarios", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/alterar-senha", method=RequestMethod.POST) 
	public ModelAndView alterarSenha(ModelMap model, @ModelAttribute Usuario parametros) {
		
		try {
				
			if(usuarioService.alterarSenha(parametros)) {
				
				return new ModelAndView("redirect:/index", model);
			}
			else {
				model.addAttribute("login", this.loginDoUsuarioLogado);
				
				model.addAttribute("usuario", parametros);
				
				model.addAttribute("mensagem","Senha atual está incorreta!");
				
				return new ModelAndView("usuario/alterar-senha-usuario", model);
			}
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
