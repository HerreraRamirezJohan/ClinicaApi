package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// Ya se trae todos los metodos de save, update, delete, etc.
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    Page<Paciente> findAllByActivoTrue(Pageable paginacion);
    
}
