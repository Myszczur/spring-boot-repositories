package pl.urbanik.callapp.user;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import pl.urbanik.callapp.exception.DuplicateUserException;
import pl.urbanik.callapp.exception.IncorrectPasswordException;
import pl.urbanik.callapp.exception.InvalidUserException;
import pl.urbanik.callapp.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author kurbanik
 */

@Log4j
@Service
public class UserService {

    private static final List<User> USER_LIST = new ArrayList<>();

    public void register(User user) {
        try {
            if (USER_LIST.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
                throw new DuplicateUserException("Użytkownik o podanym adresie e-mail już istnieje!");
            }

            if (user.getEmail() == null
                    || user.getPassword() == null
                    || user.getEmail().isEmpty()
                    || user.getPassword().isEmpty()) {
                throw new InvalidUserException("Nieprawidłowe dane użytkownika!");
            }

            user.setStatus("online");
            USER_LIST.add(user);
            log.info("User created: " + user.getEmail());

        } catch (InvalidUserException | DuplicateUserException e) {
            System.err.println(e.getMessage());
            log.error("Error creating User: " + e.getMessage());
        }
    }

    public User login(User user) {
        User connectedUser = null;
        try {
            var userIndex = IntStream.range(0, USER_LIST.size())
                    .filter(index -> USER_LIST.get(index)
                            .getEmail()
                            .equals(user.getEmail()))
                    .findAny()
                    .orElseThrow(UserNotFoundException::new);

            connectedUser = USER_LIST.get(userIndex);

            if (!connectedUser.getPassword().equals(user.getPassword())) {
                throw new IncorrectPasswordException();
            }

            connectedUser.setStatus("online");

        } catch (UserNotFoundException e) {
            System.out.println("Brak użytkownika!");
            log.error("User not found :" + e.getMessage());
        } catch (IncorrectPasswordException e) {
            System.out.println("Nieprawidłowe hasło!");
            log.error("Password Incorrect :" + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Błąd podczas pobierania użytkownika!");
            log.error("NullPointerException: " + e.getMessage());
        }

        log.info("User logged: " + user.getEmail());
        return connectedUser;
    }

    public void logout(String email) {
        try {
            var userIndex = IntStream.range(0, USER_LIST.size())
                    .filter(index -> USER_LIST.get(index)
                            .getEmail()
                            .equals(email))
                    .findAny()
                    .orElseThrow(UserNotFoundException::new);

            USER_LIST.get(userIndex).setStatus("offline");
            log.info("User Logout: " + email);

        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
            log.error("User Not Found! :" + e.getMessage());
        }
    }

    public List<User> findAll() {

        return USER_LIST;
    }
}
