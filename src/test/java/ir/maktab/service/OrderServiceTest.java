package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.model.Address;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Orders;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderServiceTest {
    static CustomerService customerService;
    static  OrderService orderService;

    @BeforeAll
    static void init() {
        customerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
        orderService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(OrderService.class);

    }
    @Test
    void saveOrderTest() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2022-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Address address=Address.builder()
                .city("semnan")
                .street("yas")
                .tag("23")
                .postalCode("3456")
                .build();
        OrderDto orderDto = OrderDto.builder()
                .orderDoingDate(date)
                .orderDoingTime(10)
                .description("saat 10 sobh anjam shavad")
                //.address(address)
                .build();
        SubServiceDto subServiceDto=SubServiceDto.builder()
                        .name("nama kari").build();
        orderService.saveOrder(orderDto,subServiceDto,"sama@email.com");
    }
    @Test
    void getListOrdersTest() {
        System.out.println(orderService.getListOrders("sama@email.com"));
    }

   /* @Test
    void getListOfferTest() {
        Orders orders = orderService.getListOrders("customer@email.com").get(0);
        System.out.println(customerService.getListOffers(orders));
    }*/
   /* @Test
    void saveOrderTestByPriceLowerThanBasePrice_ThrowException() {
        SubServiceDao subServiceDao = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(SubServiceDao.class);
        Customer customer = customerService.getCustomerByEmail("customer@email.com");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2022-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Orders order = Orders.builder()
                .orderDoingDate(date)
                .orderDoingTime(10)
                .customer(customer)
                .description("saat 10 sobh anjam shavad")
                .proposedPrice(1000)
                .subServices(subServiceDao.findByName( "bargh").get())
                .build();

        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                orderService.saveOrder(order));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("proposedPrice should be bigger than basePrice of subService", exp.getMessage());
    }*/
   @Test
   void getListOrdersThatNotFinishedTest() {
       System.out.println(orderService.getListOrdersThatNotFinished("sama@email.com"));
   }

    @Test
    void selectOfferForOrderTest() {
        orderService.selectOfferForOrder(13, 2);
    }

    @Test
    void deleteOrderTest() {
        orderService.deleteOrder(1);
    }

    @Test
    void RegisterACommentToOrderTest() {
        int result = orderService.RegisterACommentToOrder(2, "alii bood . ba tashakor");
        Assertions.assertEquals(1, result);
    }


}
