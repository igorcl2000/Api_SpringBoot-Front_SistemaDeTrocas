package canto.minas.api.domain.loja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

//import java.time.LocalDateTime;

public interface LojaRepository extends JpaRepository<Loja, Long> {
    Page<Loja> findAllByAtivoTrue(Pageable paginacao);
}
