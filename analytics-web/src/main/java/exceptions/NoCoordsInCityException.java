package exceptions;

public class NoCoordsInCityException extends RuntimeException {
    public NoCoordsInCityException(String message) {
        super(message);
    }
}
