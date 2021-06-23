package sistema.advogados.associados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Processo;

@Repository
@Transactional
public interface ProcessoRepository extends JpaRepository<Processo, Long>{

}
