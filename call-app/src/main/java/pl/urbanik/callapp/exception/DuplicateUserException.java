package pl.urbanik.callapp.exception;

/**
 * @author kurbanik
 */
public class DuplicateUserException extends Exception {
    public DuplicateUserException(String message){
        super(message);
    }
}
