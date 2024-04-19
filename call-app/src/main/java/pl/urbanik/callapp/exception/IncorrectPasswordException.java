package pl.urbanik.callapp.exception;

/**
 * @author kurbanik
 */
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(){
        super("Password incorrect!");
    }
}
