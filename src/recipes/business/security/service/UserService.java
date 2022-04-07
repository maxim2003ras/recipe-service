package recipes.business.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.business.entity.AppUser;
import recipes.business.security.exception.UserAlreadyPersisted;
import recipes.business.security.repository.UserRepository;

import java.util.Optional;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(final AppUser user) {
        userRepository.findByEmailIgnoreCase(user.getEmail()).ifPresentOrElse(
                (currentUser) -> {
                    throw new UserAlreadyPersisted();
                },
                () -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userRepository.save(user);
                }
        );
    }

    public AppUser getUserByEmail(final String username) {
        Optional<AppUser> currentUser = userRepository.findByEmailIgnoreCase(username);
        if (currentUser.isPresent()) {
            return currentUser.get();
        }
        else throw new UsernameNotFoundException("User with username %s not found");
    }
}
