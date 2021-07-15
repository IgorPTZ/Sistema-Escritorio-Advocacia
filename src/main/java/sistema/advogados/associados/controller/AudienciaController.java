package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Audiencia;
import sistema.advogados.associados.service.AudienciaService;
import sistema.advogados.associados.service.ProcessoService;

@Controller
public class AudienciaController {
	
	private Long clienteId;
	
	private Long processoId;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private AudienciaService audienciaService;
	
	@RequestMapping(value="/carregar-nova-audiencia", method=RequestMethod.GET) 
	public ModelAndView carregarNovaAudiencia(ModelAndView model,
										      @RequestParam("clienteId") Long clienteId,
											  @RequestParam("processoId") Long processoId,
										      @RequestParam("page") Long page,
								              @RequestParam("size") Long size) { 
		
		try {
			
			this.clienteId = clienteId;
			
			this.processoId = processoId;
			
			Audiencia audiencia = new Audiencia();
			
			audiencia.setProcesso(processoService.obterProcessoPorId(processoId));
			
			model.addObject("audiencia", audiencia);
			
			model.addObject("clienteId", clienteId);
			
			model.addObject("processoId", processoId);
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.setViewName("audiencia/inserir-audiencia");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-audiencia", method=RequestMethod.POST) 
	public ModelAndView inserirProcesso(ModelMap model, @ModelAttribute Audiencia audiencia) {
		
		try {

			audienciaService.inserirAudiencia(audiencia);
			
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
	
	@RequestMapping(value="/editar-audiencia-por-id", method=RequestMethod.GET) 
	public ModelAndView editarAudienciaPorId(ModelAndView model,
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
			
			model.addObject("audiencia", audienciaService.obterAudienciaPorId(id));
			
			model.setViewName("audiencia/editar-audiencia");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="editar-audiencia", method=RequestMethod.POST) 
	public ModelAndView editarAudiencia(ModelMap model, @ModelAttribute Audiencia audiencia) { 
		
		try {
			
			audienciaService.editarAudiencia(audiencia);
			
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
	
	@RequestMapping(value="excluir-audiencia-por-id", method=RequestMethod.GET) 
	public ModelAndView excluirAudiencia(ModelMap model, 		   
			                           @RequestParam("id") Long id,
			                           @RequestParam("clienteId") Long clienteId,
			                           @RequestParam("processoId") Long processoId,
			                           @RequestParam("page") Long page,
	                                   @RequestParam("size") Long size) { 
		
		try {
			
			audienciaService.excluirAudiencia(id);
			
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
