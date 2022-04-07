package recipes.business.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import recipes.business.entity.AppUser;

import java.util.Collection;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
public class UserDetailsImpl implements UserDetails {
    private String email;
    private String password;

    public UserDetailsImpl(final AppUser user) {
        email = user.getEmail().toLowerCase();
        password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
