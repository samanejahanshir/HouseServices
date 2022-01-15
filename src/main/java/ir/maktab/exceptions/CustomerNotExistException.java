package ir.maktab.exceptions;

public class CustomerNotExistException extends  RuntimeException{
    public CustomerNotExistException() {
        super("this customer not exist .");
    }
}
