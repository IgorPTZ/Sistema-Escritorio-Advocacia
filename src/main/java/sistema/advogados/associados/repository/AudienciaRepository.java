package sistema.advogados.associados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Audiencia;

@Repository
@Transactional
public interface AudienciaRepository extends JpaRepository<Audiencia, Long>{
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM audiencia WHERE processo_id = :processoId ORDER BY data_da_audiencia", nativeQuery = true)
	List<Audiencia> obterAudienciasPorProcessoId(@Param("processoId") Long processoId);
}
