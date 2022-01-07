package service;

import config.SpringConfig;
import exceptions.InvalidFormatNameException;
import exceptions.InvalidFormatPasswordException;
import exceptions.InvalidUserTypeException;
import lombok.Data;
import data.Customer;
import data.Expert;
import data.User;
import data.UserFactory;
import data.enums.UserState;
import data.enums.UserType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import service.validation.CheckValidation;
@Service
@Data
public class HomeService {
    public void register(String name, String family, String email, String password, UserType type) {
        try {
            if (checkValidation(name, family, email, password)) {
                User user = UserFactory.getUser(type, name, family, email, password);
                if (user instanceof Customer) {
                    CustomerService customerService =new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
                    customerService.saveCustomer((Customer) user);
                } else if (user instanceof Expert) {
                    ExpertService expertService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertService.class);
                    expertService.saveExpert((Expert) user);
                }
            }
        } catch (InvalidFormatNameException | InvalidFormatPasswordException | InvalidUserTypeException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidation(String name, String family, String email, String password) {
        boolean isValidName = CheckValidation.isUserNameValid(name);
        boolean isValidFamily = CheckValidation.isUserNameValid(family);
        boolean isValidPass = CheckValidation.isPassValid(password);
        boolean isValidEmail = CheckValidation.validEmail(email);
        if (isValidName && isValidFamily && isValidPass) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkConfirmUser(User user) {
        if (user.getState().equals(UserState.CONFIRMED)) {
            return true;
        } else {
            return false;
        }
    }
}
