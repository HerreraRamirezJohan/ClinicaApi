package med.voll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    
    //Recive un DTO
    public Medico(DatosRegistroMedicio request) {
        this.activo = true;
        this.nombre = request.nombre();
        this.email = request.email();
        this.telefono = request.telefono();
        this.documento = request.documento();
        this.especialidad = request.especialidad();
        this.direccion = new Direccion(request.direccion());
    }

    public void actualizarMedico(DatosActualizarMedico datosActualizarMedico){
        if(datosActualizarMedico.nombre() != null)
            this.nombre = datosActualizarMedico.nombre();
        if(datosActualizarMedico.documento() != null)
            this.documento = datosActualizarMedico.documento();
        if(datosActualizarMedico.direccion() != null)
            this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());

    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
