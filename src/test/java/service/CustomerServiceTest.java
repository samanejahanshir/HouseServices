package service;

import dao.SubServiceDao;
import model.Customer;
import model.Orders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerServiceTest {
    static CustomerService customerService;

    @BeforeAll
    static void init() {
        customerService = new CustomerService();
    }

    @Test
    void getCustomer_SaveToDb() {
        Customer customer = Customer.CustomerBuilder.aCustomer()
                .withFirstName("customer")
                .withLastName("Cfamily")
                .withPassword("a1234S454")
                .withEmail("customer@email.com")
                .build();
        customerService.saveCustomer(customer);
    }

    @Test
    void getCustomerDuplicate_SaveToDb_ThrowException() {
        Customer customer = Customer.CustomerBuilder.aCustomer()
                .withFirstName("customer")
                .withLastName("Cfamily")
                .withPassword("a1234S454")
                .withEmail("customer@email.com")
                .build();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.saveCustomer(customer));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this user by this email is exist", exp.getMessage());
    }

    @Test
    void getCustomer_ByEmailAndPass() {
        Customer customer = customerService.getCustomerByEmailAndPass("customer@email.com", "a1234S454");
        Assertions.assertNotNull(customer);
    }

    @Test
    void getNewPass_UpdateCustomerPass() {
        int id = customerService.updatePassword("customer@email.com", "56A56745dd66");
        Assertions.assertEquals(1, id);
    }

    @Test
    void saveOrderTest() {
        SubServiceDao subServiceDao = new SubServiceDao();
        Customer customer = customerService.getCustomerByEmail("customer@email.com");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2022-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Orders order = Orders.OrdersBuilder.anOrders()
                .withOrderDoneDate(date)
                .withOrderDoneTime(10)
                .withCustomer(customer)
                .withAddress(customer.getAddresses().get(0))
                .withDescription("saat 10 sobh anjam shavad")
                .withProposedPrice(3000)
                .withSubServices(subServiceDao.getService("tasisat", "bargh"))
                .build();
        customerService.saveOrder(order);
    }

    @Test
    void getListSubServiceTest() {
        int result = customerService.getListSubService("tasisat").size();
        Assertions.assertEquals(1, result);
    }

    @Test
    void getListMainServiceTest() {
        int result = customerService.getListMainService().size();
        Assertions.assertEquals(1, result);
    }

    @Test
    void getListOrdersTest() {
        System.out.println(customerService.getListOrders("customer@email.com").size());
    }

    @Test
    void getListOfferTest() {
        Orders orders = customerService.getListOrders("customer@email.com").get(0);
        System.out.println(customerService.getListOffers(orders));
    }

    @Test
    void selectOfferForOrderTest() {
        customerService.selectOfferForOrder(8, 2);
    }

    @Test
    void incrementCreditTest() {
        Customer customer = customerService.getCustomerByEmail("customer@email.com");
        int result = customerService.incrementCredit(customer, 4000);
        Assertions.assertEquals(1, result);
    }

    @Test
    void RegisterACommentToOrderTest() {
        int result = customerService.RegisterACommentToOrder(2, "alii bood . ba tashakor");
        Assertions.assertEquals(1, result);
    }

    @Test
    void deleteOrderTest(){
        customerService.deleteOrder(2);
    }
}
