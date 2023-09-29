package med.voll.api.domain.usuarios;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String clave;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
        return clave;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        return login;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }


}
