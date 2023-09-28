package med.voll.api.domain.paciente;

import med.voll.api.Direccion.DatosDireccion;

public record DatosDetalladoPaciente(String nombre, String email, String telefono, String documentoIdentidad, DatosDireccion direccion) {

} 
