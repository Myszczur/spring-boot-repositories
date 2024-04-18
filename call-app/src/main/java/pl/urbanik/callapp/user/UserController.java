package pl.urbanik.callapp.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.urbanik.callapp.exception.UserNotFoundException;

import java.util.List;

/**
 * @author kurbanik
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void register(@RequestBody User user) {

        userService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return userService.login(user);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody User email) throws UserNotFoundException {

        userService.logout(email.getEmail());
    }

    @GetMapping
    public List<User> findAll() {

        return userService.findAll();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
