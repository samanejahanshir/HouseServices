package model;

import exceptions.InvalidUserTypeException;
import model.enums.UserType;

public class UserFactory {
    private User user;

    public User getUser(UserType type){
        switch (type){
            case EXPERT:
                user=new Expert();
                break;
            case CUSTOMER:
                user=new Customer();
                break;
            default:
                throw new InvalidUserTypeException("Error==>this user type not exist");
        }
        return user;
    }
}
