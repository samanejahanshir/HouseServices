package ir.maktab.exceptions;

public class MainServiceNotFoundException extends  RuntimeException{
    public MainServiceNotFoundException() {
        super("this Main service not exist");
    }
}
