package med.voll.api.domain.medico;

import med.voll.api.Direccion.DatosDireccion;

public record DatosRespuestaMedico(long id, String nombre,String email, String telefono, String documento,
                                    DatosDireccion direccion) {
    
}
