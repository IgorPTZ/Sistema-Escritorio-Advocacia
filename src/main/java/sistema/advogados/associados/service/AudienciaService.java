package sistema.advogados.associados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Audiencia;
import sistema.advogados.associados.repository.AudienciaRepository;

@Service
public class AudienciaService {
	
	@Autowired
	private AudienciaRepository audienciaRepository;
	
	public Audiencia obterAudienciaPorId(Long id) {
		
		return audienciaRepository.findById(id).get();
	}
	
	public List<Audiencia> obterAudienciasPorProcessoId(Long id) {
		
		return audienciaRepository.obterAudienciasPorProcessoId(id);
	}
	
	public Audiencia inserirAudiencia(Audiencia audiencia) {
		
		return audienciaRepository.save(audiencia);
	}
	
	public Audiencia editarAudiencia(Audiencia audiencia) {
		
		return audienciaRepository.save(audiencia);
	}
}
