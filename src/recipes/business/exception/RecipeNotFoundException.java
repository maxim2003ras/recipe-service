package recipes.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recipe not found!")
public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException() {
        super();
    }
}
