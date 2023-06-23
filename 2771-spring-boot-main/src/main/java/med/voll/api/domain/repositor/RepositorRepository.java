package med.voll.api.domain.repositor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

//import java.time.LocalDateTime;

public interface RepositorRepository extends JpaRepository<Repositor, Long> {
    Page<Repositor> findAllByAtivoTrue(Pageable paginacao);
}
