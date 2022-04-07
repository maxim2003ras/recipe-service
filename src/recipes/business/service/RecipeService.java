package recipes.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipes.business.entity.AppUser;
import recipes.business.entity.Recipe;
import recipes.business.exception.AccessDeniedException;
import recipes.business.exception.RecipeNotFoundException;
import recipes.persistence.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addNewRecipe(Recipe recipe, final AppUser user) {
        recipe.setDate(LocalDateTime.now());
        recipe.setUser(user);
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(final Long id) {
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }


    public void deleteRecipe(final Long id, final Long currentUserId) {
        recipeRepository.findByIdAndUser_id(id, currentUserId).ifPresentOrElse((recipe)
                        -> {
                    recipeRepository.deleteById(id);
                },
                ()
                        -> {
                    if (recipeRepository.findById(id).isEmpty()) {
                        throw new RecipeNotFoundException();
                    }
                    else throw new AccessDeniedException();

                });
    }

    public void updateRecipe(final Recipe recipe, final AppUser user) {
        recipeRepository.findByIdAndUser_id(recipe.getId(), user.getId()).ifPresentOrElse(
                (oldRecipe) -> {
                    recipe.setUser(user);
                    recipeRepository.save(recipe);
                },
                () -> {
                    if (recipeRepository.findById(recipe.getId()).isEmpty()) {
                        throw new RecipeNotFoundException();
                    }
                    else throw new AccessDeniedException();
                }
        );
    }

    public Set<Recipe> getRecipesByCategory(final String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public Set<Recipe> getRecipesByName(final String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }
}