package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Direccion.DatosDireccion;

public record DatosActualizarMedico(
    @NotNull
    Long id,

    @NotBlank
    String nombre,
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String documento, 

    DatosDireccion direccion
) {
    
}
