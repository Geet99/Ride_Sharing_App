package exception;

public class NoSuchRideException extends RuntimeException {
    public NoSuchRideException(String errorMsg) {
        super(errorMsg);
    }
}