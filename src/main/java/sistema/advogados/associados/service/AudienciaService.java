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
	
	public List<Audiencia> obterAudienciasPorProcessoId(Long id) {
		
		return audienciaRepository.obterAudienciasPorProcessoId(id);
	}
}
