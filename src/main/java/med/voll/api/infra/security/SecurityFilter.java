package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    // Hacer validacion de token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //Obtener el token del header
        var authHeader = request.getHeader("Authorization");//replace("Bearer ", "");// obtener authHeader de la request
        // if(authHeader == "" || authHeader == null)// Esta validacion no permite el acceso a login o register
        //     throw new RuntimeException("authHeader vacio");
        if(authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            if(subject != null){
                var user = usuarioRepository.findByLogin(subject);
                //Forzamos el inicio de sesion
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);// unica forma de hacer el filtro y obtener los datos 
    }
    
}
