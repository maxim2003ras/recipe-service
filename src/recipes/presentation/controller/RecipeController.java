package recipes.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import recipes.business.dto.RecipeIdDTO;
import recipes.business.entity.AppUser;
import recipes.business.entity.Recipe;
import recipes.business.exception.InvalidRecipe;
import recipes.business.exception.InvalidRequestParamsException;
import recipes.business.security.service.UserService;
import recipes.business.service.RecipeService;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@RestController
@RequestMapping("api/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public RecipeIdDTO addRecipe(@RequestBody @Valid Recipe recipe, Errors errors, @AuthenticationPrincipal UserDetails userDetails) {
        if (errors.hasErrors()) throw new InvalidRecipe();
        AppUser user = userService.getUserByEmail(userDetails.getUsername());
        Long currentId = recipeService.addNewRecipe(recipe, user).getId();
        return new RecipeIdDTO(currentId);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        AppUser user = userService.getUserByEmail(userDetails.getUsername());
        Long currentUserId = user.getId();
        recipeService.deleteRecipe(id, currentUserId);
        return new ResponseEntity<>("No content in the response", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecipe(@RequestBody @Valid Recipe recipe, @PathVariable Long id, Errors errors, @AuthenticationPrincipal UserDetails userDetails) {
        if (errors.hasErrors()) throw new InvalidRecipe();
        recipe.setId(id);
        recipe.setDate(LocalDateTime.now());
        AppUser user = userService.getUserByEmail(userDetails.getUsername());
        Long currentUserId = user.getId();
        recipeService.updateRecipe(recipe, user);
        return new ResponseEntity<>("No content in the response", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public Set<Recipe> getRecipesByParam(@RequestParam(name = "category", required = false) String category
            ,@RequestParam(name = "name", required = false) String name) {
        if ((category == null && name == null) || (category != null && name != null)) throw new InvalidRequestParamsException();
        if  (category != null) {
            return recipeService.getRecipesByCategory(category);
        }
        else {
            return recipeService.getRecipesByName(name);
        }
    }
}
