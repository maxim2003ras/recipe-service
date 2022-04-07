package recipes.business.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User already persisted in database")
public class UserAlreadyPersisted extends RuntimeException{
    public UserAlreadyPersisted() {
        super();
    }
}
