package sistema.advogados.associados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario obterUsuario(Long id) {
		
		return usuarioRepository.findById(id).get();
	}
}
