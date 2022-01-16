package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.model.Address;
import ir.maktab.data.model.Customer;
import ir.maktab.dto.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class CustomerServiceTest {
    static CustomerService customerService;

    @BeforeAll
    static void init() {
        customerService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerService.class);
    }

    @Test
    void getCustomer_SaveToDb() {
        Address address = Address.builder()
                .city("ghom")
                .street("yas")
                .postalCode("3424")
                .tag("34")
                .build();
        Customer customer = Customer.builder()
                .firstName("zahra")
                .lastName("samaii")
                .password("a1234S454")
                .email("zahra@email.com")
                .build();
        //  customer.getAddresses().add(address);
        customer.setAddresses(Set.of(address));
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
        CustomerDto customerDto = CustomerDto.builder().email("samane@email.com").build();
        customerService.incrementCredit(customerDto, 4000);
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
        customerService.addAddressToListAddresses(address, "samane@email.com");
    }
}
