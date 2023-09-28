package med.voll.api.domain.paciente;

public record DatosListadoPaciente(Long id, String nombre, String documentoIdentidad, String email) {
    //Constructor de mapeo
    public DatosListadoPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNombre(), paciente.getDocumentoIdentidad(), paciente.getEmail());
    }
}
