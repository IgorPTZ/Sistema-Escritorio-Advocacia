package sistema.advogados.associados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Pericia;
import sistema.advogados.associados.repository.PericiaRepository;

@Service
public class PericiaService {
	
	@Autowired
	private PericiaRepository periciaRepository;
	
	public List<Pericia> obterPericiasPorProcessoId(Long id) {
		
		return periciaRepository.obterPericiasPorProcessoId(id);
	}
	
	public Pericia obterPericiaPorId(Long id) {
		
		return periciaRepository.findById(id).get();
	}
	
	public Pericia inserirPericia(Pericia pericia) {
		
		return periciaRepository.save(pericia);
	}
	
	public Pericia editarPericia(Pericia pericia) {
		
		return periciaRepository.save(pericia);
	}
	
	public void excluirPericia(Long id) {
		
		periciaRepository.deleteById(id);
	}
}
