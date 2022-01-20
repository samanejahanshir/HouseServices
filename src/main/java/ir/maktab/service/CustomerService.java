package ir.maktab.service;

import ir.maktab.data.dao.CustomerDao;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.Address;
import ir.maktab.data.model.Customer;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.mapper.CustomerMapper;
import ir.maktab.exceptions.CustomerNotExistException;
import ir.maktab.exceptions.UserByEmailExistException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Data
public class CustomerService {
    final CustomerDao customerDao;
    //  final OrderService orderService;
    //  final OfferService offerService;
    //  private final OrderDao orderDao;
//    private final MainServiceDao mainServiceDao;
    //   private final SubServiceDao subServices;
    //  private final ExpertDao expertDao;
    //  private final OfferDao offerDao;
    //  private final OfferMapper offerMapper;
    final CustomerMapper customerMapper;
    //  private final MainServiceMapper mainServiceMapper;
    //  private final SubServiceMapper subServiceMapper;


    public void saveCustomer(Customer customer) {
        if (customerDao.findByEmail(customer.getEmail()).isEmpty()) {
            customer.setState(UserState.NOT_CONFIRMED);
            customerDao.save(customer);
        } else {
            throw new UserByEmailExistException();
        }
    }

   /* public void updateAddress(CustomerDto customerDto, Address address) {
        Optional<Customer> customerOptional = customerDao.findByEmail(customerDto.getEmail());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
          //  customer.getAddresses().add(address);
            customerDao.save(customer);
        } else {
            throw new CustomerNotExistException();
        }
    }*/

    public CustomerDto findByEmailAndPass(String email, String password) {
        Optional<Customer> customerOptional = customerDao.findByEmailAndPassword(email, password);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return customerMapper.toDto(customer);
        } else {
            throw new CustomerNotExistException();
        }
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = customerDao.findByEmail(email);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotExistException();
        }
    }

    public void updatePassword(String email, String newPassword) {
        Optional<Customer> customerOptional = customerDao.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setPassword(newPassword);
            customerDao.save(customer);
        } else {
            throw new CustomerNotExistException();
        }
    }


    @Transactional
    public void incrementCredit(CustomerDto customerDto, double amount) {
        Customer customer = getCustomerByEmail(customerDto.getEmail());
        if (customer != null) {
            customer.setCredit(customer.getCredit() + amount);
            customerDao.save(customer);
        }
    }

   /* public Customer getCustomerByEmailWithFetchJoinAddress(String email) {
        Optional<Customer> customer = customerDao.getCustomerByEmail(email);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("this customer by this email not exist.");
        }
    }
*/
  /*  @Transactional
    public void addAddressToListAddresses(Address address, String email) {
        Optional<Customer> customerOptional = customerDao.getCustomerByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
           // customer.getAddresses().add(address);
            customerDao.save(customer);
        }
    }*/

    public void updateCustomer(Customer customer) {
        Customer customer1 = getCustomerByEmail(customer.getEmail());
        if (customer1 != null) {
            customerDao.save(customer1);
        } else {
            throw new CustomerNotExistException();
        }
    }
   /* public void saveOrder(OrderDto orderDto, String email) {
        orderService.saveOrder(orderDto, email);
    }

    public List<OrderDto> getListOrders(String email) {
        return orderService.getListOrders(email);
    }

    public List<OrderDto> getListOrdersThatNotFinished(String email) {
        return orderService.getListOrdersThatNotFinished(email);
    }*/
    public CustomerDto getCustomerById(int id){
        Optional<Customer> customer = customerDao.findById(id);
        if(customer.isPresent()){
            return customerMapper.toDto(customer.get());
        }
        else {
            throw new CustomerNotExistException();
        }
    }
}
