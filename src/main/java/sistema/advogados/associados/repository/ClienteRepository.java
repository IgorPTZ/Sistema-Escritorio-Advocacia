package sistema.advogados.associados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM cliente ORDER BY nome", nativeQuery = true)
	Page<Cliente> obterClientesPaginados(Pageable pageable);
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM cliente WHERE nome ILIKE %:nome% ORDER BY nome", nativeQuery = true)
	Page<Cliente> obterClientesPaginadosPorNome(Pageable pageable , @Param("nome") String nome);
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM cliente WHERE email ILIKE %:email% ORDER BY nome", nativeQuery = true)
	Page<Cliente> obterClientesPaginadosPorEmail(Pageable pageable, @Param("email") String email);
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM cliente cl "
			+       " JOIN processo_cliente pc ON  cl.cliente_id = pc.cliente_id"
			+       " JOIN processo pr ON pr.id = processo_id"
			+       " WHERE pr.numero ILIKE %:numeroDoProcesso% ORDER BY  cl.nome", nativeQuery = true)
	Page<Cliente> obterClientesPaginadosPorNumeroDeProcesso(Pageable pageable, @Param("numeroDoProcesso") String numeroDoProcesso);
}
