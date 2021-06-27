package sistema.advogados.associados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {
	
	@RequestMapping(value="/enviar-email-de-notificacao", method=RequestMethod.GET) 
	public ModelAndView editarPericiaPorId(ModelAndView model,  
			                               @RequestParam("clienteId") Long clienteId,
			                               @RequestParam("processoId") Long processoId,
			                               @RequestParam("periciaId") Long periciaId,
		                                   @RequestParam("tipoDeNotificacao") Long tipoDeNotificacao) { 
		
		try {
			

			
			model.setViewName("pericia/editar-pericia");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
