package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosActualizarMedico;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistroMedicio;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    @Autowired //No es util para hacer pruebas
    private MedicoRepository medicoRepository;

    @PostMapping //Le decimos la ruta y el metodo a realizar 
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedicio request){ // con request body le especificamos que ese es el body que manda la pagina
        System.out.println(request);
        medicoRepository.save(new Medico(request));
    }

    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2, sort = "id") Pageable paginacion){//usar pageable de data.domain
        // return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList(); // se trae todo en lista "lo normal sin paginacion"
        // return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new); //se trae todo con paginacion
        return medicoRepository.findAllByActivoTrue(paginacion).map(DatosListadoMedico::new); // Tiene glomentatura especial para realiar un where por automatico
    }

    //Lo siguiente es jpa puro
    @PutMapping
    @Transactional //Es necesario para realizar el commit en la base de datos y realice la actualizacion
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());// obtenemos la instancia del medico de nuestra base de datos
        medico.actualizarMedico(datosActualizarMedico);
    }

    //delete logico
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable Long id){ //pathvariable es para asignar variables en la url
        Medico medico = medicoRepository.getReferenceById(id);// obtenemos la instancia del medico de nuestra base de datos
        medico.desactivarMedico();
    }
}
