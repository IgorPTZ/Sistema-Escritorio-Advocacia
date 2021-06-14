package sistema.advogados.associados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Cliente;
import sistema.advogados.associados.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Page<Cliente> obterClientesPaginados(Pageable pageable) {

		return clienteRepository.obterClientesPaginados(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}
	
	public Page<Cliente> obterClientesPaginados(Long numeroDaPagina, Long quantidadeDeElementos) {

		return clienteRepository.obterClientesPaginados(PageRequest.of(numeroDaPagina.intValue(), quantidadeDeElementos.intValue()));
	}
	
	public Cliente obterClientePorId(Long id) {
		
		return clienteRepository.findById(id).get();
	}
	
	public Cliente inserirCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
}
