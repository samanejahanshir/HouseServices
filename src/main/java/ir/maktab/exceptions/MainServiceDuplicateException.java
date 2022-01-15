package ir.maktab.exceptions;

public class MainServiceDuplicateException extends  RuntimeException {
    public MainServiceDuplicateException() {
        super("this mainService is exist");
    }
}
