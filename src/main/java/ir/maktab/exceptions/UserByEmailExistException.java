package ir.maktab.exceptions;

public class UserByEmailExistException extends RuntimeException{
    public UserByEmailExistException() {
        super("user by this email is exist");
    }
}
