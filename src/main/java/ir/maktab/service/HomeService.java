package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.User;
import ir.maktab.data.model.UserFactory;
import ir.maktab.exceptions.InvalidFormatNameException;
import ir.maktab.exceptions.InvalidFormatPasswordException;
import ir.maktab.exceptions.InvalidUserTypeException;
import ir.maktab.service.validation.CheckValidation;
import lombok.Data;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Data
public class HomeService {
  /*  public void register(String name, String family, String email, String password, UserType type) {
        try {
            if (checkValidation(name, family, email, password)) {
                User user = UserFactory.getUser(type, name, family, email, password);
                if (user instanceof Customer) {
                    CustomerService customerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
                    customerService.saveCustomer((Customer) user);
                } else if (user instanceof Expert) {
                    ExpertService expertService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertService.class);
                    expertService.saveExpert((Expert) user);
                }
            }
        } catch (InvalidFormatNameException | InvalidFormatPasswordException | InvalidUserTypeException e) {
            e.printStackTrace();
        }
    }*/

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

    public boolean checkConfirmUser(User user) {
        if (user.getState().equals(UserState.CONFIRMED)) {
            return true;
        } else {
            return false;
        }
    }
}
