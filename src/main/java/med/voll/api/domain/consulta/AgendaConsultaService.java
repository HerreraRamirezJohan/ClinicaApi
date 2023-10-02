package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridadConsulta;

@Service
public class AgendaConsultaService {
    
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta data){

        // System.out.println("El paciente fue encontrado? " + pacienteRepository.findById(data.idPaciente()).isPresent());
        if(!pacienteRepository.findById(data.idPaciente()).isPresent()){ //Primer metodo de realizar una busqueda booleana
            throw new ValidacionDeIntegridadConsulta("este id de paciente no fue encontrado");
        }

        if(data.idMedico() != null && !medicoRepository.existsById(data.idMedico())){// Segundo metodo para realizar una busqueda booleana
            throw new ValidacionDeIntegridadConsulta("este id de medico no fue encontrado");
        }

        validadores.forEach(v-> v.validar(data));

        Paciente paciente = pacienteRepository.findById(data.idPaciente()).get();

        // Medico medico = medicoRepository.findById(data.idPaciente()).get();//obtener un medico
        Medico medico = seleccionarMedico(data); // Obtener un medico disponible segun su especialidad

        if (medico == null){
            throw new ValidacionDeIntegridadConsulta("No existe un medico disponible para este horario y especialidad");
        }
        var consulta = new Consulta(medico, paciente, data.fecha());// creamos un constructor personalizado sin id

        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    //Metodo para en caso de estar ocupado un medico encuentre uno disponible.
    private Medico seleccionarMedico(DatosAgendarConsulta data){
        if(data.idMedico() != null){
            return medicoRepository.getReferenceById(data.idMedico()); // Obtener el medico que se solicito en caso de que no exista se elegira un medico con la misma espacialidad
        }
        if(data.especialidad() == null){
            throw new ValidacionDeIntegridadConsulta("Debe seleccionarse una espacialidad para ese medico");
        }
     
        // return null;
       return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(data.especialidad(), data.fecha());
    }
}
