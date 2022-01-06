package service.validation;

import exceptions.InvalidFormatNameException;
import exceptions.InvalidFormatPasswordException;
import exceptions.InvalidTimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValidation {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String USERNAME_PATTERN =  "^[a-zA-Z]{3,20}" ;

    public static boolean isUserNameValid(String usernamme) {
        Pattern userPattern = Pattern.compile(USERNAME_PATTERN);
        if ((usernamme == null)) {
            return false;
        }
        Matcher userMatcher = userPattern.matcher(usernamme);
        if(!userMatcher.matches()){
            throw new InvalidFormatNameException("user name is not valid");
        }
        return true;
    }

    public static boolean isPassValid(String password) {
        Pattern passPattern = Pattern.compile(PASSWORD_PATTERN);
        if ((password == null)) {
            return false;
        }
        Matcher passMatcher = passPattern.matcher(password);
        if (!passMatcher.matches()){
            throw  new InvalidFormatPasswordException("password is not valid");
        }
        return true;
    }
    public static boolean isValidTime(int time){
        if(time<1 || time>24){
            throw  new InvalidTimeException("this time not valid");
        }
        else {
            return  true;
        }
    }
}
