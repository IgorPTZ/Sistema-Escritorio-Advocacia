package sistema.advogados.associados.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sistema.advogados.associados.model.Audiencia;
import sistema.advogados.associados.model.Cliente;
import sistema.advogados.associados.model.NotificacaoDeAudienciaPorEmail;
import sistema.advogados.associados.model.NotificacaoDePericiaPorEmail;
import sistema.advogados.associados.model.Pericia;
import sistema.advogados.associados.model.Processo;
import sistema.advogados.associados.service.AudienciaService;
import sistema.advogados.associados.service.ClienteService;
import sistema.advogados.associados.service.EmailDeNotificacaoService;
import sistema.advogados.associados.service.PericiaService;
import sistema.advogados.associados.service.ProcessoService;

@Controller
public class EmailController {
	
	@Autowired
	private EmailDeNotificacaoService emailDeNotificacaoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private PericiaService periciaService;
	
	@Autowired
	private AudienciaService audienciaService;
	
	
	@RequestMapping(value="/enviar-notificacao-de-audiencia", method=RequestMethod.POST) 
	public ModelAndView enviarNotificacaoDeAudiencia(ModelMap model, @ModelAttribute NotificacaoDeAudienciaPorEmail notificacao) { 
		
		try {
			Cliente cliente = clienteService.obterClientePorId(notificacao.getClienteId());
			
			Processo processo = processoService.obterProcessoPorId(notificacao.getProcessoId());
			
			Audiencia audiencia = audienciaService.obterAudienciaPorId(notificacao.getAudienciaId());
			
			String dataDaAudiencia = audiencia.getDataDaAudiencia().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
			
			String conteudo = "Prezado(a)," + cliente.getNome() + ",\nsegue os dados para a sua audiência:\n\n" + 
					          "Processo:" + processo.getNumero() + "\n" + 
			                  "Data:" + dataDaAudiencia + "\n" +
					          "Horário:" + audiencia.getHorario() + "\n" +
					          "Local:" + audiencia.getEndereco() + "\n";
			                  
			if(!notificacao.getObservacao().isEmpty()) {
				conteudo += "Observação:" + notificacao.getObservacao() + "\n";
			}
			
			if(notificacao.getFlagTipoDeNotificacao().compareTo(2L) == 0) {
				conteudo += "Favor comparecer acompanhado de 3 testemunhas \n";
			}
			                  
			conteudo += "Qualquer dúvida entrar em contato no número:3435345345334" + "\n\n" +
					    "Atenciosamente," + "\nCosta Pinto & Martinelli - Advogados Associados.";
			
			emailDeNotificacaoService.enviarEmailDeRecuperacaoDeAcesso("Aviso de Audiência", cliente.getEmail(), conteudo);
				
			model.addAttribute("mensagem", "Email enviado com sucesso!");
			
			model.addAttribute("id", processo.getId());
			
			model.addAttribute("clienteId", cliente.getId());
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("mensagem", "Falha no envio do email de notificação da audiência. Entre em contato com o administrador do sistema");
			
			model.addAttribute("id", notificacao.getProcessoId());
			
			model.addAttribute("clienteId", notificacao.getClienteId());
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
	}

	@RequestMapping(value="/enviar-notificacao-de-pericia", method=RequestMethod.POST) 
	public ModelAndView enviarNotificacaoDePericia(ModelMap model, @ModelAttribute NotificacaoDePericiaPorEmail notificacao) { 
		
		try {
			
			Cliente cliente = clienteService.obterClientePorId(notificacao.getClienteId());
			
			Processo processo = processoService.obterProcessoPorId(notificacao.getProcessoId());
			
			Pericia pericia = periciaService.obterPericiaPorId(notificacao.getPericiaId());
			
			String dataDaPericia = pericia.getDataDaPericia().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
			
			String conteudo = "Prezado(a)," + cliente.getNome() + ",\nsegue os dados para a sua perícia:\n\n" + 
					          "Processo:" + processo.getNumero() + "\n" + 
			                  "Data:" + dataDaPericia + "\n" +
					          "Horário:" + pericia.getHorario() + "\n" +
			                  "Períto:" + pericia.getNomePerito() + "\n" +
					          "Endereço:" + pericia.getEndereco() + "\n";
			                  
			if(!notificacao.getObservacao().isEmpty()) {
				conteudo += "Observação:" + notificacao.getObservacao() + "\n";
			}
			                  
			conteudo += "Qualquer dúvida entrar em contato no número:" + pericia.getTelefone() + "\n\n" +
					    "Atenciosamente," + "\nCosta Pinto & Martinelli - Advogados Associados.";
			
			emailDeNotificacaoService.enviarEmailDeRecuperacaoDeAcesso("Aviso de Perícia", cliente.getEmail(), conteudo);
				
			model.addAttribute("mensagem", "Email enviado com sucesso!");
			
			model.addAttribute("id", processo.getId());
			
			model.addAttribute("clienteId", cliente.getId());
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("mensagem", "Falha no envio do email de notificação da perícia. Entre em contato com o administrador do sistema");
			
			model.addAttribute("id", notificacao.getProcessoId());
			
			model.addAttribute("clienteId", notificacao.getClienteId());
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
	}
}
