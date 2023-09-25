package med.voll.api.medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email) {
    //Constructor de mapeo
    public DatosListadoMedico(Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
