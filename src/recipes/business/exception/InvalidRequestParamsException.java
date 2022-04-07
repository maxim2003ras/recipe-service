package recipes.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid request params!")
public class InvalidRequestParamsException extends RuntimeException{
    public InvalidRequestParamsException() {
        super();
    }
}
