package sistema.advogados.associados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.SituacaoDoProcesso;
import sistema.advogados.associados.repository.SituacaoDoProcessoRepository;

@Service
public class SituacaoDoProcessoService {
	
	@Autowired
	private SituacaoDoProcessoRepository situacaoDoProcessoRepository;
	
	public List<SituacaoDoProcesso> obterSituacoes() {
		
		return situacaoDoProcessoRepository.findAll();
	}
}
