package recipes.business.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.business.security.repository.UserRepository;
import recipes.business.entity.AppUser;

import java.util.Optional;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<AppUser> currentUser = userRepository.findByEmailIgnoreCase(username);
        if (currentUser.isPresent()) {
            return new UserDetailsImpl(currentUser.get());
        }
        else throw new UsernameNotFoundException(String.format("User with username %s not found", username));
    }
}
