package service;

import config.SpringConfig;
import dao.CustomerDao;
import lombok.Data;
import model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@Data
public class CustomerService {
    CustomerDao customerDao = new CustomerDao();//new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerDao.class);

    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public Customer getCustomerByEmail(String email,String password) {
        return customerDao.getCustomerByEmail(email,password);
    }

}
