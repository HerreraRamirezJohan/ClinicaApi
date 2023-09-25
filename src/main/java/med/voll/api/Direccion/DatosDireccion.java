package med.voll.api.Direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion (
    @NotBlank
    String calle, 

    @NotBlank
    String distrito, 

    @NotBlank
    String ciudad, 

    @NotBlank
    int numero, 

    @NotBlank
    String complemento
    
    ){
    
}

