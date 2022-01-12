package service;

import config.SpringConfig;
import data.enums.OrderState;
import data.model.Expert;
import data.model.Orders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExpertServiceTest {
    static ExpertService expertService;
    static ManagerService managerService;

    @BeforeAll
    static void init() {
        expertService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertService.class);
        managerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ManagerService.class);
    }

    @Test
    void getExpert_SaveToDb() {

        //   SubServices subServices = managerService.getServicesDao().getSubServiceByName("bargh").get();
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("reza")
                .withLastName("rezaii")
                .withPassword("a1234S454")
                .withEmail("reza@email.com")
                .build();
        // expert.getServices().add(subServices);
        expertService.saveExpert(expert);
    }

    @Test
    void getExpertDuplicate_SaveToDb_ThrowException() {
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("alireza")
                .withLastName("alian")
                .withPassword("a1234S454")
                .withEmail("alireza@email.com")
                .build();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                expertService.saveExpert(expert));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this expert by this email is exist .", exp.getMessage());
    }

    @Test
    void getExpert_ByEmailAndPass() {
        Expert expert = expertService.getExpertByEmailAndPass("expert@email.com", "a1234S454");
        Assertions.assertNotNull(expert);

    }

    @Test
    void getNewPass_UpdateExpertPass() {
        int id = expertService.updatePassword("expert@email.com", "56A56745dd66");
        Assertions.assertEquals(1, id);
    }

    @Test
    void getListOrderTest() {
        System.out.println(expertService.getListOrdersOfSubServiceExpert("ali@email.com"));
    }

    @Test
    void SetImageTest() {
        File file = new File("/res/img.jpg");
        expertService.setImage(file, "expert@email.com");
    }

    @Test
    void addSubServicesTOExpertLiseTest() {
        expertService.addSubServiceToExpertList("reza@email.com", "bargh");
    }

    @Test
    void deleteSubServicesFromExpert() {
        expertService.deleteSubServiceFromExpert("alireza@email.com", "bargh");
    }

    @Test
    void addOfferToOrder() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2022-01-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Expert expert = expertService.getExpertByEmail("ali@email.com");
        List<Orders> orders = expertService.getListOrdersOfSubServiceExpert("ali@email.com");
        if (!orders.isEmpty()) {
            expertService.addOfferToOrder(expert, orders.get(0), 3000, 2, 14);
        }
    }

    @Test
    void getOrdersWaitForSelectExpertTest() {
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        System.out.println(expertService.getListOrdersWaitExpertCome(expert).get(0));
    }

    @Test
    void updateOrderStateTest() {
        expertService.updateOrderState(2, OrderState.DONE);
    }

    @Test
    void updateOrderStateToPaidTest_ThrowException() {
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                expertService.updateOrderStateToPaid(2));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("credit of customer is not enough.", exp.getMessage());
    }

    @Test
    void updateOrderStateToPaidTest() {
        int result = expertService.updateOrderStateToPaid(2);
        Assertions.assertEquals(1, result);
    }
}
