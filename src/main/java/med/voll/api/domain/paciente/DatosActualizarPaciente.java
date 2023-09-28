package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Direccion.DatosDireccion;

public record DatosActualizarPaciente(
    @NotNull
    Long id,

    @NotBlank
    String nombre,
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String documentoIdentidad, 

    DatosDireccion direccion
) {
    
}
