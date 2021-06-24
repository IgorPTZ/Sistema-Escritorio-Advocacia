package sistema.advogados.associados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Pericia;

@Repository
@Transactional
public interface PericiaRepository extends JpaRepository<Pericia, Long>{
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM pericia WHERE processo_id = :processoId ORDER BY data_da_pericia", nativeQuery = true)
	List<Pericia> obterPericiasPorProcessoId(@Param("processoId") Long processoId);
}
