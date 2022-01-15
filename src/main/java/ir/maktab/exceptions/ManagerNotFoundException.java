package ir.maktab.exceptions;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException() {
        super("this manager by this userName and password not exist");
    }
}
