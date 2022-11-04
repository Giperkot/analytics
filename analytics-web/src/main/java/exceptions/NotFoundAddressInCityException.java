package exceptions;

public class NotFoundAddressInCityException extends RuntimeException {

    public NotFoundAddressInCityException(String message) {
        super(message);
    }
}
