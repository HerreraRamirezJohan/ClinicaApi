package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import med.voll.api.domain.usuarios.Usuario;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);// Mala practica tener el secret aqui en codigo
            String token = JWT.create()
                .withIssuer("voll med")
                .withSubject(usuario.getLogin()) //obtenemos los datos del usuario, el nombre
                .withClaim("id", usuario.getId()) // obtenemos el id
                .withExpiresAt(generarFechaExpiracion())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    public String getSubject(String token){
        if(token == null){
            throw new RuntimeException("No se recivio un token");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                // specify an specific claim validations
                .withIssuer("voll med")
                // reusable verifier instance
                .build()
                .verify(token);
            
            return verifier.getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Verified invalid: " + exception.getMessage());
        }
    }

    private Instant generarFechaExpiracion(){
        //Especificamos que el token expirara en 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));// Hora de sur america
    }
}
