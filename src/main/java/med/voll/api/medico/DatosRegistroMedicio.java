package med.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Direccion.DatosDireccion;

public record DatosRegistroMedicio(
    @NotBlank
    String nombre,

    @NotBlank
    String email, 

    @NotBlank
    String telefono,
    
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String documento, 

    @NotNull
    Especialidad especialidad, 

    @NotNull
    DatosDireccion direccion

    
    )
{}

