package sistema.advogados.associados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Processo;
import sistema.advogados.associados.repository.ProcessoRepository;

@Service
public class ProcessoService {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	public Processo obterProcessoPorId(Long id) {
		
		return processoRepository.findById(id).get();
	}
	
	public Processo inserirProcesso(Processo processo) {
		
		return processoRepository.save(processo);
	}
}
