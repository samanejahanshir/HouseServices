package service;

import dao.ExpertDao;
import model.Expert;
import model.enums.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HomeServiceTest {
    static HomeService service;

    @BeforeAll
    static void init() {
        service = new HomeService();
    }

    @Test
    void registerTest() {
        service = new HomeService();
        service.register("sara", "rezaii", "sara@gmail.com", "rg456H543", UserType.CUSTOMER);
    }

    @Test
    void checkConfirmUserTest() {
        ExpertDao expertDao = new ExpertDao();
        Expert expert = expertDao.getExpertByEmail("expert@email.com");
        Assertions.assertFalse(service.checkConfirmUser(expert));
    }
}
