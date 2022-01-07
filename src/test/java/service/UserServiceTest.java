package service;

import config.SpringConfig;
import data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceTest {

    @Test
    void getUser_ByEmail() {
        UserService userService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(UserService.class);
        User user = userService.getUserByEmail("user@email.com", "a1234S454");
        Assertions.assertNotNull(user);
    }
}
