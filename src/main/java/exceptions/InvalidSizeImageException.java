package exceptions;

public class InvalidSizeImageException extends RuntimeException {
    public InvalidSizeImageException(String message) {
        super(message);
    }
}
