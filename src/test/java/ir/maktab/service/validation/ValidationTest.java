package ir.maktab.service.validation;

import ir.maktab.exceptions.InvalidFormatNameException;
import ir.maktab.exceptions.InvalidFormatPasswordException;
import ir.maktab.exceptions.InvalidTimeException;
import ir.maktab.service.validation.CheckValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidationTest {
    @ParameterizedTest
    @CsvSource({"samane", "fatemehZahra"})
    void getUserName_ReturnTrueResult(String name) {
        boolean result = CheckValidation.isUserNameValid(name);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"sa", "fatemehZ34"})
    void getUserName_ThrowExceptionResult(String name) {
        InvalidFormatNameException exp = Assertions.assertThrows(InvalidFormatNameException.class, () ->
                CheckValidation.isUserNameValid(name));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("user name is not valid", exp.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"sf56", "675678765"})
    void getUserAndPass_throwExceptionResult(String pass) {
        InvalidFormatPasswordException exp = Assertions.assertThrows(InvalidFormatPasswordException.class, () ->
                CheckValidation.isPassValid(pass));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("password is not valid", exp.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"sAm23456B", "123456Smf"})
    void getPassword_ReturnTrueResult(String pass) {
        boolean result = CheckValidation.isPassValid(pass);
        Assertions.assertTrue(result);
    }

    @Test
    void getTime_throwExceptionResult() {
        InvalidTimeException exp = Assertions.assertThrows(InvalidTimeException.class, () ->
                CheckValidation.isValidTime(25));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this time not valid", exp.getMessage());
    }

    @Test
    void getTime_ReturnTrue() {
        boolean result = CheckValidation.isValidTime(14);
        Assertions.assertTrue(result);
    }
}
