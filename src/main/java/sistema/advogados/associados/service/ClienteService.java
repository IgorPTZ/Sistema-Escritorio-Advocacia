package sistema.advogados.associados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Cliente;
import sistema.advogados.associados.model.Processo;
import sistema.advogados.associados.repository.ClienteRepository;
import sistema.advogados.associados.util.PesquisaDeClientes;
import sistema.advogados.associados.util.Utilitarios;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> obterClientes() {
		
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> obterClientesPaginados(Pageable pageable) {

		return clienteRepository.obterClientesPaginados(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}
	
	public Page<Cliente> obterClientesPaginados(Pageable pageable, PesquisaDeClientes parametros) {
		
		Page<Cliente> clientes = null;
		
		boolean pesquisarPorEmail = parametros.getTextoDePesquisa() != null && !parametros.getTextoDePesquisa().isEmpty() && parametros.getTextoDePesquisa().contains("@");
		
		boolean pesquisarPorNumeroDoProcesso = parametros.getTextoDePesquisa() != null && !parametros.getTextoDePesquisa().isEmpty() && Utilitarios.isNumerico(parametros.getTextoDePesquisa());
		
		boolean pesquisarPorNome = parametros.getTextoDePesquisa() != null && !parametros.getTextoDePesquisa().isEmpty();
		
		if(pesquisarPorEmail) {
			
			clientes =clienteRepository.obterClientesPaginadosPorEmail(pageable, parametros.getTextoDePesquisa());
		}
		else if(pesquisarPorNumeroDoProcesso) {
			
			clientes = clienteRepository.obterClientesPaginadosPorNumeroDeProcesso(pageable, parametros.getTextoDePesquisa());
		}
		else if(pesquisarPorNome) {
			
			clientes = clienteRepository.obterClientesPaginadosPorNome(pageable, parametros.getTextoDePesquisa());
		}
		else {
			
			clientes = clienteRepository.obterClientesPaginados(pageable);
		}
		
		return clientes;
	}
	
	public Page<Cliente> obterClientesPaginados(Long numeroDaPagina, Long quantidadeDeElementos) {

		return clienteRepository.obterClientesPaginados(PageRequest.of(numeroDaPagina.intValue(), quantidadeDeElementos.intValue()));
	}
	
	public Cliente obterClientePorId(Long id) {
		
		Cliente cliente = clienteRepository.findById(id).get();
		
		String nomeDosReus = "";
		
		for(Processo processo : cliente.getProcessos()) {
			
			for(int i = 0; i < processo.getReus().size() ; i++) {
				
				nomeDosReus += processo.getReus().get(i).getNome();
				
				if(i != (processo.getReus().size() - 1)) {
					
					nomeDosReus += ",";
				}
			}
			
			processo.setNomeDosReus(nomeDosReus);
			
			nomeDosReus = "";
		}
		
		return cliente;
	}
	
	public Cliente inserirCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
}
