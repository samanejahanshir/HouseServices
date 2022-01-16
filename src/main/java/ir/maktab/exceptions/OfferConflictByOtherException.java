package ir.maktab.exceptions;

public class OfferConflictByOtherException extends  RuntimeException{
    public OfferConflictByOtherException() {
        super("there is a offer by this date and time");
    }
}
