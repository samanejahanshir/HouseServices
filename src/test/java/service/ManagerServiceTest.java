package service;

import model.MainServices;
import model.SubServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerServiceTest {
    @Test
    void getService_SaveToDb() {
        SubServices subServices = SubServices.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("Decoration home")
                .withSubService("wallpaper design")
                .withDescription("this is wallpaper design.price is for one meter wallpaper")
                .build();
        ManagerService managerService= new ManagerService();
       managerService.addServicesToDb(subServices);
    }

    @Test
    void getService_SaveToDb_ThrowException() {
        SubServices subServices = SubServices.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("Decoration home")
                .withSubService("wallpaper design")
                .withDescription("this is wallpaper design.price is for one meter wallpaper")
                .build();
        ManagerService managerService= new ManagerService();

        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                managerService.addServicesToDb(subServices));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this services is exist .", exp.getMessage());
    }

    @Test
    void getServiceThatNotExistMainService_SaveToDb_ThrowException() {
        SubServices subServices = SubServices.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("tasisat")
                .withSubService("bargh")
                .withDescription("sim keshi sakhteman")
                .build();
        ManagerService managerService= new ManagerService();

        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                managerService.addServicesToDb(subServices));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this Main service not exist", exp.getMessage());
    }
@Test
    void save_MainServiceToDb(){
    MainServices mainServices=new MainServices();
    mainServices.setGroupName("tasisat");
    ManagerService managerService=new ManagerService();
    managerService.saveMainServiceToDb(mainServices);
}

    @Test
    void saveDuplicate_MainServiceToDb_ThrowException(){
        MainServices mainServices=new MainServices();
        mainServices.setGroupName("lavazem khanegi");
        ManagerService managerService=new ManagerService();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                managerService.saveMainServiceToDb(mainServices));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this mainService is exist", exp.getMessage());

    }
    @Test
    void getListUser(){
        ManagerService managerService=new ManagerService();
        System.out.println(managerService.getListUsers().size());
    }
}
