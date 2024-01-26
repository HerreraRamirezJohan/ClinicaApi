package med.voll.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.Direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosActualizarPaciente;
import med.voll.api.domain.paciente.DatosDetalladoPaciente;
import med.voll.api.domain.paciente.DatosListadoPaciente;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired //No es util para hacer pruebas
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalladoPaciente> registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriBuilder) {
        Paciente paciente = repository.save(new Paciente(datos));

        DatosDetalladoPaciente datosDetalladoPaciente = new DatosDetalladoPaciente(
            paciente.getNombre(), paciente.getEmail(),
            paciente.getTelefono(), paciente.getDocumentoIdentidad(),
            new DatosDireccion(paciente.getDireccion().getCalle(),
                                paciente.getDireccion().getDistrito(), 
                                paciente.getDireccion().getCiudad(), 
                                paciente.getDireccion().getNumero(), 
                                paciente.getDireccion().getComplemento()));

        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(datosDetalladoPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listar(@PageableDefault(size = 2, sort = {"nombre"}) Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListadoPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDetalladoPaciente> actualizar(@RequestBody @Valid DatosActualizarPaciente datos) {
        var paciente = repository.getReferenceById(datos.id());
        paciente.actualizarPaciente(datos);

        DatosDetalladoPaciente datosDetalladoPaciente = new DatosDetalladoPaciente(
            paciente.getNombre(), paciente.getEmail(),
            paciente.getTelefono(), paciente.getDocumentoIdentidad(),
            new DatosDireccion(paciente.getDireccion().getCalle(),
                                paciente.getDireccion().getDistrito(), 
                                paciente.getDireccion().getCiudad(), 
                                paciente.getDireccion().getNumero(), 
                                paciente.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosDetalladoPaciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalladoPaciente> eliminar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.desactivarPaciente();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalladoPaciente> detallar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        DatosDetalladoPaciente datosDetalladoPaciente = new DatosDetalladoPaciente(
            paciente.getNombre(), paciente.getEmail(),
            paciente.getTelefono(), paciente.getDocumentoIdentidad(),
            new DatosDireccion(paciente.getDireccion().getCalle(),
                                paciente.getDireccion().getDistrito(), 
                                paciente.getDireccion().getCiudad(), 
                                paciente.getDireccion().getNumero(), 
                                paciente.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosDetalladoPaciente);
    }
}
