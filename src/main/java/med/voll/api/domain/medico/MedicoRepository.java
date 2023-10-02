package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
// Ya se trae todos los metodos de save, update, delete, etc.
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

    Page<Medico> findAllByActivoTrue(Pageable paginacion);
    // Nota importante, los querys realizados en este apartado se realizan en base al modelo y no a la base de datos!!!
    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(Long idMedico);

    @Query("""
            select m from Medico m
            where m.activo=true
            and
            m.especialidad=:especialidad 
            and
            m.id not in(  
                select c.medico.id from Consulta c
                where
                c.fecha=:fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
}
