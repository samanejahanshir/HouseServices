package service;

import dao.ServicesDao;
import exceptions.InvalidFormatNameException;
import model.Services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.validation.UserAndPassValidation;

public class ManagerServiceTest {
    @Test
    void getService_SaveToDb() {
        Services services = Services.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("Decoration home")
                .withSubService("wallpaper design")
                .withDescription("this is wallpaper design.price is for one meter wallpaper")
                .build();
        ManagerService managerService= new ManagerService();
       managerService.addServicesToDb(services);
    }

    @Test
    void getService_SaveToDb_ThrowException() {
        Services services = Services.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("Decoration home")
                .withSubService("wallpaper design")
                .withDescription("this is wallpaper design.price is for one meter wallpaper")
                .build();
        ManagerService managerService= new ManagerService();

        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                managerService.addServicesToDb(services));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this services is exist .", exp.getMessage());
    }

}
