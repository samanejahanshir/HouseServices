package service;

import dao.CustomerDao;
import dao.MainServiceDao;
import dao.OrderDao;
import dao.SubServiceDao;
import lombok.Data;
import model.*;
import model.enums.OrderState;
import model.enums.UserState;

import java.util.List;

@Data
public class CustomerService {
    CustomerDao customerDao = new CustomerDao();
    OrderDao orderDao = new OrderDao();
    MainServiceDao mainServiceDao = new MainServiceDao();
    SubServiceDao subServices = new SubServiceDao();

    public void saveCustomer(Customer customer) {
        if (customerDao.getCustomerByEmail(customer.getEmail()) == null) {
            customer.setState(UserState.NOT_CONFIRMED);
            customerDao.save(customer);
        } else {
            throw new RuntimeException("this user by this email is exist");
        }
    }

    public void updateAddress(Customer customer, Address address) {
        if (customerDao.getCustomerByEmail(customer.getEmail()) != null) {
            customer.getAddresses().add(address);
            address.setUser(customer);
            customerDao.update(customer);
        } else {
            throw new RuntimeException("this user by this email is exist");
        }
    }

    public Customer getCustomerByEmailAndPass(String email, String password) {
        return customerDao.getCustomerByEmailAndPass(email, password);
    }

    public Customer getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }

    public int updatePassword(String email, String newPassword) {
        return customerDao.UpdatePassword(email, newPassword);
    }

    public void saveOrder(Orders order) {
        order.setState(OrderState.WAIT_OFFER_EXPERTS);
        orderDao.save(order);
    }

    public List<MainServices> getListMainService() {
        return mainServiceDao.getListMainServices();
    }

    public List<SubServices> getListSubService(String groupName) {
        return subServices.getListSubServices(groupName);
    }

    public List<Orders> getListOrders(String email) {
        Customer customer = customerDao.getCustomerByEmail(email);
        return customerDao.getListOrders(customer);
    }

    public List<Offer> getListOffers(Orders order) {
        return orderDao.getListOffers(order);
    }
}
