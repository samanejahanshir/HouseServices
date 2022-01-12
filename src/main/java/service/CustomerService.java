package service;

import data.dao.*;
import data.enums.OrderState;
import data.enums.UserState;
import data.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class CustomerService {
    private final CustomerDao customerDao;
    private final OrderDao orderDao;
    private final MainServiceDao mainServiceDao;
    private final SubServiceDao subServices;
    private final ExpertDao expertDao;
    private final OfferDao offerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, OrderDao orderDao, MainServiceDao mainServiceDao, SubServiceDao subServices, ExpertDao expertDao, OfferDao offerDao) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.mainServiceDao = mainServiceDao;
        this.subServices = subServices;
        this.expertDao = expertDao;
        this.offerDao = offerDao;
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
        double basePrice = order.getSubServices().getBasePrice();
        double proposedPrice = order.getProposedPrice();
        if (proposedPrice >= basePrice) {
            order.setState(OrderState.WAIT_OFFER_EXPERTS);
            orderDao.save(order);
        } else {
            throw new RuntimeException("proposedPrice should be bigger than basePrice of subService");
        }
    }

    public List<MainServices> getListMainService() {
        return mainServiceDao.findAll();
    }

    public List<SubServices> getListSubService(String groupName) {
        return subServices.findAllByGroupName(groupName);
    }

    public List<Orders> getListOrders(String email) {
        List<Orders> ordersList = new ArrayList<>();
        try {
            Customer customer = customerDao.findByEmail(email).get();
            ordersList = customerDao.getAllOrders(customer.getId());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public List<Orders> getListOrdersThatNotFinished(String email) {
        List<Orders> ordersList = new ArrayList<>();
        try {
            Customer customer = customerDao.findByEmail(email).get();
            ordersList = customerDao.getListOrdersThatNotFinished(customer.getId());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public List<Offer> getListOffers(Orders order) {
        return orderDao.getListOffers(order.getId());
    }

    public List<Offer> getListOffersSortByScoreOrPrice(Orders order, boolean byPrice, boolean byScoreExpert) {
        if (byPrice && !byScoreExpert) {
            return orderDao.getListOffersBySort(order.getId(), Sort.by("offerPrice").ascending());
        } else if (!byPrice && byScoreExpert) {
            return orderDao.getListOffersBySort(order.getId(), Sort.by("expert.score").descending());
        } else if (byPrice && byScoreExpert) {
            return orderDao.getListOffersBySort(order.getId(), Sort.by("expert.score").descending().and(Sort.by("offerPrice").ascending()));
        } else {
            return getListOffers(order);
        }
    }

    public void selectOfferForOrder(int idExpert, int idOrder) {
        Optional<Orders> orders = orderDao.findById(idOrder);
        if (orders.isPresent()) {
            Orders order = orders.get();
            Expert expert = expertDao.findById(idExpert).get();
            order.setExpert(expert);
            order.setState(OrderState.WAIT_EXPERT_COME);
            orderDao.save(order);
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
        //offerDao.deleteOffersOfAOrder(orderId);
        List<Integer> offersId = orderDao.getListOffers(orderId).stream().map(offer -> offer.getId()).collect(Collectors.toList());
        if (!offersId.isEmpty()) {
            offerDao.deleteByIdIn(offersId);
        }
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

    @Transactional
    public void addAddressToListAddresses(Address address,String email){
        Optional<Customer> customerOptional = customerDao.getCustomerByEmail(email);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.getAddresses().add(address);
            customerDao.save(customer);
        }
    }
}
