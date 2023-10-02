package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Ya se trae todos los metodos de save, update, delete, etc.
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

    //Tienes que utiliza exists con la sintaxis especifica para que JPA haga bien el trabajo
    //Realiza la consulta de manera automatica por spring
    Boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    // Verificamos si el medico ya tiene una consulta en esa fecha
    Boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    // Page<Consulta> findAllByActivoTrue(Pageable paginacion);
    
}
