package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.model.Manager;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.MainServiceDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.exceptions.MainServiceDuplicateException;
import ir.maktab.exceptions.MainServiceNotFoundException;
import ir.maktab.exceptions.SubServiceDuplicateException;
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
        SubServiceDto subServiceDto = SubServiceDto.builder()
                .basePrice(3000)
                .groupName("nezafat")
                .name("shosteshuye nama sakhteman")
                .description("shosteshooye namaye sakhteman  ra be ma besparid ")
                .build();
        managerService.saveSubService(subServiceDto);
    }

    @Test
    void getServiceDuplicate_SaveToDb_ThrowException() {
        SubServiceDto subServiceDto = SubServiceDto.builder()
                .basePrice(2000)
                .groupName("tasisat")
                .name("bargh")
                .description("sim keshi sakhteman va hale moshkele bargh sakhteman")
                .build();
        SubServiceDuplicateException exp = Assertions.assertThrows(SubServiceDuplicateException.class, () ->
                managerService.saveSubService(subServiceDto));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this services is exist .", exp.getMessage());
    }

    @Test
    void getServiceThatNotExistMainService_SaveToDb_ThrowException() {
        SubServiceDto subServiceDto = SubServiceDto.builder()
                .basePrice(2000)
                .groupName("lavazem khanegi")
                .name("yakhchal")
                .description("tamirate anva yakhchal")
                .build();

        MainServiceNotFoundException exp = Assertions.assertThrows(MainServiceNotFoundException.class, () ->
                managerService.saveSubService(subServiceDto));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this Main service not exist", exp.getMessage());
    }

    @Test
    void save_MainServiceToDb() {
        MainServiceDto mainServiceDto =  MainServiceDto.builder().groupName("sakhteman").build();
        managerService.saveMainServiceToDb(mainServiceDto);
    }

    @Test
    void saveDuplicate_MainServiceToDb_ThrowException() {
        MainServiceDto mainServiceDto =  MainServiceDto.builder().groupName("sakhteman").build();
        MainServiceDuplicateException exp = Assertions.assertThrows(MainServiceDuplicateException.class, () ->
                managerService.saveMainServiceToDb(mainServiceDto));
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
        System.out.println(managerService.getListCustomerNoConfirm().size());
    }

    @Test
    void confirmUserTest() {
        CustomerDto customerDto=CustomerDto.builder().email("sama@email.com").build();
        managerService.confirmCustomer(customerDto);
    }

    @Test
    void getManagerTest() {
        Manager manager = managerService.getManagerByEmailAndPass("admin", "admin");
        Assertions.assertNull(manager);
    }

  /*  @Test
    void saveManagerTest() {
        Manager manager = Manager.builder()
                .userName("admin")
                .password("1234sd34A")
                .build();
        managerService.saveManager(manager);
    }*/

    @Test
    void deleteMainServicesTest() {
        managerService.deleteMainServices("tasisat");
    }

    @Test
    void deleteSubServiceTest() {
        managerService.deleteSubServices("kooler");
    }
}
