package service;

import model.enums.UserType;
import org.junit.jupiter.api.Test;

public class HomeServiceTest {
    @Test
    void registerTest(){
        HomeServicesService service=new HomeServicesService();
        service.register("sara","rezaii","sara@gmail.com","rg456H543", UserType.CUSTOMER);
    }
}
