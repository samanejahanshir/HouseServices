package service;

import config.SpringConfig;
import data.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceTest {

   /* @Test
    void getUser_ByEmail() {
        UserService userService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(UserService.class);
        User user = userService.getUserByEmail("reza@email.com", "rg456H543");
        Assertions.assertNotNull(user);
    }*/
}
