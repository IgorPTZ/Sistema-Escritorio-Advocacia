package sistema.advogados.associados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Processo;
import sistema.advogados.associados.service.ClienteService;
import sistema.advogados.associados.service.ProcessoService;
import sistema.advogados.associados.service.ReuService;
import sistema.advogados.associados.service.SituacaoDoProcessoService;

@Controller
public class ProcessoController {
	
	private Long clienteId;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ReuService reuService;
	
	@Autowired
	private SituacaoDoProcessoService situacaoDoProcessoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/carregar-novo-processo", method=RequestMethod.GET) 
	public ModelAndView carregarNovoCliente(ModelAndView model,
											@RequestParam("clienteId") Long clienteId,
										    @RequestParam("page") Long page,
							                @RequestParam("size") Long size) { 
		
		try {
			this.clienteId = clienteId;
			
			model.addObject("clienteId", this.clienteId);
			
			model.addObject("page", page);
			
			model.addObject("size", size);
			
			model.addObject("processo", new Processo());
			
			model.addObject("listaDeReus", reuService.obterReus());
			
			model.addObject("listaDeSituacoesDoProcesso", situacaoDoProcessoService.obterSituacoes());
			
			model.addObject("listaDeClientes", clienteService.obterClientes());
			
			model.setViewName("processo/inserir-processo");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir-processo", method=RequestMethod.POST) 
	public ModelAndView inserirProcesso(ModelMap model, @ModelAttribute Processo processo) {
		
		try {
			
			if(processo.getReus().isEmpty() || processo.getClientes().isEmpty() || processo.getSituacao().getId() == null) {
				
				model.addAttribute("clienteId", this.clienteId);
				
				model.addAttribute("mensagem", "Outra parte, clientes e situação são campos obrigatórios");
				
				model.addAttribute("page", 0L);
				
				model.addAttribute("size", 20L);
				
				model.addAttribute("processo", processo);
				
				model.addAttribute("listaDeReus", reuService.obterReus());
				
				model.addAttribute("listaDeSituacoesDoProcesso", situacaoDoProcessoService.obterSituacoes());
				
				model.addAttribute("listaDeClientes", clienteService.obterClientes());
				
				return new ModelAndView("processo/inserir-processo", model);
			}

			Processo processoCriado = processoService.inserirProcesso(processo);
			
			model.addAttribute("id", processoCriado.getId());
			
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
	
	@RequestMapping(value="/detalhar-processo-por-id", method=RequestMethod.GET) 
	public ModelAndView obterProcessoPorId(ModelAndView model,
											@RequestParam("id") Long id,
											@RequestParam("clienteId") Long clienteId,
										    @RequestParam("page") Long page,
							                @RequestParam("size") Long size) { 
		
		try {
			
			Processo processo = processoService.obterProcessoPorId(id); 
			
			String nomesDosReus = "";
			
			for(int i = 0; i < processo.getReus().size() ; i++) {
				
				nomesDosReus += processo.getReus().get(i).getNome();
				
				if(i != (processo.getReus().size() - 1)) {
					
					nomesDosReus += ",";
				}
			}
			
			model.addObject("page", 0L);
			
			model.addObject("size", 20L);
			
			model.addObject("clienteId", clienteId);
			
			model.addObject("nomesDosReus", nomesDosReus);
			
			model.addObject("processo", processo);
			
			model.setViewName("processo/detalhar-processo");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
