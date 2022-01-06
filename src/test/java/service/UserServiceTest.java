package service;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    void getUser_ByEmail() {
        UserService userService = new UserService();
        User user = userService.getUserByEmail("user@email.com", "a1234S454");
        Assertions.assertNotNull(user);
    }
}
