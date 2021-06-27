package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Pericia;
import sistema.advogados.associados.service.PericiaService;
import sistema.advogados.associados.service.ProcessoService;

@Controller
public class PericiaController {
	
	private Long clienteId;
	
	private Long processoId;
	
	@Autowired
	private PericiaService periciaService;
	
	@Autowired
	private ProcessoService processoService;
	
	@RequestMapping(value="/carregar-nova-pericia", method=RequestMethod.GET) 
	public ModelAndView carregarNovaPericia(ModelAndView model,
										    @RequestParam("clienteId") Long clienteId,
											@RequestParam("processoId") Long processoId,
										    @RequestParam("page") Long page,
							                @RequestParam("size") Long size) { 
		
		try {
			
			this.clienteId = clienteId;
			
			this.processoId = processoId;
			
			Pericia pericia = new Pericia();
			
			pericia.setProcesso(processoService.obterProcessoPorId(processoId));
			
			model.addObject("pericia", pericia);
			
			model.addObject("clienteId", clienteId);
			
			model.addObject("processoId", processoId);
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.setViewName("pericia/inserir-pericia");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@RequestMapping(value="/inserir-pericia", method=RequestMethod.POST) 
	public ModelAndView inserirPericia(ModelMap model, @ModelAttribute Pericia pericia) { 
		
		try {
			
			periciaService.inserirPericia(pericia);
			
			model.addAttribute("id", this.processoId);
			
			model.addAttribute("clienteId", this.clienteId);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-pericia-por-id", method=RequestMethod.GET) 
	public ModelAndView editarPericiaPorId(ModelAndView model,
										   @RequestParam("id") Long id,
										   @RequestParam("clienteId") Long clienteId,
										   @RequestParam("processoId") Long processoId,
										   @RequestParam("page") Long page,
								           @RequestParam("size") Long size) { 
		
		try {
			
			this.clienteId = clienteId;
			
			this.processoId = processoId;
			
			model.addObject("clienteId", clienteId);
			
			model.addObject("processoId", processoId);
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.addObject("pericia", periciaService.obterPericiaPorId(id));
			
			model.setViewName("pericia/editar-pericia");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="editar-pericia", method=RequestMethod.POST) 
	public ModelAndView editarPericia(ModelMap model, @ModelAttribute Pericia pericia) { 
		
		try {
			
			periciaService.editarPericia(pericia);
			
			model.addAttribute("id", this.processoId);
			
			model.addAttribute("clienteId", this.clienteId);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="excluir-pericia-por-id", method=RequestMethod.GET) 
	public ModelAndView excluirPericia(ModelMap model, 		   
			                           @RequestParam("id") Long id,
			                           @RequestParam("clienteId") Long clienteId,
			                           @RequestParam("processoId") Long processoId,
			                           @RequestParam("page") Long page,
	                                   @RequestParam("size") Long size) { 
		
		try {
			
			periciaService.excluirPericia(id);
			
			model.addAttribute("id", processoId);
			
			model.addAttribute("clienteId", clienteId);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
