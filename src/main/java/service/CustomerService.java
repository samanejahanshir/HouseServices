package service;

import dao.CustomerDao;
import lombok.Data;
import model.Customer;
import model.enums.UserState;

@Data
public class CustomerService {
    CustomerDao customerDao = new CustomerDao();//new AnnotationConfigApplicationContext(SpringConfig.class).getBean(CustomerDao.class);

    public void saveCustomer(Customer customer) {
        if(customerDao.getCustomerByEmail(customer.getEmail())==null) {
            customer.setState(UserState.NOT_CONFIRMED);
            customerDao.save(customer);
        }
        else {
            throw new RuntimeException("this user by this email is exist");
        }
    }

    public Customer getCustomerByEmail(String email,String password) {
        return customerDao.getCustomerByEmailAndPass(email,password);
    }
    public int updatePassword(String email,String newPassword){
       return customerDao.UpdatePassword(email,newPassword);
    }

}
