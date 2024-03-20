package exception;

public class NoSuchVehicleException extends RuntimeException {
    public NoSuchVehicleException(String errorMsg) {
        super(errorMsg);
    }
}