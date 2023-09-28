package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Direccion.DatosDireccion;

public record DatosRegistroPaciente(
    @NotBlank
    String nombre,

    @NotBlank
    String email, 

    @NotBlank
    String telefono,
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String documentoIdentidad, 

    @NotNull
    DatosDireccion direccion

    
    )
{}

