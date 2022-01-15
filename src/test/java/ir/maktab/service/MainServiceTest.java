package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainServiceTest {
    static MainServicesService service;

    @BeforeAll
    static void init() {
        service = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(MainServicesService.class);
    }
    @Test
    void getListMainServiceTest() {
        int result = service.getListMainService().size();
        Assertions.assertEquals(1, result);
    }
}
