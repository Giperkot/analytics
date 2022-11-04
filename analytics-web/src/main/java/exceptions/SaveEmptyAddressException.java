package exceptions;

public class SaveEmptyAddressException extends RuntimeException{

    public SaveEmptyAddressException(String message) {
        super(message);
    }
}
