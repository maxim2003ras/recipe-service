package recipes.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid recipe!")
public class InvalidRecipe extends RuntimeException{
    public InvalidRecipe() {
        super();
    }
}
