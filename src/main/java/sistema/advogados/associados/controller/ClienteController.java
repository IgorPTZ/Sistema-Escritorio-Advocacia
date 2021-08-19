package sistema.advogados.associados.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Cliente;
import sistema.advogados.associados.model.Contato;
import sistema.advogados.associados.service.ClienteService;
import sistema.advogados.associados.util.PesquisaDeClientes;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/obter-clientes", method=RequestMethod.GET)
	public ModelAndView obterClientes(ModelAndView model, 
			                          @PageableDefault(size = 20) Pageable pageable) { 
		
		try {
			
			Page<Cliente> clientes = clienteService.obterClientesPaginados(pageable);
			
			model.addObject("page", pageable.getPageNumber());
			
			model.addObject("size", pageable.getPageSize());
			
			model.addObject("pesquisaDeClientes", new PesquisaDeClientes()); 
			
			model.addObject("clientesPaginados", clientes);
			
			model.setViewName("cliente/listar-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/obter-clientes-filtrados", method=RequestMethod.POST) 
	public ModelAndView obterClientesFiltrados(ModelAndView model, 
			                                   @PageableDefault(size = 20) Pageable pageable,
			                                   @ModelAttribute PesquisaDeClientes pesquisaDeClientes) { 
		
		try {
			
			Page<Cliente> clientes = clienteService.obterClientesPaginados(pageable, pesquisaDeClientes);
			
			model.addObject("page", pageable.getPageNumber());
			
			model.addObject("size", pageable.getPageSize());
			
			model.addObject("pesquisaDeClientes", pesquisaDeClientes); 
			
			model.addObject("clientesPaginados", clientes);
			
			model.setViewName("cliente/listar-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/obter-cliente-por-id", method=RequestMethod.GET) 
	public ModelAndView obterClientePorId(ModelAndView model, 
			                              @RequestParam(value="id") Long id,
			                              @RequestParam("page") Long page,
						                  @RequestParam("size") Long size) {
		
		try {
			
			Cliente cliente = clienteService.obterClientePorId(id);
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.addObject("cliente", cliente);
			
			model.setViewName("cliente/editar-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/detalhar-cliente-por-id", method=RequestMethod.GET)
	public ModelAndView detalharClientePorId(ModelAndView model, 
			                              @RequestParam(value="id") Long id,
			                              @RequestParam("page") Long page,
						                  @RequestParam("size") Long size) {
		
		try {
			
			Cliente cliente = clienteService.obterClientePorId(id);
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.addObject("cliente", cliente);
			
			model.setViewName("cliente/detalhar-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/carregar-novo-cliente", method=RequestMethod.GET)
	public ModelAndView carregarNovoCliente(ModelAndView model,
											@RequestParam("page") Long page,
								            @RequestParam("size") Long size) { 
		
		try {
			
			Cliente cliente = new Cliente();
			
			cliente.setContatos(new ArrayList<Contato>());
			
			for(int i = 0 ; i < 4 ; i++) {
				
				cliente.getContatos().add(new Contato());
			}
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.addObject("cliente", cliente);
			
			model.setViewName("cliente/inserir-cliente");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-cliente", method=RequestMethod.POST)
	public ModelAndView inserirCliente(ModelMap model, @ModelAttribute Cliente cliente) {
		
		try {
			
			boolean cpfECnpjNaoPreenchidos = (cliente.getCpf() == null && cliente.getCnpj() == null) || (cliente.getCpf().isEmpty() && cliente.getCnpj().isEmpty());
			
			if(cpfECnpjNaoPreenchidos) {
				
				model.addAttribute("page", 0L);
				
				model.addAttribute("size", 20L);
				
				model.addAttribute("cliente", cliente);
				
				model.addAttribute("mensagem", "CPF ou CNPJ devem ser preenchidos");
								
				return new ModelAndView("cliente/inserir-cliente", model);
			}
			
			if(cliente.getCpf().isEmpty()) {
				
				cliente.setCpf(null);
			}
			
			if(cliente.getCnpj().isEmpty()) {
				
				cliente.setCnpj(null);
			}
			
			clienteService.inserirCliente(cliente);
			
			Page<Cliente> clientes = clienteService.obterClientesPaginados(0L, 20L);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			model.addAttribute("clientesPaginados", clientes);
			
			model.addAttribute("cliente/listar-cliente");
			
			return new ModelAndView("redirect:/obter-clientes", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-cliente", method=RequestMethod.POST)
	public ModelAndView editarCliente(ModelMap model, @ModelAttribute Cliente cliente) {
		
		try {
			boolean cpfECnpjNaoPreenchidos = (cliente.getCpf() == null && cliente.getCnpj() == null) || (cliente.getCpf().isEmpty() && cliente.getCnpj().isEmpty());
			
			if(cpfECnpjNaoPreenchidos) {
				
				model.addAttribute("page", 0L);
				
				model.addAttribute("size", 20L);
				
				model.addAttribute("cliente", cliente);
				
				model.addAttribute("mensagem", "CPF ou CNPJ devem ser preenchidos");
								
				return new ModelAndView("cliente/editar-cliente", model);
			}
			
			
			if(cliente.getCpf().isEmpty()) {
				
				cliente.setCpf(null);
			}
			
			if(cliente.getCnpj().isEmpty()) {
				
				cliente.setCnpj(null);
			}
			
			clienteService.editarCliente(cliente);
			
			Page<Cliente> clientes = clienteService.obterClientesPaginados(0L, 20L);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			model.addAttribute("clientesPaginados", clientes);
			
			model.addAttribute("cliente/listar-cliente");
			
			return new ModelAndView("redirect:/obter-clientes", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
