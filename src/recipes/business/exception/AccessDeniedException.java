package recipes.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Access Denied")
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
        super();
    }
}
