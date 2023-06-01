package co.edu.javeriana.ms.LoadBalancer.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUser implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> roles;

    public AppUser(Cliente c) {
        this.username = c.getUsername();
        this.password = c.getPassword();
        this.roles = List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
    }

    public AppUser(Proveedor p) {
        this.username = p.getUsername();
        this.password = p.getPassword();
        this.roles = List.of(new SimpleGrantedAuthority("ROLE_PROVEEDOR"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
