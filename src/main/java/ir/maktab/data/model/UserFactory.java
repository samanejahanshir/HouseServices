package ir.maktab.data.model;

import ir.maktab.data.enums.UserType;
import ir.maktab.exceptions.InvalidUserTypeException;

public class UserFactory {

   /* public static User getUser(UserType type, String firstName, String lastName, String email, String password) {
        switch (type) {
            case EXPERT:
                return new Expert.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .build();
            case CUSTOMER:
                return new Customer.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .build();
            default:
                throw new InvalidUserTypeException("Error==>this user type not exist");
        }
    }*/
}
