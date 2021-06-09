package sistema.advogados.associados.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Usuario;
import sistema.advogados.associados.repository.UsuarioRepository;

@Service
@Transactional
public class AutenticacaoDoUsuario implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.obterUsuarioPeloLogin(username);
		
		if(usuario == null) {
			
			throw new UsernameNotFoundException("O usuario nao foi encontrado");
		}
		
		return new User(usuario.getLogin(), 
						usuario.getPassword(), 
						usuario.isEnabled(), 
						true, 
						true, 
						true, 
						usuario.getAuthorities());
	}
}
