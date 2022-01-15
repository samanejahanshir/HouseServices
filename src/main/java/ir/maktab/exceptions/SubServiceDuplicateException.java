package ir.maktab.exceptions;

public class SubServiceDuplicateException extends RuntimeException{
    public SubServiceDuplicateException() {
        super("this services is exist .");
    }
}
