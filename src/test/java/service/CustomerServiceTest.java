package service;

import model.Customer;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerServiceTest {
    @Test
    void getCustomer_SaveToDb() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Customer customer = Customer.CustomerBuilder.aCustomer()
                .withFirstName("customer")
                .withLastName("Cfamily")
                .withPassword("a1234S454")
                .withEmail("customer@email.com")
                .build();

        CustomerService customerService = new CustomerService();
        customerService.saveCustomer(customer);
    }

    @Test
    void getCustomer_ByEmailAndPass() {
        CustomerService customerService = new CustomerService();
        Customer customer=customerService.getCustomerByEmail("customer@email.com", "a1234S454");
        Assertions.assertNotNull(customer);

    }

    @Test
    void getNewPass_UpdateCustomerPass() {
        CustomerService customerService = new CustomerService();
        int id = customerService.updatePassword("customer@email.com", "56A56745dd66");
        Assertions.assertEquals(1, id);
    }
}
