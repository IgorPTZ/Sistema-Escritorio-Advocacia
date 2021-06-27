package sistema.advogados.associados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AudienciaController {
	
	@RequestMapping(value="/carregar-nova-audiencia", method=RequestMethod.GET) 
	public ModelAndView carregarNovaAudiencia(ModelAndView model,
											@RequestParam("processoId") Long processoId,
										    @RequestParam("page") Long page,
							                @RequestParam("size") Long size) { 
		
		try {
			
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
}
