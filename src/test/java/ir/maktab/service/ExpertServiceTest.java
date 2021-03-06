package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.model.Expert;
import ir.maktab.data.model.Orders;
import ir.maktab.data.model.SubServices;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ExpertServiceTest {
    static ExpertService expertService;
    static ManagerService managerService;
    static OrderService orderService;

    @BeforeAll
    static void init() {
        expertService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertService.class);
        managerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ManagerService.class);
        orderService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(OrderService.class);

    }

    @Test
    void getExpert_SaveToDb() {
        File file = new File("/res/img.jpg");
        byte[] imageFile = new byte[(int) file.length()];
        try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(imageFile);
                fileInputStream.close();
            } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SubServices subServices = managerService.getServicesDao().findByName("nama kari").get();
        Expert expert = Expert.builder()
                .firstName("ali")
                .lastName("rezaii")
                .password("a1234S454")
                .email("ali@email.com")
                .build();
        expert.setServices(List.of(subServices));
        expert.setImage(imageFile);
        expertService.saveExpert(expert);
    }

    @Test
    void getExpertDuplicate_SaveToDb_ThrowException() {
        Expert expert = Expert.builder()
                .firstName("alireza")
                .lastName("alian")
                .password("a1234S454")
                .email("alireza@email.com")
                .build();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                expertService.saveExpert(expert));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this expert by this email is exist .", exp.getMessage());
    }

    @Test
    void getExpert_ByEmailAndPass() {
        ExpertDto expert = expertService.findByEmailAndPass("expert@email.com", "a1234S454");
        Assertions.assertNotNull(expert);

    }

    @Test
    void getNewPass_UpdateExpertPass() {
        expertService.updatePassword("expert@email.com", "56A56745dd66");
    }

    @Test
    void getListOrderTest() {
        System.out.println(orderService.getListOrdersOfSubServiceExpert("ali@email.com"));
    }

  /*  @Test
    void SetImageTest() {
        File file = new File("/res/img.jpg");
        expertService.setImage(file, "expert@email.com");
    }*/

    @Test
    void addSubServicesTOExpertLiseTest() {
        expertService.addSubServiceToExpertList("farhad@email.com", "bargh");
    }

    @Test
    void deleteSubServicesFromExpert() {
        expertService.deleteSubServiceFromExpert("alireza@email.com", "bargh");
    }


    @Test
    void addOfferToOrder_ThrowException() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2022-01-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        OfferDto offerDto = OfferDto.builder()
                .offerPrice(3000)
                .durationTime(2)
                .startTime(14)
                .build();
        List<OrderDto> orders = orderService.getListOrdersOfSubServiceExpert("ali@email.com");
        if (!orders.isEmpty()) {
            offerDto.setOrderDto(orders.get(0));
            RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                    expertService.addOfferToOrder("ali@email.com", offerDto));
            System.out.println(exp.getMessage());
            Assertions.assertEquals("there is a offer by this date and time", exp.getMessage());

        }
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
        OfferDto offerDto = OfferDto.builder()
                .offerPrice(3500)
                .durationTime(2)
                .startTime(18)
                .build();
        List<OrderDto> orders = orderService.getListOrdersOfSubServiceExpert("farhad@email.com");
        if (!orders.isEmpty()) {
            offerDto.setOrderDto(orders.get(1));
            expertService.addOfferToOrder("farhad@email.com", offerDto);

        }
    }

    @Test
    void updateOrderStateTest() {
        expertService.updateOrderState(2, OrderState.DONE);
    }


}
