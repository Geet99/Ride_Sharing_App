package exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String errorMsg) {
        super(errorMsg);
    }
}