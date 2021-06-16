package sistema.advogados.associados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Role;
import sistema.advogados.associados.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> obterPerfis() {
		
		return roleRepository.findAll();
	}
}
