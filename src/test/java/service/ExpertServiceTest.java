package service;

import model.Expert;
import model.Orders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExpertServiceTest {
    static ExpertService expertService;

    @BeforeAll
    static void init() {
        expertService = new ExpertService();
    }

    @Test
    void getExpert_SaveToDb() {
        File file = new File("/res/unknown.png");
        byte[] imageFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("expert")
                .withLastName("Efamily")
                .withPassword("a1234S454")
                .withEmail("expert@email.com")
                .withImage(imageFile)
                .build();

        expertService.saveExpert(expert);
    }

    @Test
    void getExpertDuplicate_SaveToDb_ThrowException() {
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("expert")
                .withLastName("Efamily")
                .withPassword("a1234S45874")
                .withEmail("expert@email.com")
                //.withImage(imageFile)
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
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        System.out.println(expertService.getListOrders(expert).size());
    }

    @Test
    void SetImageTest() {
        File file = new File("/res/unknown.png");
        expertService.setImage(file, "expert@email.com");
    }

    @Test
    void addSubServicesTOExpertLiseTest() {
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        expertService.addSubServiceToExpertList(expert, "bargh");
    }

    @Test
    void deleteSubServicesFromExpert() {
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        expertService.deleteSubServiceFromExpert(expert, "bargh");
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
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        List<Orders> orders = expertService.getListOrders(expert);
        if (!orders.isEmpty()) {
            expertService.addOfferToOrder(expert, orders.get(0), 3000, date, 2, 14);
        }
    }

    @Test
    void getOrdersWaitForSelectExpertTest() {
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        System.out.println(expertService.getOrdersWaitForSelectExpert(expert).get(0));
    }
}
