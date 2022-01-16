package ir.maktab.exceptions;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException() {
        super("price should be bigger than basePrice of subService");
    }
}
