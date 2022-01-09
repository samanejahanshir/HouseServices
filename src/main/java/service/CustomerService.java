package service;

import data.dao.*;
import data.model.*;
import lombok.Data;
import data.enums.OrderState;
import data.enums.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Data
public class CustomerService {
    private final CustomerDao customerDao;
    private final OrderDao orderDao;
    private final MainServiceDao mainServiceDao;
    private final SubServiceDao subServices;
    private final ExpertDao expertDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, OrderDao orderDao, MainServiceDao mainServiceDao, SubServiceDao subServices ,ExpertDao expertDao) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.mainServiceDao = mainServiceDao;
        this.subServices = subServices;
        this.expertDao=expertDao;
    }

    public void saveCustomer(Customer customer) {
        if (customerDao.findByEmail(customer.getEmail()).isEmpty()) {
            customer.setState(UserState.NOT_CONFIRMED);
            customerDao.save(customer);
        } else {
            throw new RuntimeException("this user by this email is exist");
        }
    }

   /* public void updateAddress(Customer customer, Address address) {
        if (customerDao.findByEmail(customer.getEmail()).isPresent()) {
            customer.getAddresses().add(address);
            address.setUser(customer);
            customerDao.update(customer);
        } else {
            throw new RuntimeException("this user by this email is exist");
        }
    }*/

    public Customer getCustomerByEmailAndPass(String email, String password) {
        Optional<Customer> customer = customerDao.findByEmailAndPassword(email, password);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("this customer by this email and password not exist.");
        }
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = customerDao.findByEmail(email);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("this customer by this email not exist.");
        }
    }

    public int updatePassword(String email, String newPassword) {
        return customerDao.UpdatePassword(email, newPassword);
    }

    public void saveOrder(Orders order) {
        order.setState(OrderState.WAIT_OFFER_EXPERTS);
        orderDao.save(order);
    }

    public List<MainServices> getListMainService() {
        return mainServiceDao.findAll();
    }

    public List<SubServices> getListSubService(String groupName) {
        return subServices.findAllByGroupName(groupName);
    }

    public List<Orders> getListOrders(String email) {
        List<Orders> ordersList=new ArrayList<>();
        try {
            Customer customer = customerDao.findByEmail(email).get();
            ordersList= customerDao.getListOrders(customer.getId());
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return ordersList;
    }

    public List<Offer> getListOffers(Orders order) {
        return orderDao.getListOffers(order.getId());
    }

    public void selectOfferForOrder(int idExpert, int idOrder) {
        Optional<Orders> orders = orderDao.findById(idOrder);
        if(orders.isPresent()) {
            Orders order=orders.get();
            Expert expert = expertDao.findById(idExpert).get();
            if (order != null && expert != null) {
                order.setExpert(expert);
                order.setState(OrderState.WAIT_SELECT_EXPERT);
               //TODO orderDao.update(order);
            }
        }
    }

    @Transactional
    public int incrementCredit(Customer customer, double amount) {
        return customerDao.updateCredit(customer.getId(), customer.getCredit() + amount);
    }

    public int RegisterACommentToOrder(int orderId, String comment) {
        return orderDao.updateOrderComment(orderId, comment);
    }

    public void deleteOrder(int orderId) {
        orderDao.deleteById(orderId);
    }

    public Customer getCustomerByEmailWithFetchJoinAddress(String email) {
        Optional<Customer> customer = customerDao.getCustomerByEmail(email);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("this customer by this email not exist.");
        }
    }
}
