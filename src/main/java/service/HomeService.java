package service;

import exceptions.InvalidFormatNameException;
import exceptions.InvalidFormatPasswordException;
import exceptions.InvalidUserTypeException;
import lombok.Data;
import model.Customer;
import model.Expert;
import model.User;
import model.UserFactory;
import model.enums.UserState;
import model.enums.UserType;
import service.validation.CheckValidation;

@Data
public class HomeService {
    public void register(String name, String family, String email, String password, UserType type) {
        try {
            if (checkValidation(name, family, email, password)) {
                User user = UserFactory.getUser(type, name, family, email, password);
                if (user instanceof Customer) {
                    CustomerService customerService = new CustomerService();
                    customerService.saveCustomer((Customer) user);
                } else if (user instanceof Expert) {
                    ExpertService expertService = new ExpertService();
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
