package service;

import config.SpringConfig;
import data.model.Expert;
import data.enums.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HomeServiceTest {
    static HomeService service;
static  ExpertService expertService;
    @BeforeAll
    static void init() {
        service = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(HomeService.class);
        expertService=new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertService.class);
    }

    @Test
    void registerTest() {
        service = new HomeService();
        service.register("reza", "rezaii", "reza@gmail.com", "rg456H543", UserType.EXPERT);
    }

    @Test
    void checkConfirmUserTest() {
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        Assertions.assertFalse(service.checkConfirmUser(expert));
    }
}
