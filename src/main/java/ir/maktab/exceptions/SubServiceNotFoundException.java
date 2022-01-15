package ir.maktab.exceptions;

public class SubServiceNotFoundException extends  RuntimeException{
    public SubServiceNotFoundException() {
        super("this subService not found");
    }
}
