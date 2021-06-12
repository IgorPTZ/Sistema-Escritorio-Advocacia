package sistema.advogados.associados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Usuario;


@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select * from usuario where login = :login", nativeQuery = true)
	Usuario obterUsuarioPeloLogin(@Param("login") String login);
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM usuario ORDER BY nome", nativeQuery = true)
	Page<Usuario> obterUsuariosPaginados(Pageable pageable);
}
