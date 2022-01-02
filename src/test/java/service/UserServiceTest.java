package service;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    void getUser_SaveToDb() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*User user = User.UserBuilder.anUser()
                .withFirstName("user")
                .withLastName("family")
                .withPassword("a1234S454")
                .withEmail("user@email.com")
                .build();

        UserService userService = new UserService();
        userService.saveUser(user);*/
    }

    @Test
    void getUser_ByEmail() {
        UserService userService = new UserService();
        User user = userService.getUserByEmail("user@email.com","a1234S454");
        Assertions.assertNotNull(user);

    }
}
