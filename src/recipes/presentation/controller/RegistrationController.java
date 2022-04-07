package recipes.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import recipes.business.entity.AppUser;
import recipes.business.security.service.UserService;

import javax.validation.Valid;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@RestController
@RequestMapping("api/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid AppUser user, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>("Invalid data!", HttpStatus.BAD_REQUEST);
        }
        userService.register(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
