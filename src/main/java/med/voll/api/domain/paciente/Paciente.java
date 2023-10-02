package med.voll.api.domain.paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.Direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    @Column(name="documentoIdentidad")
    private String documentoIdentidad;
    private boolean activo;
    @Embedded
    private Direccion direccion;
    
    //Recive un DTO
    public Paciente(DatosRegistroPaciente request) {
        this.activo = true;
        this.nombre = request.nombre();
        this.email = request.email();
        this.telefono = request.telefono();
        this.documentoIdentidad = request.documentoIdentidad();
        this.direccion = new Direccion(request.direccion());
    }

    public void actualizarPaciente(DatosActualizarPaciente datosActualizarPaciente){
        if(datosActualizarPaciente.nombre() != null)
            this.nombre = datosActualizarPaciente.nombre();
        if(datosActualizarPaciente.documentoIdentidad() != null)
            this.documentoIdentidad = datosActualizarPaciente.documentoIdentidad();
        if(datosActualizarPaciente.direccion() != null)
            this.direccion = direccion.actualizarDireccion(datosActualizarPaciente.direccion());

    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}
