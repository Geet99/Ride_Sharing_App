package exception;

public class VehicleAlreadyExistsException extends RuntimeException {
    public VehicleAlreadyExistsException(String errorMsg) {
        super(errorMsg);
    }
}