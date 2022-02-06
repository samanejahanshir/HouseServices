package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.model.*;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.dto.SubServiceDto;
import ir.maktab.exceptions.CreditNotEnoughException;
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
    static OrderService orderService;
    static OfferService offerService;
static ExpertService expertService;
    @BeforeAll
    static void init() {
        expertService=new AnnotationConfigApplicationContext(SpringConfig.class).getBean(ExpertService.class);
        customerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
        orderService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(OrderService.class);
        offerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(OfferService.class);
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
        OrderDto orderDto = OrderDto.builder()
                .orderDoingDate(date)
                .orderDoingTime(10)
                .description("saat 10 sobh anjam shavad")
                .build();
        SubServiceDto subServiceDto = SubServiceDto.builder()
                .name("bargh").build();
        orderDto.setSubServiceDto(subServiceDto);
        Address address = Address.builder()
                .city("ghom")
                .postalCode("3424")
                .tag("34")
                .street("yas")
                .build();
      //  orderDto.setAddress(address);
        orderService.saveOrder(orderDto, "zahra@email.com");
    }

    @Test
    void getListOrdersTest() {
        System.out.println(orderService.getListOrders("samane@email.com"));
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
        System.out.println(orderService.getListOrdersThatNotFinished("zahra@email.com"));
    }

    @Test
    void selectOfferForOrderTest() {
        List<OrderDto> orderDtos = orderService.getListOrdersThatNotFinished("zahra@email.com");
        System.out.println(orderDtos.get(0));
        List<OfferDto> listOffers = offerService.getListOffersForCustomer(orderDtos.get(0));
        listOffers.forEach(System.out::println);
        orderService.selectOfferForOrder(listOffers.get(0));
    }

    @Test
    void deleteOrderTest() {
        orderService.deleteOrder(1);
    }

    @Test
    void RegisterACommentToOrderTest() {
        Commend commend=Commend.builder()
                .commend("bad nabood ")
                .score(6)
                .build();
        orderService.registerACommentToOrder(commend,9 );
    }

    @Test
    void getOrdersWaitForSelectExpertTest() {
        System.out.println(orderService.getListOrdersForExpert("ali@email.com"));
    }

    @Test
    void updateOrderStateToPaidTest_ThrowException() {
        CreditNotEnoughException exp = Assertions.assertThrows(CreditNotEnoughException.class, () ->
                orderService.updateOrderStateToPaidByCredit(10));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("credit of customer is not enough.", exp.getMessage());
    }

    @Test
    void updateOrderStateToPaidTest() {
        orderService.updateOrderStateToPaidByCredit(9);
    }

   /* @Test
    void getScoreOfOrderTest(){
        int score = orderService.getScoreOrderForExpert(9);
        Assertions.assertEquals(6,score);
    }*/
}
