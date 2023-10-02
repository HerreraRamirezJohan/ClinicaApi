package med.voll.api.infra.errores;

public class ValidacionDeIntegridadConsulta extends RuntimeException{
    public ValidacionDeIntegridadConsulta(String error){
        super(error);
    }
}
