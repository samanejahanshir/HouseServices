package ir.maktab.service;

import ir.maktab.data.dao.UserDao;
import ir.maktab.data.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public void saveUser(User user) {
        userDao.save(user);
    }

/*
    public User getUserByEmail(String email, String password) {
        return userDao.getUserByEmail(email, password);
    }
*/
}
