package med.voll.api.medico;

import med.voll.api.Direccion.DatosDireccion;

public record DatosRegistroMedicio(String nombre, String email, String documento, Especialidad especialidad, DatosDireccion direccion) {
    
}

