package service;

import dao.CustomerDao;
import model.Customer;
import model.MainServices;
import model.SubServices;
import model.enums.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ManagerServiceTest {
    static ManagerService managerService;

    @BeforeAll
    static void init() {
        managerService = new ManagerService();
    }

    @Test
    void getService_SaveToDb() {
        SubServices subServices = SubServices.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("Decoration home")
                .withSubService("wallpaper design")
                .withDescription("this is wallpaper design.price is for one meter wallpaper")
                .build();
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

        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                managerService.addServicesToDb(subServices));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this Main service not exist", exp.getMessage());
    }

    @Test
    void save_MainServiceToDb() {
        MainServices mainServices = new MainServices();
        mainServices.setGroupName("tasisat");
        managerService.saveMainServiceToDb(mainServices);
    }

    @Test
    void saveDuplicate_MainServiceToDb_ThrowException() {
        MainServices mainServices = new MainServices();
        mainServices.setGroupName("lavazem khanegi");
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                managerService.saveMainServiceToDb(mainServices));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this mainService is exist", exp.getMessage());

    }

    @Test
    void getListUser() {
        System.out.println(managerService.getListUsers().size());
    }

    @Test
    void getListUserByConditionTest() {
        int result = managerService.getListUsersByCondition(UserType.CUSTOMER, "", "sara", "").size();
        Assertions.assertEquals(1, result);
    }

    @Test
    void getListUserByConditionTestReturnAnyThing() {
        int result = managerService.getListUsersByCondition(UserType.CUSTOMER, "sara@yahoo.com", "", "").size();
        Assertions.assertEquals(0, result);
    }

   /* //TODO.....
    @Test
    void getListUserByConditionTestWithoutType() {
        int result = managerService.getListUsersByCondition(null, "", "sara", "").size();
        Assertions.assertEquals(1, result);
    }*/

    @Test
    void getListUserNoConfirmTest() {
        System.out.println(managerService.getListUserNoConfirm().size());
    }

    @Test
    void confirmUserTest() {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getCustomerByEmail("sara@gmail.com");
        managerService.confirmUser(customer);
    }
}
