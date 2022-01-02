package model;

import exceptions.InvalidUserTypeException;
import model.enums.UserType;

public class UserFactory {
    private User user;

    public User getUser(UserType type,String firstName,String lastName,String email,String password) {
        switch (type) {
            case EXPERT:
                user = new Expert.ExpertBuilder().withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmail(email)
                        .withPassword(password)
                        .build();
                break;
            case CUSTOMER:
                user = new Customer.CustomerBuilder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmail(email)
                        .withPassword(password)
                        .build();;
                break;
            default:
                throw new InvalidUserTypeException("Error==>this user type not exist");
        }
        return user;
    }
}
