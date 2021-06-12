package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Cliente;
import sistema.advogados.associados.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/obter-clientes", method=RequestMethod.GET)
	public ModelAndView obterUsuarios(ModelAndView model, @PageableDefault(size = 20) Pageable pageable) {
		
		try {
			
			Page<Cliente> clientes = clienteService.obterClientesPaginados(pageable);
			
			model.addObject("page", pageable.getPageNumber());
			
			model.addObject("size", pageable.getPageSize());
			
			model.addObject("clientesPaginados", clientes);
			
			model.setViewName("listar-clientes");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/obter-cliente-por-id", method=RequestMethod.GET)
	public ModelAndView obterClientePorId(ModelAndView model, @RequestParam(value="id") Long id) {
		
		try {
			
			Cliente cliente = clienteService.obterClientePorId(id);
			
			model.addObject("cliente", cliente);
			
			model.setViewName("edicao-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/carregar-novo-cliente", method=RequestMethod.GET)
	public ModelAndView carregarNovoCliente(ModelAndView model) {
		
		try {
			
			model.addObject("cliente", new Cliente());
			
			model.setViewName("inserir-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-cliente", method=RequestMethod.POST)
	public String inserirCliente(Model model, @ModelAttribute Cliente cliente) {
		
		try {
			
			clienteService.inserirCliente(cliente);
			
			return "index";
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-cliente", method=RequestMethod.POST)
	public String editarCliente(ModelAndView model, @ModelAttribute Cliente cliente) {
		
		try {
			
			clienteService.editarCliente(cliente);
			
			return "index";
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
