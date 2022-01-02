package service;

import dao.UserDao;
import lombok.Data;
import model.User;

@Data
public class UserService {
    UserDao userDao = new UserDao();

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User getUserByEmail(String email, String password) {
        return userDao.getUserByEmail(email, password);
    }

}
