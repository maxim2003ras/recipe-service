package recipes.business.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.business.entity.AppUser;

import java.util.Optional;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByEmailIgnoreCase(final String email);
}
