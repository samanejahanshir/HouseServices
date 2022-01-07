package service.validation;

import exceptions.InvalidFormatNameException;
import exceptions.InvalidFormatPasswordException;
import exceptions.InvalidTimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValidation {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z]{3,20}";

    public static boolean isUserNameValid(String userName) {
        Pattern userPattern = Pattern.compile(USERNAME_PATTERN);
        if ((userName == null)) {
            return false;
        }
        Matcher userMatcher = userPattern.matcher(userName);
        if (!userMatcher.matches()) {
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
        if (!passMatcher.matches()) {
            throw new InvalidFormatPasswordException("password is not valid");
        }
        return true;
    }

    public static boolean isValidTime(int time) {
        if (time < 1 || time > 24) {
            throw new InvalidTimeException("this time not valid");
        } else {
            return true;
        }
    }

    public static boolean validEmail(String email) {
        boolean matches = email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
                "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        if (matches)
            return true;
        return false;
    }

}
