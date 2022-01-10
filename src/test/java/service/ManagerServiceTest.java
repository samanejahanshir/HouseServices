package service;

import config.SpringConfig;
import data.model.Customer;
import data.model.MainServices;
import data.model.Manager;
import data.model.SubServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ManagerServiceTest {
    static ManagerService managerService;
    static CustomerService customerService;

    @BeforeAll
    static void init() {
        managerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ManagerService.class);
        customerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
    }

    @Test
    void getService_SaveToDb() {
        SubServices subServices = SubServices.ServicesBuilder.aServices()
                .withBasePrice(3000)
                .withGroupService("tasisat")
                .withSubService("kooler")
                .withDescription("kooler sakhteman va hale moshkele ")
                .build();
        managerService.addServicesToDb(subServices);
    }

    @Test
    void getServiceDuplicate_SaveToDb_ThrowException() {
        SubServices subServices = SubServices.ServicesBuilder.aServices()
                .withBasePrice(2000)
                .withGroupService("tasisat")
                .withSubService("bargh")
                .withDescription("sim keshi sakhteman va hale moshkele bargh sakhteman")
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
                .withGroupService("lavazem khanegi")
                .withSubService("yakhchal")
                .withDescription("tamirate anva yakhchal")
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

   /* @Test
    void getListUserByConditionTest() {
        int result = managerService.getListUsersByCondition(UserType.CUSTOMER, "", "sara", "").size();
        Assertions.assertEquals(1, result);
    }*/

    @Test
    void getListUserNoConfirmTest() {
        System.out.println(managerService.getListUserNoConfirm().size());
    }

    @Test
    void confirmUserTest() {
        Customer customer = customerService.getCustomerByEmail("customer@email.com");
        managerService.confirmUser(customer);
    }

    @Test
    void getManagerTest() {
        Manager manager = managerService.getManagerByNameAndPass("admin", "admin");
        Assertions.assertNull(manager);
    }

    @Test
    void saveManagerTest() {
        Manager manager = Manager.ManagerBuilder.aManager()
                .withUserName("admin")
                .withPassword("1234sd34A")
                .build();
        managerService.saveManager(manager);
    }

    @Test
    void deleteMainServicesTest(){
        managerService.deleteMainServices("tasisat");
    }
}
