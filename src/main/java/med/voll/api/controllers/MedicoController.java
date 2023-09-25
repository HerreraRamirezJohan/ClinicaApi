package med.voll.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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
    public List<Medico> listadoMedicos(){
        return medicoRepository.findAll();
    }
}
