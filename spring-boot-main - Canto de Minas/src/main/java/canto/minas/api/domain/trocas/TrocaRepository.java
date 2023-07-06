package canto.minas.api.domain.trocas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

//import java.time.LocalDateTime;

public interface TrocaRepository extends JpaRepository<Troca, Long> {
    Page<Troca> findAllByAtivoTrue(Pageable paginacao);
}
