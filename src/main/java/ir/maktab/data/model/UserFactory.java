package ir.maktab.data.model;

import ir.maktab.data.enums.UserType;
import ir.maktab.exceptions.InvalidUserTypeException;

public class UserFactory {

    public static User getUser(UserType type, String firstName, String lastName, String email, String password) {
        switch (type) {
            case EXPERT:
                return new Expert.ExpertBuilder().withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmail(email)
                        .withPassword(password)
                        .build();
            case CUSTOMER:
                return new Customer.CustomerBuilder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmail(email)
                        .withPassword(password)
                        .build();
            default:
                throw new InvalidUserTypeException("Error==>this user type not exist");
        }
    }
}
