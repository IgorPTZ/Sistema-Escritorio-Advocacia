package sistema.advogados.associados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Reu;
import sistema.advogados.associados.repository.ReuRepository;

@Service
public class ReuService {

	@Autowired
	private ReuRepository reuRepository;
	
	public List<Reu> obterReus() {
		
		return reuRepository.findAll();
	}
}
