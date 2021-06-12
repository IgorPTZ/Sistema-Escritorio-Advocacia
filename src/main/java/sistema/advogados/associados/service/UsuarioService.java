package sistema.advogados.associados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Usuario> obterUsuariosPaginados(Pageable pageable) {

		return usuarioRepository.obterUsuariosPaginados(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}
	
	public Usuario obterUsuario(Long id) {
		
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario obterUsuarioPorLogin(String login) {
		
		return usuarioRepository.obterUsuarioPeloLogin(login);
	}
}
