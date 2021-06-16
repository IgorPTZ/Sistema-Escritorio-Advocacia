package sistema.advogados.associados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Usuario> obterUsuariosPaginados(Pageable pageable) {

		return usuarioRepository.obterUsuariosPaginados(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}
	
	public Page<Usuario> obterUsuariosPaginados(Long numeroDaPagina, Long quantidadeDeElementos) {

		return usuarioRepository.obterUsuariosPaginados(PageRequest.of(numeroDaPagina.intValue(), quantidadeDeElementos.intValue()));
	}
	
	public Usuario obterUsuarioPorId(Long id) {
		
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario obterUsuarioPorLogin(String login) {
		
		return usuarioRepository.obterUsuarioPeloLogin(login);
	}
	
	public Usuario inserirUsuario(Usuario parametros) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		parametros.setSenha(encoder.encode(parametros.getSenha()));
		
		removerConstraint();
		
		return usuarioRepository.save(parametros);
	}
	
	public Usuario editarUsuario(Usuario parametros) {
		
		Usuario usuario = usuarioRepository.getById(parametros.getId());
		
		usuario.setNome(parametros.getNome());
		
		usuario.setLogin(parametros.getLogin());
		
		usuario.setRoles(parametros.getRoles());
		
		removerConstraint();
		
		return usuarioRepository.save(usuario);
	}
	
	public void excluirUsuario(Long id) {
		
		Usuario usuario = usuarioRepository.findById(id).get();
		
		usuarioRepository.delete(usuario);
	}
	
	public Boolean alterarSenha(Usuario parametros) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Usuario usuario = usuarioRepository.findById(parametros.getId()).get();
		
		if(encoder.matches(parametros.getSenha(), usuario.getSenha())) {
			
			usuario.setSenha(encoder.encode(parametros.getNovaSenha()));
			
			usuarioRepository.save(usuario);
			
			return true;
		}
		else {
			
			return false;
		}	
	}
	
	private void removerConstraint() {
		
		/* 1) DESCROBRE O NOME DA CONSTRAINT QUE SERÁ REMOVIDA */
		String nomeDaConstraint = usuarioRepository.obterNomeDaConstraint();
		
		if(nomeDaConstraint != null) {
			
			/* 2) EXCLUI A CONSTRAINT */
			jdbcTemplate.execute("ALTER TABLE usuario_role DROP CONSTRAINT " + nomeDaConstraint);
		}
	}
}
