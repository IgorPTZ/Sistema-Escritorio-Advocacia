package sistema.advogados.associados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sistema.advogados.associados.model.Reu;

@Repository
@Transactional
public interface ReuRepository extends JpaRepository<Reu, Long>{

}
