package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

public record DatosAgendarConsulta (
    @NotNull Long idPaciente, 
    Long idMedico, 
    @NotNull @Future LocalDateTime fecha,
    @Enumerated(EnumType.STRING)
    Especialidad especialidad){// si coloco not null puedo generar un conflicto con el throw definido en nuestra capa de servicio

}
