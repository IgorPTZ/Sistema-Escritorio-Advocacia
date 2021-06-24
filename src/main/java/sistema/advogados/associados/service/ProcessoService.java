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
		
		Processo processo = processoRepository.findById(id).get();
		
		String nomeDosReus = "";
		
		for(int i = 0; i < processo.getReus().size() ; i++) {
			
			nomeDosReus += processo.getReus().get(i).getNome();
			
			if(i != (processo.getReus().size() - 1)) {
				
				nomeDosReus += ",";
			}
		}
		
		processo.setNomeDosReus(nomeDosReus);
		
		return processo;
	}
	
	public void excluirProcessoPorId(Long id) {
		
		processoRepository.deleteById(id);
	}
	
	public Processo inserirProcesso(Processo processo) {
		
		return processoRepository.save(processo);
	}
	
	public Processo editarProcesso(Processo processo) {
		
		return processoRepository.save(processo);
	}
}
