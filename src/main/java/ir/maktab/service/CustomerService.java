package ir.maktab.service;

import ir.maktab.data.dao.*;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.enums.UserState;
import ir.maktab.data.model.*;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.mapper.CustomerMapper;
import ir.maktab.dto.mapper.MainServiceMapper;
import ir.maktab.dto.mapper.OfferMapper;
import ir.maktab.dto.mapper.SubServiceMapper;
import ir.maktab.exceptions.CustomerNotExistException;
import ir.maktab.exceptions.UserByEmailExistException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class CustomerService {
    private final CustomerDao customerDao;
  //  private final OrderDao orderDao;
//    private final MainServiceDao mainServiceDao;
 //   private final SubServiceDao subServices;
  //  private final ExpertDao expertDao;
  //  private final OfferDao offerDao;
  //  private final OfferMapper offerMapper;
    private final CustomerMapper customerMapper;
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

    public void updateAddress(CustomerDto customerDto, Address address) {
        Optional<Customer> customerOptional = customerDao.findByEmail(customerDto.getEmail());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.getAddresses().add(address);
            customerDao.save(customer);
        } else {
            throw new CustomerNotExistException();
        }
    }

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
    public int incrementCredit(Customer customer, double amount) {
        return customerDao.updateCredit(customer.getId(), customer.getCredit() + amount);
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
    public void addAddressToListAddresses(Address address, String email) {
        Optional<Customer> customerOptional = customerDao.getCustomerByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.getAddresses().add(address);
            customerDao.save(customer);
        }
    }
}
