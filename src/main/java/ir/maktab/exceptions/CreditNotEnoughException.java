package ir.maktab.exceptions;

public class CreditNotEnoughException extends RuntimeException {
    public CreditNotEnoughException() {
        super("credit of customer is not enough.");
    }
}
