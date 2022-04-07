package recipes.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.business.entity.Recipe;

import java.util.Optional;
import java.util.Set;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByIdAndUser_id(Long id, final Long currentUserId);
    Set<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    Set<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name);
}
