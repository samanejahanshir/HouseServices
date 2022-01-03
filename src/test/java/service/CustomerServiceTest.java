package service;

import dao.SubServiceDao;
import model.Address;
import model.Customer;
import model.Orders;
import model.SubServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    void getCustomer_SaveToDb_ThrowException() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Customer customer = Customer.CustomerBuilder.aCustomer()
                .withFirstName("customer")
                .withLastName("Cfamily")
                .withPassword("a1234S454")
                .withEmail("customer@email.com")
                .build();

        CustomerService customerService = new CustomerService();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.saveCustomer(customer));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this user by this email is exist", exp.getMessage());
    }

    @Test
    void getCustomer_ByEmailAndPass() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomerByEmailAndPass("customer@email.com", "a1234S454");
        Assertions.assertNotNull(customer);

    }

    @Test
    void getNewPass_UpdateCustomerPass() {
        CustomerService customerService = new CustomerService();
        int id = customerService.updatePassword("customer@email.com", "56A56745dd66");
        Assertions.assertEquals(1, id);
    }

    @Test
    void saveOrderTest() {
        CustomerService customerService = new CustomerService();
        SubServiceDao subServiceDao = new SubServiceDao();
        Customer customer = customerService.getCustomerByEmail("customer@email.com");

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2022-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Orders order = Orders.OrderBuilder.anOrder()
                .withOrderDoneDate(date)
                .withOrderDoneTime(10)
                .withCustomer(customer)
                .withAddress(customer.getAddresses().get(0))
                .withDescription("saat 10 sobh anjam shavad")
                .withProposedPrice(3000)
                .withServices(subServiceDao.getService("tasisat", "bargh"))
                .build();
        customerService.saveOrder(order);
    }
}
