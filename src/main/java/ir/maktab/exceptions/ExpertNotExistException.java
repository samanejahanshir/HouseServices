package ir.maktab.exceptions;

public class ExpertNotExistException extends RuntimeException{
    public ExpertNotExistException() {
        super("this expert not exist.");
    }
}
