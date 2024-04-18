package pl.urbanik.callapp.exception;

/**
 * @author kurbanik
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super("User not Found!");
    }
}
