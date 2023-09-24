package med.voll.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.medico.DatosRegistroMedicio;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    @PostMapping //Le decimos la ruta y el metodo a realizar 
    public void registrarMedico(@RequestBody DatosRegistroMedicio request){ // con request body le especificamos que ese es el body que manda la pagina
        System.out.println(request);
    }
}
