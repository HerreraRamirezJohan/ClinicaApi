package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{// Verifica que no haya realizado una conusulta el dia de hoy

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos)  {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario= datos.fecha().withHour(18);

        // este metodo personalizado debe meter un boolean
        var pacienteConConsulta=repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("el paciente ya tiene una consulta para ese dia");
        }

    }
}