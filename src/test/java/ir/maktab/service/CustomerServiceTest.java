package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.data.model.Address;
import ir.maktab.data.model.Customer;
import ir.maktab.data.model.Orders;
import ir.maktab.dto.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerServiceTest {
    static CustomerService customerService;

    @BeforeAll
    static void init() {
        customerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
    }

    @Test
    void getCustomer_SaveToDb() {
        Address address = Address.builder()
                .city("semnan")
                .street("yas")
                .postalCode("3424")
                .tag("34")
                .build();
        Customer customer = Customer.builder()
                .firstName("sama")
                .lastName("samaii")
                .password("a1234S454")
                .email("sama@email.com")
                .build();
      //  customer.getAddresses().add(address);
        customerService.saveCustomer(customer);
    }

    @Test
    void getCustomerDuplicate_SaveToDb_ThrowException() {
        Customer customer = Customer.builder()
                .firstName("customer")
                .lastName("Cfamily")
                .password("a1234S454")
                .email("customer@email.com")
                .build();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.saveCustomer(customer));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this user by this email is exist", exp.getMessage());
    }

    @Test
    void getCustomer_ByEmailAndPass() {
        CustomerDto customer = customerService.findByEmailAndPass("sama@email.com", "a1234S454");
        Assertions.assertNotNull(customer);
    }

    @Test
    void getNewPass_UpdateCustomerPass() {
        customerService.updatePassword("customer@email.com", "56A56745dd66");
    }











    @Test
    void incrementCreditTest() {
        Customer customer = customerService.getCustomerByEmail("customer@email.com");
        int result = customerService.incrementCredit(customer, 4000);
        Assertions.assertEquals(1, result);
    }

   /* @Test
    void getListOfferBySortTest() {
        Orders orders = customerService.getListOrders("customer@email.com").get(0);
        System.out.println(customerService.getListOffersSortByScoreOrPrice(orders, true, true));
    }*/

    @Test
    void addAddressToListAddressTest() {
        Address address = Address.builder()
                .tag("25")
                .postalCode("3453")
                .street("30metri")
                .city("tehran")
                .build();
        customerService.addAddressToListAddresses(address, "sama@email.com");
    }
}
