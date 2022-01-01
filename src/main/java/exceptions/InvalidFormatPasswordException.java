package exceptions;

public class InvalidFormatPasswordException extends RuntimeException{
    public InvalidFormatPasswordException(String message) {
        super(message);
    }
}
