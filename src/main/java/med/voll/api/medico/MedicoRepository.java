package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// Ya se trae todos los metodos de save, update, delete, etc.
public interface MedicoRepository extends JpaRepository<Medico, Long>{

    Page<Medico> findAllByActivoTrue(Pageable paginacion);
    
}
