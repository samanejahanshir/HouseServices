package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SubServiceTest {
    static SubServicesService service;

    @BeforeAll
    static void init() {
        service = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(SubServicesService.class);
    }
    @Test
    void getListSubServiceTest() {
        int result = service.getListSubService("tasisat").size();
        Assertions.assertEquals(1, result);
    }
}
