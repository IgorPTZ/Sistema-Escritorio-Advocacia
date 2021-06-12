package sistema.advogados.associados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM cliente ORDER BY nome", nativeQuery = true)
	Page<Cliente> obterClientesPaginados(Pageable pageable);
}
